import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    public boolean addResult(Result result) {
        String sql = "INSERT INTO results (student_id, subject_id, marks_obtained, grade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, result.getStudentId());
            ps.setInt(2, result.getSubjectId());
            ps.setInt(3, result.getMarksObtained());
            ps.setString(4, result.getGrade());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding result: " + e.getMessage());
            return false;
        }
    }

    public List<Result> getResultsByStudent(int studentId) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT r.result_id, r.student_id, r.subject_id, r.marks_obtained, " +
                     "s.name AS student_name, sub.subject_name, sub.max_marks " +
                     "FROM results r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "JOIN subjects sub ON r.subject_id = sub.subject_id " +
                     "WHERE r.student_id = ? " +
                     "ORDER BY sub.subject_name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new Result(
                    rs.getInt("result_id"),
                    rs.getInt("student_id"),
                    rs.getInt("subject_id"),
                    rs.getInt("marks_obtained"),
                    rs.getString("student_name"),
                    rs.getString("subject_name"),
                    rs.getInt("max_marks")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching results: " + e.getMessage());
        }
        return results;
    }
}
