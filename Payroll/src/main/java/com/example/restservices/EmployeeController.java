package com.example.restservices;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import java.util.List;
import java.util.stream.Collectors;

//RestController automatically annotates the @ResponseBody, no need to write it
@RestController
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;
    // Constructor that needs the repo and assembler parameters
    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    // Maps HTTP Get requests onto a specific handler method, for example all().
    @GetMapping("/employees")
    /* This method returns all the employees located in our database. It creates a
    * list that holds them all, and the CollectionModel easily encapsulates
    * that list. The link is mapped to "/employees".*/
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(employees,linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    // Tells the server that the data sent is stored in the request body of the HTTP request.
    // Updates database at employees link.
    @PostMapping("/employees")
    // I AM CURRENTLY WORKING ON THIS
    public ResponseEntity<?> addEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
    }

//    @GetMapping("/employees/{id}")
//    Employee one(@PathVariable Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//    }
    // At the link of the employee ID
    @GetMapping("/employees/{id}")
    /* Similarly to the all() method, this time we find the employee with the
    * given id and reaturn it with the link for it as well.*/
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    // Updates employee based on link
    @PutMapping("/employees/{id}")
    /* here we are editing the employee that we want based on the ID we provide.
    * here we set the roles and name based on the "new employee" that we are providing.
    * We also wrap the Employee object into a EntityModel using the assembler we defined.
    * Then we grab the link for it that was created from the assembler.*/
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee,@PathVariable Long id) {
        Employee updatedEmployee = repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return  repository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
    // Just deletes the employee based on the ID we provide. Returns a 204 no response.
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
