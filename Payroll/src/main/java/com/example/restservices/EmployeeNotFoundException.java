package com.example.restservices;

// Error message for when not finding a employee that we may be looking for.
public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long id) {
        super("Could not find employee by id: " + id);
    }
}
