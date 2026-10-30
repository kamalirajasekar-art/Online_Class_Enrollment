package model;

import java.time.LocalDate;

public class Enrollment {
    private final Student student;
    private final OnlineCourse course;
    private final LocalDate enrollmentDate;

    public Enrollment(Student student, OnlineCourse course) {
        if (student == null || course == null) throw new IllegalArgumentException("Student and course are required.");
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    public Student getStudent() { return student; }
    public OnlineCourse getCourse() { return course; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }

    @Override
    public String toString() {
        return student.getId() + " - " + student.getName() + " | "
                + course.getCourseId() + " - " + course.getTitle()
                + " | Enrolled on: " + enrollmentDate;
    }
}
