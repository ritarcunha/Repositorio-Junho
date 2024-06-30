package io.codeforall.bootcamp.javabank.controllers;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.viewers.LogInView;

import java.util.Set;

public class LogInController implements Controller{

    private LogInView logInView = new LogInView(this);
    private MenuController menuController;
    private Bank bank;

    public LogInController(Bank bank){
        this.bank = bank;
    }

    public Set<Integer> getCustomerIds() {
        return bank.getCustomerIds();
    }

    public void nextController(int customerId){
        menuController = new MenuController(bank, customerId);
        menuController.execute();
    }

    @Override
    public void execute() {
        logInView.show();
    }
}
