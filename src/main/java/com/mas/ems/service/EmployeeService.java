package com.mas.ems.service;

import java.util.List;

import com.mas.ems.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updaEmployee(Long id, EmployeeDto employeeDto);

    String deleteEmployee(Long id);
}
