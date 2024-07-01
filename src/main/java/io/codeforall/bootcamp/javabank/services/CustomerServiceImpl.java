package io.codeforall.bootcamp.javabank.services;


import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.*;

public class CustomerServiceImpl implements  CustomerService{
    private HashMap<Integer, Customer> customers;
    private Map<Integer, Account> accounts;

    private LinkedList list;

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
        this.list=new LinkedList<>(customers.values());
        return list;
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
