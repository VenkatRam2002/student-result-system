public class Result {
    private int resultId;
    private int studentId;
    private int subjectId;
    private int marksObtained;
    private String grade;
    private String studentName;
    private String subjectName;
    private int maxMarks;

    public Result(int studentId, int subjectId, int marksObtained) {
        if (marksObtained < 0 || marksObtained > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100. Got: " + marksObtained);
        }
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.marksObtained = marksObtained;
        this.grade = calculateGrade(marksObtained);
    }

    public Result(int resultId, int studentId, int subjectId, int marksObtained,
                  String studentName, String subjectName, int maxMarks) {
        this.resultId = resultId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.marksObtained = marksObtained;
        this.grade = calculateGrade(marksObtained);
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.maxMarks = maxMarks;
    }

    public static String calculateGrade(int marks) {
        if (marks >= 90) return "O";       // Outstanding
        else if (marks >= 80) return "A+"; // Excellent
        else if (marks >= 70) return "A";  // Very Good
        else if (marks >= 60) return "B";  // Good
        else if (marks >= 50) return "C";  // Average
        else if (marks >= 35) return "D";  // Pass
        else return "F";                   // Fail
    }

    public boolean isPass() {
        return marksObtained >= 35;
    }

    public int getResultId() { return resultId; }
    public int getStudentId() { return studentId; }
    public int getSubjectId() { return subjectId; }
    public int getMarksObtained() { return marksObtained; }
    public String getGrade() { return grade; }
    public String getStudentName() { return studentName; }
    public String getSubjectName() { return subjectName; }
    public int getMaxMarks() { return maxMarks; }
}
