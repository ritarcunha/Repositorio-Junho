package io.codeforall.bootcamp.javabank.application.operations.views;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.domain.Bank;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

public class LoginView implements View {

    Bank bank= new Bank();
    Prompt prompt= new Prompt(System.in,System.out);

    public LoginView (Bank bank){
        this.bank=bank;
    }

    @Override
    public Integer show() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());//o fscto do scanner receber como argumento o bank.getCustomerIds() faz com que nao tenhamos que fazer a verificacao
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);

    }
}
