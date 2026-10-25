package com.example.employeemanager.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity 
@Table (name = "employees")
@Getter 
@Setter 
@NoArgsConstructor 
public class Employee {
    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(unique = true)
    private String email; 

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;   


}
