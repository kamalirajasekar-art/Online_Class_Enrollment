package model;

public abstract class Person {
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        setId(id);
        setName(name);
        setEmail(email);
    }

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("ID cannot be empty.");
        this.id = id.trim();
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new IllegalArgumentException("Enter a valid email address.");
        this.email = email.trim();
    }

    public abstract String getRole();
}
