package com.example.restservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    //Initializes the database and adds a few elements to the database
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeerepository,OrderRepository orderRepository) {
        return args -> {
            employeerepository.save(new Employee("Bilbo", "Baggins",
                    "burglar"));
            employeerepository.save(new Employee("Frodo", "Baggins",
                    "thief"));
            employeerepository.findAll().forEach(employee->log.info("Preloaded " + employee));

            orderRepository.save(new Order("Macbook Pro", Status.COMPLETED));
            orderRepository.save(new Order("IPhone MAX 6",Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
