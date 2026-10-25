package com.example.employeemanager.service;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection: Spring automatically provides the repository here
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // 1. READ: Get all employees for the table
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. CREATE / UPDATE: Save a new employee or update an existing one
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // 3. READ: Get a single employee by ID (used when clicking the Edit button)
    public Employee getEmployeeById(Long id) {
        // If the ID doesn't exist, throw an error (we will handle this better later)
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
    }

    // 4. DELETE: Remove an employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // 5. DUPLICATE CHECK: Check if an email already exists (needed for the validation test)
    public boolean isEmailAlreadyRegistered(String email) {
        return employeeRepository.findByEmail(email).isPresent();
        
    }

    public List<Employee> getEmployeesByKeyword(String keyword) {
    if (keyword != null && !keyword.trim().isEmpty()) {
        return employeeRepository.findByFullNameContainingIgnoreCase(keyword.trim());
    }
    return employeeRepository.findAll();
}
}
