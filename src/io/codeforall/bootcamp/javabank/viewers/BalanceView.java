package io.codeforall.bootcamp.javabank.viewers;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.BalanceController;
import io.codeforall.bootcamp.javabank.domain.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View{

    private BalanceController balanceController;
    private DecimalFormat df = new DecimalFormat("#.##");

    public BalanceView(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    @Override
    public void show() {
        System.out.println("\n" + balanceController.getCustomer().getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = balanceController.getCustomer().getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(balanceController.getCustomer().getBalance()));
    }
}
