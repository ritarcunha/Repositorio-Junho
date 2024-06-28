package io.codeforall.bootcamp.javabank.application.operations.views;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.Account;
import io.codeforall.bootcamp.javabank.test.BankTest;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceView implements View {

    private DecimalFormat df = new DecimalFormat("#.##");
    protected BankApplication bankApplication;

    public BalanceView(BankApplication bankApplication){
        this.bankApplication = bankApplication;

    }

    @Override
    public Integer show() {
        Customer customer = bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
        return (int) customer.getBalance();
    }
}
