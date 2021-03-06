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

    @ResponseBody
    @RequestMapping(value = "/checkAccess", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public String postCustomer(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Customer customer) {
        // Todo: refactor those 2 lanes
        if (customer.isValid() && isBasicAuthRight(authorizationHeader)) {
            customer.setCreationDate(new Date());
            customerRepository.save(customer);

            return "{ \"access\": \"granted\" }";
        } else {
            return "{ \"access\": \"denied\" }";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestHeader("Authorization") String authorizationHeader, long id) {
        if (isBasicAuthRight(authorizationHeader)) {
            try {
                  customerRepository.delete(id);
                  return "{ \"access\": \"granted\" }";
            }
            catch (Exception ex) {
                return "{ \"access\": \"denied\"\n\"error\": \"" + ex.toString() + "\" }";
            }
        } else {
            return "{ \"access\": \"denied\" }";
        }
    }

    private boolean isBasicAuthRight(String authorizationHeader) {
        String decodedToken = (System.getProperty("DIRECT_ADMIN_NAME") + ":" + System.getProperty("DIRECT_ADMIN_PASSWORD"));
        String encodedToken = DatatypeConverter.printBase64Binary(decodedToken.getBytes());

        return authorizationHeader.equals("Basic " + encodedToken);
    }
}
