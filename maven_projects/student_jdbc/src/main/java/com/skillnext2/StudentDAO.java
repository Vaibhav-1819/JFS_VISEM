package com.skillnext2;
import java.sql.*;
import java.util.*;
public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/skillnext2_db";
    private static final String USER = "root";
    private static final String PASSWORD = "V@ibhav18";
    // Add student
    public void addStudent(Student student) throws Exception {
        String sql = "INSERT INTO student (name, sem, department) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getSem());
            stmt.setString(3, student.getDepartment());
            stmt.executeUpdate();
        }
    }
    // Fetch all students
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setSem(rs.getInt("sem"));
                s.setDepartment(rs.getString("department"));
                list.add(s);
            }
        }
        return list;
    }
    // Delete student
    public void deleteStudent(int id) throws Exception {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    // Update student
    public void updateStudent(Student student) throws Exception {
        String sql = "UPDATE student SET name=?, sem=?, department=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getSem());
            stmt.setString(3, student.getDepartment());
            stmt.setInt(4, student.getId());
            stmt.executeUpdate();
        }
    }
}
