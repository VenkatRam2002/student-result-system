import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public boolean addSubject(Subject subject) {
        String sql = "INSERT INTO subjects (subject_name, subject_code, max_marks) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, subject.getSubjectName());
            ps.setString(2, subject.getSubjectCode());
            ps.setInt(3, subject.getMaxMarks());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding subject: " + e.getMessage());
            return false;
        }
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects ORDER BY subject_name";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                subjects.add(new Subject(
                    rs.getInt("subject_id"),
                    rs.getString("subject_name"),
                    rs.getString("subject_code"),
                    rs.getInt("max_marks")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching subjects: " + e.getMessage());
        }
        return subjects;
    }
}
