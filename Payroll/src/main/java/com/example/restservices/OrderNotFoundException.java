package com.example.restservices;

public class OrderNotFoundException extends RuntimeException{
    OrderNotFoundException(Long id) {
        super("Could not find order by id: " + id);
    }
}
