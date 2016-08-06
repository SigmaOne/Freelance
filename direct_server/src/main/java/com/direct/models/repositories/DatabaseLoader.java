package com.direct.models.repositories;

import com.direct.models.Customer;
import com.direct.models.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final CustomerDataRepository dataRepository;

    @Autowired
    public DatabaseLoader(CustomerRepository customerRepository, CustomerDataRepository dataRepository) {
        this.customerRepository = customerRepository;
        this.dataRepository = dataRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.customerRepository.save(new Customer("Customer1"));
        this.customerRepository.save(new Customer("Customer2"));

        this.dataRepository.save(new CustomerData("plug1", "plug2", "plug3", "plug4" ,"plug5" ,"plug6", "plug7", "plug8"));
        this.dataRepository.save(new CustomerData("plug9", "plug10", "plug11", "plug12" ,"plug13" ,"plug14", "plug15", "plug16"));
    }
}
