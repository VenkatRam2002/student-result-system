import java.util.List;
import java.util.Scanner;

public class Main {

    static StudentDAO studentDAO = new StudentDAO();
    static SubjectDAO subjectDAO = new SubjectDAO();
    static ResultDAO resultDAO = new ResultDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Result Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addSubject();
                case 3 -> enterMarks();
                case 4 -> viewResults();
                case 5 -> exportReportCard();
                case 6 -> listAllStudents();
                case 0 -> {
                    running = false;
                    DatabaseConnection.closeConnection();
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Subject");
        System.out.println("3. Enter Marks");
        System.out.println("4. View Student Results");
        System.out.println("5. Export Report Card to File");
        System.out.println("6. List All Students");
        System.out.println("0. Exit");
    }

    static void addStudent() {
        System.out.println("\n-- Add Student --");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Roll Number: ");
        String roll = scanner.nextLine().trim();
        System.out.print("Branch (e.g. ECE, CSE): ");
        String branch = scanner.nextLine().trim();
        int year = readInt("Year (1-4): ");

        Student s = new Student(name, roll, branch, year);
        if (studentDAO.addStudent(s)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    static void addSubject() {
        System.out.println("\n-- Add Subject --");
        System.out.print("Subject Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Subject Code: ");
        String code = scanner.nextLine().trim();
        int max = readInt("Max Marks (usually 100): ");

        Subject sub = new Subject(name, code, max);
        if (subjectDAO.addSubject(sub)) {
            System.out.println("Subject added successfully.");
        } else {
            System.out.println("Failed to add subject.");
        }
    }

    static void enterMarks() {
        System.out.println("\n-- Enter Marks --");
        int studentId = readInt("Student ID: ");
        int subjectId = readInt("Subject ID: ");

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        int marks = readInt("Marks Obtained (0-100): ");
        try {
            Result result = new Result(studentId, subjectId, marks);
            if (resultDAO.addResult(result)) {
                System.out.println("Marks entered. Grade: " + result.getGrade());
            } else {
                System.out.println("Failed to save marks.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void viewResults() {
        System.out.println("\n-- View Results --");
        int studentId = readInt("Student ID: ");

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Result> results = resultDAO.getResultsByStudent(studentId);
        if (results.isEmpty()) {
            System.out.println("No results found for this student.");
            return;
        }

        System.out.println("\nResults for: " + student.getName());
        System.out.printf("%-25s %-8s %-6s%n", "Subject", "Marks", "Grade");
        System.out.println("-".repeat(42));
        for (Result r : results) {
            System.out.printf("%-25s %-8d %-6s%n", r.getSubjectName(), r.getMarksObtained(), r.getGrade());
        }
    }

    static void exportReportCard() {
        System.out.println("\n-- Export Report Card --");
        int studentId = readInt("Student ID: ");

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Result> results = resultDAO.getResultsByStudent(studentId);
        if (results.isEmpty()) {
            System.out.println("No results found. Enter marks first.");
            return;
        }

        ReportCardExporter.exportReportCard(student, results);
    }

    static void listAllStudents() {
        System.out.println("\n-- All Students --");
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.printf("%-6s %-20s %-12s %-8s %-4s%n", "ID", "Name", "Roll", "Branch", "Year");
        System.out.println("-".repeat(55));
        for (Student s : students) {
            System.out.printf("%-6d %-20s %-12s %-8s %-4d%n",
                s.getStudentId(), s.getName(), s.getRollNumber(), s.getBranch(), s.getYear());
        }
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
