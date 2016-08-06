package com.direct.controllers;

import com.direct.models.Customer;
import com.direct.models.CustomerData;
import com.direct.models.User;
import com.direct.models.repositories.CustomerDataRepository;
import com.direct.models.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerDataRepository dataRepository;

    @RequestMapping("/login")
    public String login(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void postLogin(/*@ModelAttribute(value = "user") User user, */HttpServletResponse response) throws IOException {
        File file = new File("/home/sigma/Furnace/test.xls");

        response.setContentLength((int)file.length());
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/checkAccess", method = RequestMethod.POST)
    @ResponseBody
    public String postCustomer(@ModelAttribute Customer customer) {
        // Save customer
        return "{ access: true }";
    }

    @RequestMapping(value = "/checkData", method = RequestMethod.POST)
    @ResponseBody
    public String postData(@ModelAttribute CustomerData data) {
        // Save data
        return "{ access: true }";
    }
}
