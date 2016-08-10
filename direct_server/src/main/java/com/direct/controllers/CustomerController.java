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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
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
    public void postLogin(@ModelAttribute(value = "user") User user, HttpServletResponse response) throws IOException {
        if(isAdmin(user)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", String.format("inline; filename=\"customerData.xls\""));

            HSSFWorkbook workbook = xlsCreator.createXlsWithAllEntities();
            workbook.write(response.getOutputStream());
        } else {
            response.setStatus(401);
            response.getOutputStream().write(("redirect:" + "sht.com").getBytes());
        }
    }

    @RequestMapping(value = "/checkAccess", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String postCustomer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Customer customer) {
        // Todo: refactor those 2 lanes
        if (customer.isValid() && isBasicAuthRight(authorizationHeader)) {
            customer.setCreationDate(new Date());
            customerRepository.save(customer);

            return "{ access: 'granted' }";
        } else {
            return "{ access: 'denied' }";
        }
    }

    @RequestMapping(value = "/checkData", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String postData(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CustomerData data) {
        // Todo: refactor those 2 lanes
        if (data.isValid() && isBasicAuthRight(authorizationHeader)) {
            data.setCreationDate(new Date());
            dataRepository.save(data);
            return "{ access: 'granted' }";
        } else {
            return "{ access: 'denied' }";
        }
    }

    private boolean isAdmin(User user) {
        return user.getName().equals(System.getProperty("DIRECT_ADMIN_NAME")) && user.getPassword().equals(System.getProperty("DIRECT_ADMIN_PASSWORD"));
    }
    private boolean isBasicAuthRight(String authorizationHeader) {
        String decodedToken = (System.getProperty("DIRECT_ADMIN_NAME") + ":" + System.getProperty("DIRECT_ADMIN_PASSWORD"));
        String encodedToken = DatatypeConverter.printBase64Binary(decodedToken.getBytes());

        return authorizationHeader.equals("Basic " + encodedToken);
    }
}