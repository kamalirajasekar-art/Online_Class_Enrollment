package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Enrollment;
import model.OnlineCourse;
import model.Student;
import exception.EnrollmentException;

public class EnrollmentService {
    private final List<Student> students = new ArrayList<>();
    private final List<OnlineCourse> courses = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentService() {
        courses.add(new OnlineCourse("JAVA101", "Java Programming", "Ms. Priya", 30));
        courses.add(new OnlineCourse("WEB102", "Web Development", "Mr. Arun", 25));
        courses.add(new OnlineCourse("DS103", "Data Structures", "Dr. Karthik", 20));
    }

    public void addStudent(Student student) throws EnrollmentException {
        if (findStudent(student.getId()) != null) throw new EnrollmentException("Student ID already exists.");
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(student.getEmail()))
                throw new EnrollmentException("A student with this email already exists.");
        }
        students.add(student);
    }

    public void addCourse(OnlineCourse course) throws EnrollmentException {
        if (findCourse(course.getCourseId()) != null) throw new EnrollmentException("Course ID already exists.");
        courses.add(course);
    }

    public Student findStudent(String id) {
        for (Student student : students)
            if (student.getId().equalsIgnoreCase(id.trim())) return student;
        return null;
    }

    public OnlineCourse findCourse(String id) {
        for (OnlineCourse course : courses)
            if (course.getCourseId().equalsIgnoreCase(id.trim())) return course;
        return null;
    }

    public void enroll(String studentId, String courseId) throws EnrollmentException {
        Student student = findStudent(studentId);
        if (student == null) throw new EnrollmentException("Student not found. Add the student first.");
        OnlineCourse course = findCourse(courseId);
        if (course == null) throw new EnrollmentException("Course not found.");
        for (Enrollment e : enrollments) {
            if (e.getStudent().getId().equalsIgnoreCase(studentId)
                    && e.getCourse().getCourseId().equalsIgnoreCase(courseId))
                throw new EnrollmentException("Student is already enrolled in this course.");
        }
        if (!course.hasSpace()) throw new EnrollmentException("Course is full.");
        course.enrollOne();
        enrollments.add(new Enrollment(student, course));
    }

    public void cancelEnrollment(String studentId, String courseId) throws EnrollmentException {
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            if (e.getStudent().getId().equalsIgnoreCase(studentId)
                    && e.getCourse().getCourseId().equalsIgnoreCase(courseId)) {
                e.getCourse().unenrollOne();
                enrollments.remove(i);
                return;
            }
        }
        throw new EnrollmentException("Matching enrollment was not found.");
    }

    public List<Student> getStudents() { return Collections.unmodifiableList(students); }
    public List<OnlineCourse> getCourses() { return Collections.unmodifiableList(courses); }
    public List<Enrollment> getEnrollments() { return Collections.unmodifiableList(enrollments); }

    public List<Enrollment> getStudentEnrollments(String studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments)
            if (e.getStudent().getId().equalsIgnoreCase(studentId)) result.add(e);
        return result;
    }
}
