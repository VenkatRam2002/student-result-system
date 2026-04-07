import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportCardExporter {

    public static void exportReportCard(Student student, List<Result> results) {
        String filename = "ReportCard_" + student.getRollNumber() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("=".repeat(55));
            writer.newLine();
            writer.write("         STUDENT RESULT MANAGEMENT SYSTEM");
            writer.newLine();
            writer.write("                   REPORT CARD");
            writer.newLine();
            writer.write("=".repeat(55));
            writer.newLine();

            writer.write(String.format("%-20s: %s%n", "Student Name", student.getName()));
            writer.write(String.format("%-20s: %s%n", "Roll Number", student.getRollNumber()));
            writer.write(String.format("%-20s: %s%n", "Branch", student.getBranch()));
            writer.write(String.format("%-20s: %d%n", "Year", student.getYear()));
            writer.write("-".repeat(55));
            writer.newLine();

            writer.write(String.format("%-25s %-8s %-6s %-6s%n", "Subject", "MaxMark", "Marks", "Grade"));
            writer.write("-".repeat(55));
            writer.newLine();

            int totalMarks = 0;
            int totalMax = 0;
            int failCount = 0;

            for (Result r : results) {
                writer.write(String.format("%-25s %-8d %-6d %-6s%n",
                    r.getSubjectName(), r.getMaxMarks(), r.getMarksObtained(), r.getGrade()));
                totalMarks += r.getMarksObtained();
                totalMax += r.getMaxMarks();
                if (!r.isPass()) failCount++;
            }

            writer.write("-".repeat(55));
            writer.newLine();

            double percentage = totalMax > 0 ? (totalMarks * 100.0 / totalMax) : 0;
            String overallGrade = Result.calculateGrade((int) percentage);
            String status = failCount > 0 ? "FAIL (Arrears: " + failCount + ")" : "PASS";

            writer.write(String.format("%-25s %-8d %-6d %-6s%n", "TOTAL", totalMax, totalMarks, overallGrade));
            writer.write(String.format("%-25s %.2f%%%n", "Percentage", percentage));
            writer.write(String.format("%-25s %s%n", "Result", status));
            writer.write("=".repeat(55));
            writer.newLine();

            writer.write("Grade Scale: O(90+) A+(80+) A(70+) B(60+) C(50+) D(35+) F(<35)");
            writer.newLine();

            System.out.println("Report card exported successfully: " + filename);

        } catch (IOException e) {
            System.out.println("Error exporting report card: " + e.getMessage());
        }
    }
}
