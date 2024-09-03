# University Course Management System

## Overview
This is a menu-based console application built in Core Java that simulates a university course management system. The system allows users to manage courses, students, and enrollments using MySQL for data storage and JDBC for database interactions.

## Features
- **Course Management**:
  - Add new courses to the university
  - View course details
  - Update course information
  - Delete courses from the system

- **Student Management**:
  - Register new students
  - View student details
  - Update student information
  - Delete student accounts

- **Enrollment Management**:
  - Enroll students in courses
  - View enrollment details
  - Update enrollment information
  - Withdraw students from courses


## Database Setup
1. Open MySQL Workbench or Command Line.
2. Execute the following SQL commands to set up the database:

   ```sql
   CREATE DATABASE university;

   USE university;

   CREATE TABLE Course (
       course_id INT AUTO_INCREMENT PRIMARY KEY,
       course_name VARCHAR(100),
       instructor VARCHAR(100),
       schedule VARCHAR(100),
       location VARCHAR(100)
   );

   CREATE TABLE Student (
       student_id INT AUTO_INCREMENT PRIMARY KEY,
       student_name VARCHAR(100),
       email VARCHAR(100),
       major VARCHAR(50),
       year INT
   );

   CREATE TABLE Enrollment (
       enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
       course_id INT,
       student_id INT,
       enrollment_date DATE,
       grade VARCHAR(5),
       FOREIGN KEY (course_id) REFERENCES Course(course_id),
       FOREIGN KEY (student_id) REFERENCES Student(student_id)
   );


Project Setup
1. Clone the Repository

2. Open in IDE:
   Open the project in your preferred IDE.

3. Configure Database Connection:

   Modify the DBConnection.java file to set your MySQL database URL, username, and    password.

   private static final String URL = "jdbc:mysql://localhost:3306/university";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";

4. Add MySQL Connector/J:

   Add the mysql-connector-java.jar to your project's classpath.

5. Run the Application:

   Run the CourseManagementSystem.java file.
   Use the menu options to manage courses, students, and enrollments.

