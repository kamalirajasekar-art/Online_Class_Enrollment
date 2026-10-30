package main;

import java.util.List;
import java.util.Scanner;
import exception.EnrollmentException;
import model.Enrollment;
import model.OnlineCourse;
import model.Person;
import model.Student;
import service.EnrollmentService;
import util.InputUtil;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EnrollmentService service = new EnrollmentService();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("======================================");
        System.out.println("     ONLINE CLASS ENROLLMENT SYSTEM");
        System.out.println("======================================");

        while (running) {
            showMenu();
            int choice = InputUtil.readInt(scanner, "Enter your choice: ", 0, 8);
            try {
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: viewStudents(); break;
                    case 3: addCourse(); break;
                    case 4: viewCourses(); break;
                    case 5: enrollStudent(); break;
                    case 6: viewEnrollments(); break;
                    case 7: cancelEnrollment(); break;
                    case 8: viewStudentEnrollments(); break;
                    case 0: running = false; System.out.println("Thank you for using the system!"); break;
                    default: System.out.println("Invalid menu choice.");
                }
            } catch (EnrollmentException | IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected input or system error: " + ex.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Add Online Course");
        System.out.println("4. View Courses");
        System.out.println("5. Enroll Student in a Course");
        System.out.println("6. View All Enrollments");
        System.out.println("7. Cancel Enrollment");
        System.out.println("8. View Enrollments by Student");
        System.out.println("0. Exit");
    }

    private static void addStudent() throws EnrollmentException {
        String id = InputUtil.readRequired(scanner, "Student ID: ");
        String name = InputUtil.readRequired(scanner, "Student name: ");
        String email = InputUtil.readRequired(scanner, "Email: ");
        String department = InputUtil.readRequired(scanner, "Department: ");
        // Person is abstract; Student inherits its shared identity details.
        Person student = new Student(id, name, email, department);
        service.addStudent((Student) student);
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (service.getStudents().isEmpty()) { System.out.println("No students registered."); return; }
        System.out.println("\n--- Students ---");
        for (Person person : service.getStudents()) {
            System.out.println(person.getRole() + " | " + person);
        }
    }

    private static void addCourse() throws EnrollmentException {
        String id = InputUtil.readRequired(scanner, "Course ID: ");
        String title = InputUtil.readRequired(scanner, "Course title: ");
        String instructor = InputUtil.readRequired(scanner, "Instructor name: ");
        int capacity = InputUtil.readInt(scanner, "Maximum seats: ", 1, 100000);
        service.addCourse(new OnlineCourse(id, title, instructor, capacity));
        System.out.println("Course added successfully.");
    }

    private static void viewCourses() {
        System.out.println("\n--- Online Courses ---");
        for (OnlineCourse course : service.getCourses()) System.out.println(course);
    }

    private static void enrollStudent() throws EnrollmentException {
        System.out.println("Available courses:");
        viewCourses();
        String studentId = InputUtil.readRequired(scanner, "Student ID: ");
        String courseId = InputUtil.readRequired(scanner, "Course ID: ");
        service.enroll(studentId, courseId);
        System.out.println("Enrollment successful.");
    }

    private static void viewEnrollments() {
        List<Enrollment> enrollments = service.getEnrollments();
        if (enrollments.isEmpty()) { System.out.println("No enrollments found."); return; }
        System.out.println("\n--- All Enrollments ---");
        for (Enrollment enrollment : enrollments) System.out.println(enrollment);
    }

    private static void cancelEnrollment() throws EnrollmentException {
        String studentId = InputUtil.readRequired(scanner, "Student ID: ");
        String courseId = InputUtil.readRequired(scanner, "Course ID: ");
        service.cancelEnrollment(studentId, courseId);
        System.out.println("Enrollment cancelled successfully.");
    }

    private static void viewStudentEnrollments() {
        String studentId = InputUtil.readRequired(scanner, "Student ID: ");
        List<Enrollment> list = service.getStudentEnrollments(studentId);
        if (list.isEmpty()) { System.out.println("No enrollments found for this student."); return; }
        for (Enrollment enrollment : list) System.out.println(enrollment);
    }
}
