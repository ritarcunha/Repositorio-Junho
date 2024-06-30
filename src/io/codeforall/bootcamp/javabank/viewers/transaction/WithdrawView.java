package io.codeforall.bootcamp.javabank.viewers.transaction;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.transaction.WithdrawController;
import io.codeforall.bootcamp.javabank.viewers.View;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public class WithdrawView extends AbstractBankTransactionView{

    public WithdrawView(WithdrawController withdrawController) {
        super(withdrawController);
    }

    @Override
    public void show() {
        if (!controller.hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }
        System.out.println("\n" + Messages.OPEN_ACCOUNTS + controller.buildAccountList());

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (controller.getCustomer().getAccountIds().contains(accountId)) {
            controller.getAccountManager().withdraw(accountId, amount);
        }
    }
}
