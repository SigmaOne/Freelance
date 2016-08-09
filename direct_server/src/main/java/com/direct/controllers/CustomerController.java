package com.direct.controllers;

import com.direct.models.Customer;
import com.direct.models.CustomerData;
import com.direct.models.User;
import com.direct.models.XlsCreator;
import com.direct.models.repositories.CustomerDataRepository;
import com.direct.models.repositories.CustomerRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
public class CustomerController {
    @Autowired private CustomerRepository customerRepository;
    @Autowired private CustomerDataRepository dataRepository;
    @Autowired private XlsCreator xlsCreator;

    @RequestMapping("/login")
    public String login(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void postLogin(@ModelAttribute(value = "user") User user, HttpServletResponse response) throws IOException {
        if(isAdmin(user)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", String.format("inline; filename=\"customerData.xls\""));

            HSSFWorkbook workbook = xlsCreator.createXlsWithAllEntities();
            workbook.write(response.getOutputStream());
        } else {
            response.getOutputStream().write("{ access: denied }".getBytes());
        }
    }
    private boolean isAdmin(User user) {
        return user.getName().equals(System.getenv("DIRECT_ADMIN_NAME")) && user.getPassword().equals(System.getenv("DIRECT_ADMIN_PASSWORD"));
    }

    @RequestMapping(value = "/checkAccess", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String postCustomer(@RequestBody Customer customer) {
        // Todo: refactor those 2 lanes
        if (customer.isValid()) {
            customer.setCreationDate(new Date());
            customerRepository.save(customer);

            return "{ access: true }";
        } else {
            return "{ access: false }";
        }
    }

    @RequestMapping(value = "/checkData", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String postData(@RequestBody CustomerData data) {
        // Todo: refactor those 2 lanes
        if (data.isValid()) {
            data.setCreationDate(new Date());
            dataRepository.save(data);
            return "{ access: true }";
        } else {
            return "{ access: false }";
        }
    }
}
