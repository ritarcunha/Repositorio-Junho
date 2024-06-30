package io.codeforall.bootcamp.javabank.viewers;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.NewAccountController;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;

public class NewAccountView implements View{

    private NewAccountController newAccountController;

    public NewAccountView(NewAccountController newAccountController){
        this.newAccountController = newAccountController;
    }

    @Override
    public void show() {
        int accountId = newAccountController.getCustomer().openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + newAccountController.getCustomer().getName() + " : " + accountId);
    }
}
