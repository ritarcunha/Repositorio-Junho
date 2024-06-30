package io.codeforall.bootcamp.javabank.application.operations.Controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.operations.Controller;
import io.codeforall.bootcamp.javabank.application.view.LoginView;
import io.codeforall.bootcamp.javabank.application.view.MenuView;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

public class LoginController implements Controller {
    private LoginView loginView;
    private MenuController menuController;

    private Bank bank;
    private Prompt prompt;


    public LoginController(Bank bank){
        this.loginView=new LoginView(this);
        this.menuController=new MenuController(bank);//posso criar novos mas todos vao buscar a instancia de bank
        this.bank=bank;
    }

    public Bank getBank(){
        return this.bank;
    }

    @Override
    public void execute() {
        loginView.show();
    }

    @Override
    public void nextController() {
        menuController.execute();

    }


}
