package com.example.restservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

// A POGO, meant to be help in a database
@Entity
class Employee {
    // Various attributes of a Employee, ID is unique
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;
    //Empty constructor
    public Employee() {}
    //Adding a existing employee
    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }
    public String getRole() {
        return this.role;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String Role) {
        this.role = role;
    }
    // Comparing the various employees to see if they are the same or not
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if(!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(this.id,employee.id) && Objects.equals(this.firstName,employee.firstName) &&
                Objects.equals(this.lastName,employee.lastName) && Objects.equals(this.role,employee.role);
    }
    //Creates/returns a hashcode for the employee
    public int hashCode() {
        return Objects.hash(this.id,this.firstName,this.lastName,this.role);
    }

    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
