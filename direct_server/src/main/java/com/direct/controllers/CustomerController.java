package com.direct.controllers;

import com.direct.models.Customer;
import com.direct.models.CustomerData;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @RequestMapping("/checkAccess")
    @ResponseBody
    public String postCustomer(@ModelAttribute Customer customer) {
        // Save customer
        return "{ access: true }";
    }

    @RequestMapping("/checkData")
    @ResponseBody
    public String postData(@ModelAttribute CustomerData data) {
        // Save data
        return "{ access: true }";
    }
}
