package com.skillnext2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    // Register user
    public void register(String username, String password) {

        String sql =
            "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();
            System.out.println("User registered successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login validation
    public boolean login(String username, String password) {

        String sql =
            "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if login success

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
