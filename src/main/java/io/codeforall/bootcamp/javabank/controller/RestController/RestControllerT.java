package io.codeforall.bootcamp.javabank.controller.RestController;


import io.codeforall.bootcamp.javabank.converters.CustomerToCustomerDto;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestControllerT {

    CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(
                method = RequestMethod.GET,
                value = "api/customers",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity customerList () {
            CustomerToCustomerDto customerToCustomerDto= new CustomerToCustomerDto();

            return new ResponseEntity<>(customerToCustomerDto.convert(customerService.list()),HttpStatus.OK);//vai retonar a lista e o pedido ok

    }
}
