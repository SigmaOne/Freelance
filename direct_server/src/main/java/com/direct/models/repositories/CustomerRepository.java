package com.direct.models.repositories;

import org.springframework.data.repository.CrudRepository;
import com.direct.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
