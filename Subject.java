public class Subject {
    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private int maxMarks;

    public Subject(int subjectId, String subjectName, String subjectCode, int maxMarks) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.maxMarks = maxMarks;
    }

    public Subject(String subjectName, String subjectCode, int maxMarks) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.maxMarks = maxMarks;
    }

    public int getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public String getSubjectCode() { return subjectCode; }
    public int getMaxMarks() { return maxMarks; }

    @Override
    public String toString() {
        return "Subject[ID=" + subjectId + ", Name=" + subjectName + ", Code=" + subjectCode + ", MaxMarks=" + maxMarks + "]";
    }
}
