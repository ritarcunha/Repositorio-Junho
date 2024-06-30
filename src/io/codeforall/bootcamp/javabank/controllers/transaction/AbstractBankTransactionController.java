package io.codeforall.bootcamp.javabank.controllers.transaction;

import io.codeforall.bootcamp.javabank.controllers.AbstractBankController;
import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.managers.AccountManager;

public abstract class AbstractBankTransactionController extends AbstractBankController {


    public AbstractBankTransactionController(Bank bank, int customerId) {
        super(bank, customerId);
    }

    public AccountManager getAccountManager() {
        return bank.getAccountManager();
    }

    public boolean hasAccounts() {
        return bank.getCustomer(customerId).getAccountIds().size() > 0;
    }

    public String buildAccountList() {
        StringBuilder builder = new StringBuilder();

        for (Integer id : bank.getCustomer(customerId).getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

}
