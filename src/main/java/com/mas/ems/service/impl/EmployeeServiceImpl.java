package com.mas.ems.service.impl;

import org.springframework.stereotype.Service;

import com.mas.ems.dto.EmployeeDto;
import com.mas.ems.entity.Employee;
import com.mas.ems.exception.ResourceNotFoundException;
import com.mas.ems.mapper.EmployeeMapper;
import com.mas.ems.repository.EmployeeRepository;
import com.mas.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Convert DTO to Entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // Save the entity in the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Convert the saved entity back to DTO and return
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Employee id cannot be null");
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto updaEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        } else {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            Employee updatedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
        }
    }

    @Override
    public String deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }
}
