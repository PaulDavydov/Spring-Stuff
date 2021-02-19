package com.example.restservices;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    private @Id @GeneratedValue Long id;
    private String description;
    private Status status;

    Order() {}

    Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return this.status;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Order)) {
            return false;
        }
        Order newO = (Order) o;
        return Objects.equals(this.id, newO.id) && Objects.equals(this.description,newO.description)
                && this.status == newO.status;
    }

    public int hashCode() {
        return Objects.hash(this.id,this.description,this.status);
    }

    public String toString() {
        return "Order{" + "id=" + this.id + ", description='" +
                this.description + '\'' + ", status=" + this.status + ')';
    }
}
