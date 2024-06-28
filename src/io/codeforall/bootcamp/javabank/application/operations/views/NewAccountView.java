package io.codeforall.bootcamp.javabank.application.operations.views;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;

public class NewAccountView implements View {
    BankApplication bankApplication;
    Customer customer;

    public NewAccountView (BankApplication bankApplication){
        this.bankApplication=bankApplication;
    }


    @Override
    public Integer show() {
        customer= bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
        int accountId = customer.openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
        return accountId;

    }
}
