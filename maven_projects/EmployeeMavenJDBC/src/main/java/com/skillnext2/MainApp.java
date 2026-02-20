package com.skillnext2;

public class MainApp {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        dao.addEmployee("Ram", "IT", 45000);
        dao.addEmployee("Suresh", "HR", 38000);

        System.out.println("New Department entries added");
    }
}
