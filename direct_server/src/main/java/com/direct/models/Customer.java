package com.direct.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Id @GeneratedValue private Long id;
    private Date created_at;
    private String name;

    Customer() {} // Jpa only
    public Customer(String name) {
        this.created_at = new Date();

        this.name = name;
    }

    public void setCreationDate(Date date) {
        this.created_at = date;
    }
}
