package io.codeforall.bootcamp.javabank.controllers.transaction;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.viewers.transaction.DepositView;

public class DepositController extends AbstractBankTransactionController{

    private DepositView depositView = new DepositView(this);

    public DepositController(Bank bank, int customerId){
        super(bank, customerId);
    }

    @Override
    public void execute() {
        depositView.show();
    }

}
