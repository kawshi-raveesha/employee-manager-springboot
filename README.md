# employee-manager-springboot

# Employee Management System

A Spring Boot CRUD application to manage employee records, connected to a MySQL database. It uses Lombok to reduce boilerplate code and Jakarta Validation to enforce data rules.

## Prerequisites
* Java 17 or higher
* MySQL (or XAMPP running the MySQL module)
* Maven

## How to Run
1. Ensure your MySQL server is running.
2. Clone this repository.
3. Create a `secrets.properties` file in the root directory (refer to `secrets.properties.example`) and add your MySQL username and password.
4. Run the application using your IDE or the terminal:
   ```bash
   ./mvnw spring-boot:run
   