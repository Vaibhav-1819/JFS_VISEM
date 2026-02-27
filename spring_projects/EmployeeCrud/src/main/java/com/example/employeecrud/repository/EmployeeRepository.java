package com.example.employeecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeecrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	

}