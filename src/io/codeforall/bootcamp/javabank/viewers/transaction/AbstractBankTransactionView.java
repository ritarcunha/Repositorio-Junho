package io.codeforall.bootcamp.javabank.viewers.transaction;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.AbstractBankController;
import io.codeforall.bootcamp.javabank.controllers.Controller;
import io.codeforall.bootcamp.javabank.controllers.transaction.AbstractBankTransactionController;
import io.codeforall.bootcamp.javabank.viewers.View;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public abstract class AbstractBankTransactionView implements View {

    AbstractBankTransactionController controller;

    public AbstractBankTransactionView(AbstractBankTransactionController controller){
        this.controller = controller;
    }

    protected int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(controller.getCustomer().getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    protected double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}
