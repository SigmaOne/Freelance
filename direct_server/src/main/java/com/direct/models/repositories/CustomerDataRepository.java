package com.direct.models.repositories;

import org.springframework.data.repository.CrudRepository;
import com.direct.models.CustomerData;

public interface CustomerDataRepository extends CrudRepository<CustomerData, Long> {
}
