package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;

import java.sql.*;
import java.util.List;

public class JDBCAccountDao implements AccountDao {

    private SessionManager sessionManager;
    private CustomerDao customerDao;
    private AccountFactory accountFactory;

    @Override
    public void setAccountFactory(AccountFactory accountFactory){
        this.accountFactory = accountFactory;
    }

    @Override
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account findById(Integer id) {
        Connection connection = sessionManager.getCurrentSession();
        Account account = null;

        try {

            String query = "SELECT id, account_type, customer_id, balance, version FROM account WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                account = accountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.credit(resultSet.getInt("balance"));
                account.setVersion(resultSet.getInt("version"));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account saveOrUpdate(Account account) throws SQLException {
        String query = "UPDATE account SET balance = ?, version = ? WHERE id = ?";

        PreparedStatement statement = sessionManager.getCurrentSession().prepareStatement(query);

        statement.setDouble(1, account.getBalance());
        statement.setInt(2, account.getVersion() + 1);
        statement.setInt(3, account.getId());

        statement.executeUpdate();
        statement.close();

        return account; //como estou a atualizar esta conta basta retornar a conta que estou a atualizar
    }


    @Override
    public void delete(Integer id) {

    }

    @Override
    public void add(Account account) throws SQLException {

        Connection connection = sessionManager.getCurrentSession();
        PreparedStatement statement = null;



            String query = "INSERT INTO account(account_type, balance, customer_id) " +
                    "VALUES (?, ?, ?)";

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, account.getAccountType().name());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getCustomerId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            }

            statement.close();



        }



    }

