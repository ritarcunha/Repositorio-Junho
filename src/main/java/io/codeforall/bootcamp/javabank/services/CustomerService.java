package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.domain.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer get(Integer id);//vai retornar um Customer a partir do id
    List<Customer> list();//vai retornar uma lista de customers
    Set<Integer> listCustomerAccountIds(Integer id);
    double getBalance(int customerId);
    void add(Customer customer);


}
