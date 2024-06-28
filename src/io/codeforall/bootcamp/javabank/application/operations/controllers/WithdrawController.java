package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;

public class WithdrawController implements Controller {

    BankApplication bankApplication;

    public WithdrawController(BankApplication bankApplication){
        this.bankApplication = bankApplication;
    }

    @Override
    public void execute() {

    }

    @Override
    public void nextController() {

    }
}
