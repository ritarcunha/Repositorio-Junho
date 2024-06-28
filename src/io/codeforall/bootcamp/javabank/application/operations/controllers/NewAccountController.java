package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;
import io.codeforall.bootcamp.javabank.application.operations.views.NewAccountView;

public class NewAccountController implements Controller {

   NewAccountView newAccountView;
    BankApplication bankApplication;

    public NewAccountController(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        this.newAccountView = new NewAccountView(bankApplication);
    }

    @Override
    public void execute() {
        newAccountView.show();

    }

    @Override
    public void nextController() {

    }
}
