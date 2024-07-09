package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.persistence.TransactionException;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.util.*;

public class CustomerServiceImpl implements CustomerService {

    private TransactionManager tm;
    private CustomerDao customerDao;


    public void setCustomerDAO(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setTm(TransactionManager tm) {
        this.tm = tm;
    }


    @Override
    public Customer get(Integer id) {

        try {
            tm.beginRead();
            return customerDao.findById(id);
        } finally {
            tm.commit();
        }

    }

    @Override
    public List<Customer> list() {

        try {
            tm.beginRead();
            return customerDao.findAll();
        } finally {
            tm.commit();
        }
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        if (accounts.size() == 0) {
            return Collections.emptySet();
        }

        Set<Integer> customerAccountIds = new HashSet<>();

        for (Account account : accounts) {
            customerAccountIds.add(account.getId());
        }

        return customerAccountIds;
    }

    @Override
    public double getBalance(int id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {

        try {
            tm.beginWrite();
            customerDao.saveOrUpdate(customer);
            tm.commit();
        } catch (TransactionException e){
            tm.rollback();
        }

    }


}
