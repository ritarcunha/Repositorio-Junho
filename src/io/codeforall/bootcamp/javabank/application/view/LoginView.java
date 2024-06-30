package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.application.operations.Controllers.LoginController;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

public class LoginView implements View {


    private LoginController loginController;
    private Prompt prompt;

    public LoginView (LoginController loginController){
        this.loginController=loginController;
    }


    @Override
    public void show() {//pedido de login
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(loginController.getBank().getCustomerIds());//este metodo retorna uma lista e o inputset so permite continuar se tiver na lista
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        prompt.getUserInput(scanner);
        loginController.nextController();
        //chama o nextController
    }







}




