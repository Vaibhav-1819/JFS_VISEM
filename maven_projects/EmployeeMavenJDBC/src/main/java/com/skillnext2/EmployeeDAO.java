package com.skillnext2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    // Insert employee
    public void addEmployee(String name, String department, double salary) {

        String sql = 
            "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, salary);

            ps.executeUpdate();
            System.out.println("Employee added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View employees
    public void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getString("department") + " " +
                    rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
