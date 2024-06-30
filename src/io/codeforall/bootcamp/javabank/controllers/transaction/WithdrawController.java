package io.codeforall.bootcamp.javabank.controllers.transaction;

import io.codeforall.bootcamp.javabank.controllers.Controller;
import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import io.codeforall.bootcamp.javabank.viewers.transaction.WithdrawView;

public class WithdrawController extends AbstractBankTransactionController{

    private WithdrawView withdrawView = new WithdrawView(this);

    public WithdrawController(Bank bank, int customerId){
        super(bank, customerId);
    }

    @Override
    public void execute() {
        withdrawView.show();
    }

}
