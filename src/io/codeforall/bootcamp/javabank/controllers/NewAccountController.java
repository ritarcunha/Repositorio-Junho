package io.codeforall.bootcamp.javabank.controllers;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.viewers.NewAccountView;

public class NewAccountController extends AbstractBankController{

    private NewAccountView newAccountView = new NewAccountView(this);

    public NewAccountController(Bank bank, int customerId){
        super(bank, customerId);
    }

    @Override
    public void execute() {
        newAccountView.show();
    }
}
