package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;
import io.codeforall.bootcamp.javabank.application.operations.views.BalanceView;
import io.codeforall.bootcamp.javabank.domain.Customer;

public class BalanceController implements Controller {

    BankApplication bankApplication;
    BalanceView balanceView;

    public BalanceController(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        Customer customer = bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
        this.balanceView = new BalanceView(bankApplication);
    }

    @Override
    public void execute() {
        balanceView.show();
    }

    @Override
    public void nextController() {

    }
}
