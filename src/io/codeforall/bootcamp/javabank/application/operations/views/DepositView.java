package io.codeforall.bootcamp.javabank.application.operations.views;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.domain.Customer;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public class DepositView implements View {


    Prompt prompt = new Prompt(System.in, System.out);
    BankApplication bankApplication;
    Customer customer;

    public DepositView(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
    }

    @Override
    public Integer show() {
        customer = bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return null;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());
        return null;
    }

    protected boolean hasAccounts() {
        return customer.getAccountIds().size() > 0;
    }

    private String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : customer.getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    public int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    /**
     * Prompts the user for a transaction amount
     *
     * @return the amount to be transactioned
     */
    public double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }


}
