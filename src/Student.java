import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String major;
    private int year;

    public Student(String studentName, String email, String major, int year) {
        this.studentName = studentName;
        this.email = email;
        this.major = major;
        this.year = year;
    }

    public Student(int studentId, String studentName, String email, String major, int year) {
        this(studentName, email, major, year);
        this.studentId = studentId;
    }

    public void addStudent() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Student (student_name, email, major, year) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.studentName);
            stmt.setString(2, this.email);
            stmt.setString(3, this.major);
            stmt.setInt(4, this.year);
            stmt.executeUpdate();
            System.out.println("Student registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
    }

    public static Student getStudentById(int studentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Student WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("student_name");
                String email = rs.getString("email");
                String major = rs.getString("major");
                int year = rs.getInt("year");
                return new Student(studentId, studentName, email, major, year);
            } else {
                System.out.println("Student not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student: " + e.getMessage());
            return null;
        }
    }

    public void updateStudent() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Student SET student_name = ?, email = ?, major = ?, year = ? WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.studentName);
            stmt.setString(2, this.email);
            stmt.setString(3, this.major);
            stmt.setInt(4, this.year);
            stmt.setInt(5, this.studentId);
            stmt.executeUpdate();
            System.out.println("Student information updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public static void deleteStudentById(int studentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Student WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
            System.out.println("Student account deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nStudent Name: " + studentName + "\nEmail: " + email + "\nMajor: " + major + "\nYear: " + year;
    }
}

