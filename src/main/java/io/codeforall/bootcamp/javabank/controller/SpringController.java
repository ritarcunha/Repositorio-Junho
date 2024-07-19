package io.codeforall.bootcamp.javabank.controller;


import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SpringController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String displayUser(Model model) {
        CustomerServiceImpl customerService= new CustomerServiceImpl();
        customerService.customerList();
        model.addAttribute("customerList",customerService.customerList() );
        return "index";

    }






}
