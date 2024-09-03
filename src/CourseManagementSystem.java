import java.util.Scanner;

public class CourseManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nUniversity Course Management System");
            System.out.println("1. Add Course");
            System.out.println("2. View Course");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Add Student");
            System.out.println("6. View Student");
            System.out.println("7. Update Student");
            System.out.println("8. Delete Student");
            System.out.println("9. Enroll Student in Course");
            System.out.println("10. View Enrollment");
            System.out.println("11. Update Enrollment Grade");
            System.out.println("12. Delete Enrollment");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter instructor: ");
                    String instructor = scanner.nextLine();
                    System.out.print("Enter schedule: ");
                    String schedule = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    Course newCourse = new Course(courseName, instructor, schedule, location);
                    newCourse.addCourse();
                    break;

                case 2:
                    System.out.print("Enter course ID: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine();
                    Course course = Course.getCourseById(courseId);
                    if (course != null) {
                        System.out.println(course);
                    }
                    break;

                case 3:
                    System.out.print("Enter course ID to update: ");
                    courseId = scanner.nextInt();
                    scanner.nextLine();
                    course = Course.getCourseById(courseId);
                    if (course != null) {
                        System.out.print("Enter new course name: ");
                        courseName = scanner.nextLine();
                        System.out.print("Enter new instructor: ");
                        instructor = scanner.nextLine();
                        System.out.print("Enter new schedule: ");
                        schedule = scanner.nextLine();
                        System.out.print("Enter new location: ");
                        location = scanner.nextLine();
                        Course updatedCourse = new Course(courseId, courseName, instructor, schedule, location);
                        updatedCourse.updateCourse();
                    }
                    break;

                case 4:
                    System.out.print("Enter course ID to delete: ");
                    courseId = scanner.nextInt();
                    scanner.nextLine();
                    Course.deleteCourseById(courseId);
                    break;

                case 5:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter major: ");
                    String major = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    Student newStudent = new Student(studentName, email, major, year);
                    newStudent.addStudent();
                    break;

                case 6:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    Student student = Student.getStudentById(studentId);
                    if (student != null) {
                        System.out.println(student);
                    }
                    break;

                case 7:
                    System.out.print("Enter student ID to update: ");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    student = Student.getStudentById(studentId);
                    if (student != null) {
                        System.out.print("Enter new student name: ");
                        studentName = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter new major: ");
                        major = scanner.nextLine();
                        System.out.print("Enter new year: ");
                        year = scanner.nextInt();
                        scanner.nextLine();
                        Student updatedStudent = new Student(studentId, studentName, email, major, year);
                        updatedStudent.updateStudent();
                    }
                    break;

                case 8:
                    System.out.print("Enter student ID to delete: ");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    Student.deleteStudentById(studentId);
                    break;

                case 9:
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextInt();
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter enrollment date (YYYY-MM-DD): ");
                    String enrollmentDate = scanner.nextLine();
                    Enrollment newEnrollment = new Enrollment(courseId, studentId, enrollmentDate);
                    newEnrollment.enrollStudent();
                    break;

                case 10:
                    System.out.print("Enter enrollment ID: ");
                    int enrollmentId = scanner.nextInt();
                    scanner.nextLine();
                    Enrollment enrollment = Enrollment.getEnrollmentById(enrollmentId);
                    if (enrollment != null) {
                        System.out.println(enrollment);
                    }
                    break;

                case 11:
                    System.out.print("Enter enrollment ID to update grade: ");
                    enrollmentId = scanner.nextInt();
                    scanner.nextLine();
                    enrollment = Enrollment.getEnrollmentById(enrollmentId);
                    if (enrollment != null) {
                        System.out.print("Enter new grade: ");
                        String newGrade = scanner.nextLine();
                        enrollment.updateEnrollmentGrade(newGrade);
                    }
                    break;

                case 12:
                    System.out.print("Enter enrollment ID to delete: ");
                    enrollmentId = scanner.nextInt();
                    scanner.nextLine();
                    Enrollment.deleteEnrollmentById(enrollmentId);
                    break;

                case 13:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 13);

        scanner.close();
    }
}
