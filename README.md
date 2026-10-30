# Online Class Enrollment System (Java Console Application)

A menu-driven Java console project for managing online courses and student enrollments.

## Features
- Add and view students
- Add and list online courses
- Enroll a student in a course
- View a student's enrollments
- Cancel an enrollment
- Input validation and custom exceptions
- Encapsulation, inheritance, aggregation, and polymorphism
- Packages: `model`, `service`, `exception`, `util`, `main`

## Requirements
- Java JDK 8 or later
- Terminal / Command Prompt

## Compile and run
Open a terminal in the `src` folder (the folder containing the `main` package directory), then run:

```bash
javac -d out model/*.java exception/*.java service/*.java util/*.java main/*.java
java -cp out main.Main
```

If using an IDE such as IntelliJ IDEA or Eclipse, import the project as a Java project and run `main.Main`.

## Notes
- The application stores data in memory; data is reset when the program exits.
- Sample courses are loaded at startup.
