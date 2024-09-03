import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enrollment {
    private int enrollmentId;
    private int courseId;
    private int studentId;
    private String enrollmentDate;
    private String grade;

    public Enrollment(int courseId, int studentId, String enrollmentDate) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int enrollmentId, int courseId, int studentId, String enrollmentDate, String grade) {
        this(courseId, studentId, enrollmentDate);
        this.enrollmentId = enrollmentId;
        this.grade = grade;
    }

    public void enrollStudent() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Enrollment (course_id, student_id, enrollment_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.courseId);
            stmt.setInt(2, this.studentId);
            stmt.setString(3, this.enrollmentDate);
            stmt.executeUpdate();
            System.out.println("Student enrolled successfully.");
        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    public static Enrollment getEnrollmentById(int enrollmentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Enrollment WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enrollmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int courseId = rs.getInt("course_id");
                int studentId = rs.getInt("student_id");
                String enrollmentDate = rs.getString("enrollment_date");
                String grade = rs.getString("grade");
                return new Enrollment(enrollmentId, courseId, studentId, enrollmentDate, grade);
            } else {
                System.out.println("Enrollment not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching enrollment: " + e.getMessage());
            return null;
        }
    }

    public void updateEnrollmentGrade(String newGrade) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Enrollment SET grade = ? WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newGrade);
            stmt.setInt(2, this.enrollmentId);
            stmt.executeUpdate();
            System.out.println("Enrollment grade updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating enrollment grade: " + e.getMessage());
        }
    }

    public static void deleteEnrollmentById(int enrollmentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Enrollment WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
            System.out.println("Enrollment record deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting enrollment record: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Enrollment ID: " + enrollmentId + "\nCourse ID: " + courseId + "\nStudent ID: " + studentId + "\nEnrollment Date: " + enrollmentDate + "\nGrade: " + grade;
    }
}
