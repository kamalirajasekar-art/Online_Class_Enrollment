package model;

public class OnlineCourse {
    private String courseId;
    private String title;
    private String instructor;
    private int capacity;
    private int enrolledCount;

    public OnlineCourse(String courseId, String title, String instructor, int capacity) {
        setCourseId(courseId);
        setTitle(title);
        setInstructor(instructor);
        setCapacity(capacity);
        this.enrolledCount = 0;
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) throw new IllegalArgumentException("Course ID cannot be empty.");
        this.courseId = courseId.trim().toUpperCase();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Course title cannot be empty.");
        this.title = title.trim();
    }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty()) throw new IllegalArgumentException("Instructor cannot be empty.");
        this.instructor = instructor.trim();
    }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be greater than zero.");
        if (capacity < enrolledCount) throw new IllegalArgumentException("Capacity cannot be below current enrollment.");
        this.capacity = capacity;
    }

    public int getEnrolledCount() { return enrolledCount; }
    public boolean hasSpace() { return enrolledCount < capacity; }

    public void enrollOne() {
        if (!hasSpace()) throw new IllegalStateException("This course is full.");
        enrolledCount++;
    }

    public void unenrollOne() {
        if (enrolledCount > 0) enrolledCount--;
    }

    @Override
    public String toString() {
        return courseId + " | " + title + " | Instructor: " + instructor
                + " | Seats: " + enrolledCount + "/" + capacity;
    }
}
