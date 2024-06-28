package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;
import io.codeforall.bootcamp.javabank.application.operations.views.LoginView;
import io.codeforall.bootcamp.javabank.domain.Bank;

public class LoginController implements Controller {
    private Bank bank;
    private BankApplication bankApplication;
    LoginView loginView;
    MenuController menuController;


    public LoginController(BankApplication bankApplication){
        this.bank=bankApplication.getBank();
        this.bankApplication = bankApplication;
        this.loginView=new LoginView(bank);
        this.menuController = new MenuController(bankApplication);
    }

    @Override
    public void execute() {
        int id= loginView.show();
        bankApplication.setAccessingCustomerId(id);
    }

    @Override
    public void nextController() {
        menuController.execute();
    }
}
