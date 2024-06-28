package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;
import io.codeforall.bootcamp.javabank.application.operations.views.DepositView;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.managers.AccountManager;

public class DepositController  implements Controller {

    BankApplication bankApplication;
    AccountManager accountManager;
    DepositView depositView;
    Customer customer;

    public DepositController(BankApplication bankApplication){
        this.bankApplication = bankApplication;
        depositView = new DepositView(bankApplication);
        accountManager = accountManager = bankApplication.getBank().getAccountManager();
    }

    @Override
    public void execute() {
        customer =  bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
        depositView.show();
        Integer accountId = depositView.scanAccount();
        Double amount = depositView.scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.deposit(accountId, amount);
        }

    }

    @Override
    public void nextController() {

    }
}
