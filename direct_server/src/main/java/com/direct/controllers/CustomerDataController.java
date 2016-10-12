package com.direct.controllers;

import com.direct.models.CustomerData;
import com.direct.models.repositories.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Controller
public class CustomerDataController {
    @Autowired private CustomerDataRepository dataRepository;

    @ResponseBody
    @RequestMapping(value = "/checkData", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public String postData(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CustomerData data) {
        if (data.isValid() && isBasicAuthRight(authorizationHeader)) {
            data.setCreationDate(new Date());
            dataRepository.save(data);
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
                  dataRepository.delete(id);
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
