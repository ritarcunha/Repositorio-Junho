package io.codeforall.bootcamp.javabank.controllers;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;

import java.util.Set;

public abstract class AbstractBankController implements Controller{

    protected Bank bank;
    protected int customerId;


    public AbstractBankController(Bank bank, int customerId){
        this.bank = bank;
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return bank.getCustomer(customerId);
    }

    public Set<Integer> getCustomerIds() {
        return bank.getCustomerIds();
    }

}
