package com.example.restservices;

import org.springframework.data.jpa.repository.JpaRepository;
// Defines the repository bean
//manages data
interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
