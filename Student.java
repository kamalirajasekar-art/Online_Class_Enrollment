package model;

public class Student extends Person {
    private String department;

    public Student(String id, String name, String email, String department) {
        super(id, name, email);
        setDepartment(department);
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty())
            throw new IllegalArgumentException("Department cannot be empty.");
        this.department = department.trim();
    }

    @Override
    public String getRole() { return "Student"; }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Name: " + getName() + " | Email: " + getEmail()
                + " | Department: " + department;
    }
}
