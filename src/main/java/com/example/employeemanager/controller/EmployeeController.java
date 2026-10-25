package com.example.employeemanager.controller;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. READ: Show the form and the table (GET)
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String viewHomePage(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        // Add the list of employees to the page
         model.addAttribute("employees", employeeService.getEmployeesByKeyword(keyword));
        // Add an empty employee object for the Thymeleaf form to bind to
        model.addAttribute("keyword", keyword);
        model.addAttribute("employee", new Employee());
        return "employees"; //This will look for employees.html
    }

    // 2. CREATE: Save a new employee (POST)
    @PostMapping("/employees")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, 
                               BindingResult result, 
                               Model model) {
        
        // 1. Check for validation errors (empty fields, invalid email, etc.)
        if (result.hasErrors()) {
            // If there are errors, stay on the same page and show the errors
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "employees";
        }

        // 2. Check for duplicate email (from your lab sheet requirements)
        if (employeeService.isEmailAlreadyRegistered(employee.getEmail())) {
            result.rejectValue("email", "error.employee", "This email is already registered");
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "employees";
        }

        // 3. Save to database and redirect
        employeeService.saveEmployee(employee);
        return "redirect:/employees"; 
    }

    // 3. EDIT: Show the edit form filled with data (GET)
    @GetMapping("/employees/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit-employee"; // This will look for edit-employee.html
    }

    // 4. UPDATE: Save the edited employee (POST)
    @PostMapping("/employees/{id}/update")
    public String updateEmployee(@PathVariable("id") Long id, 
                                 @Valid @ModelAttribute("employee") Employee employee, 
                                 BindingResult result) {
        
        if (result.hasErrors()) {
            return "edit-employee";
        }

        // Ensure the ID is set correctly before updating
        employee.setId(id);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // 5. DELETE: Remove an employee (POST)
    @PostMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}