import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
    private int courseId;
    private String courseName;
    private String instructor;
    private String schedule;
    private String location;

    public Course(String courseName, String instructor, String schedule, String location) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.schedule = schedule;
        this.location = location;
    }

    public Course(int courseId, String courseName, String instructor, String schedule, String location) {
        this(courseName, instructor, schedule, location);
        this.courseId = courseId;
    }

    public void addCourse() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Course (course_name, instructor, schedule, location) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.courseName);
            stmt.setString(2, this.instructor);
            stmt.setString(3, this.schedule);
            stmt.setString(4, this.location);
            stmt.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    public static Course getCourseById(int courseId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Course WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String courseName = rs.getString("course_name");
                String instructor = rs.getString("instructor");
                String schedule = rs.getString("schedule");
                String location = rs.getString("location");
                return new Course(courseId, courseName, instructor, schedule, location);
            } else {
                System.out.println("Course not found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching course: " + e.getMessage());
            return null;
        }
    }

    public void updateCourse() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Course SET course_name = ?, instructor = ?, schedule = ?, location = ? WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.courseName);
            stmt.setString(2, this.instructor);
            stmt.setString(3, this.schedule);
            stmt.setString(4, this.location);
            stmt.setInt(5, this.courseId);
            stmt.executeUpdate();
            System.out.println("Course updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    public static void deleteCourseById(int courseId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Course WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
            System.out.println("Course deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }


    public String toString() {
        return "Course ID: " + courseId + "\nCourse Name: " + courseName + "\nInstructor: " + instructor + "\nSchedule: " + schedule + "\nLocation: " + location;
    }
}

