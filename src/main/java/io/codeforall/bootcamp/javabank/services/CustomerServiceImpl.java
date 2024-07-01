package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerServiceImpl implements  CustomerService{
    private HashMap<Integer, Customer> customers;
    private Map<Integer, Account> accounts;

    public CustomerServiceImpl(){
        this.customers=customers;
        this.accounts=new HashMap<>();
    }

    @Override
    public Customer get(Integer id) {//retorna o Customer com aquele id especifico
        return customers.get(id);
    }

    @Override
    public List<Customer> list() {
        return null;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return customers.keySet();
    }

    @Override
    public double getBalance(int customerId) {
        return accounts.get(customerId).getBalance();
    }

    @Override
    public void add(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
