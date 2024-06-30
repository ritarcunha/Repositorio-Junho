package io.codeforall.bootcamp.javabank.viewers.transaction;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.transaction.DepositController;
import io.codeforall.bootcamp.javabank.viewers.View;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public class DepositView extends AbstractBankTransactionView{

    public DepositView(DepositController depositController) {
        super(depositController);
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
            controller.getAccountManager().deposit(accountId, amount);
        }
    }
}
