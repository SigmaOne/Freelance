package com.direct.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id @GeneratedValue private Long id;
    private Date creationDate;
    private String name;

    Customer() {} // Jpa only

    public Customer(String name) {
        this.creationDate = new Date();

        this.name = name;
    }

    public boolean isValid() {
        return name != null;
    }
}
