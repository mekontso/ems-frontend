package com.mas.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mas.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
