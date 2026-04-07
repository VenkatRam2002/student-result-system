public class Student {
    private int studentId;
    private String name;
    private String rollNumber;
    private String branch;
    private int year;

    public Student(int studentId, String name, String rollNumber, String branch, int year) {
        this.studentId = studentId;
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.year = year;
    }

    public Student(String name, String rollNumber, String branch, int year) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.year = year;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getBranch() { return branch; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Student[ID=" + studentId + ", Name=" + name + ", Roll=" + rollNumber + ", Branch=" + branch + ", Year=" + year + "]";
    }
}
