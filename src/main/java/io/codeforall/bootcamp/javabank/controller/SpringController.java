package io.codeforall.bootcamp.javabank.controller;


import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringController {

    //Vai invocar o metodo da logica de negocio que me interessa
    //vai retornar a view que vai ter a resposta
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String displayCustomer (Model model) {
        CustomerServiceImpl customerService= new CustomerServiceImpl();
        customerService.customerList();

        model.addAttribute("customerList", customerService.customerList());
        return "index";

    }

}
