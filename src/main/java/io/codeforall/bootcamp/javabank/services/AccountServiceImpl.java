package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.ConnectionManager;
import io.codeforall.bootcamp.javabank.model.account.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    private ConnectionManager dbConnection;

    private Map<Integer, Account> accountMap = new HashMap<>();

    /**
     * Gets the next account id
     *
     * @return the next id
     */
    private Integer getNextId() {
        return accountMap.isEmpty() ? 1 : Collections.max(accountMap.keySet()) + 1;
    }

    /**
     * @see AccountService#add(Account)
     */
    public void add(Account account) throws SQLException {

        try {//se conseguir faz commit

            Statement statement = dbConnection.getConnection().createStatement();//classe de java que representa a query

            String query= "INSERT INTO customers (customer_name, customer_id, number_of_acounts, version) VALUES ('Manel', 1,1,1)";

            ResultSet resultSet = statement.executeQuery(query);
        }
        catch (){//se nao conseguir faz rolback
            dbConnection.getConnection().rollback();
        }


        //se fizer autocommit ele automaticamente faz start connection
        //falta o autocommit
        //o commit faz com o estado da BD seja alterado apos toda as operaçoes serem feitas







        if (account.getId() == null) {
            account.setId(getNextId());
        }

        accountMap.put(account.getId(), account);
    }



    /**
     * @see AccountService#deposit(int, double)
     */
    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    /**De
     * @see AccountService#withdraw(int, double)
     */
    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    /**
     * @see AccountService#transfer(int, int, double)
     */
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
