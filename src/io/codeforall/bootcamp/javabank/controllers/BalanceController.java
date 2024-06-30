package io.codeforall.bootcamp.javabank.controllers;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.viewers.BalanceView;

public class BalanceController extends AbstractBankController{

    private BalanceView balanceView = new BalanceView(this);

    public BalanceController(Bank bank, int customerId) {
        super(bank, customerId);
    }

    @Override
    public void execute() {
        balanceView.show();
    }

}
