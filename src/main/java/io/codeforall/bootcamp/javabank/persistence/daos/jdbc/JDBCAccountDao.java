package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.TransactionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class JDBCAccountDao extends JDBCGenericDao<Account> implements AccountDao {

    private JDBCSessionManager sm;

    public JDBCAccountDao() {
        super(Account.class);
    }

    public void setConnectionManager(JDBCSessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new LinkedList<>();

        try {
            String query = "SELECT * FROM account";
            Statement statement = sm.getCurrentSession().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                Account account = AccountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setVersion(resultSet.getInt("version"));
                account.credit(resultSet.getInt("balance"));

                accounts.add(account);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Account account = null;

        try {

            String query = "SELECT id, account_type, customer_id, balance, version FROM account WHERE id=?";
            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AccountType accountType = AccountType.valueOf(resultSet.getString("account_type"));

                account = AccountFactory.createAccount(accountType);
                account.setId(resultSet.getInt("id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setVersion(resultSet.getInt("version"));
                account.credit(resultSet.getInt("balance"));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account saveOrUpdate(Account modelObject) {
        try {

            Integer id = null;

            if (modelObject.getId() != null && (modelObject.getId()) != null) {
                id = update(modelObject);
            } else {
                id = insert(modelObject);
            }

            modelObject.setId(id);
            return modelObject;

        } catch (SQLException e) {
            throw new TransactionException();
        }
    }

    @Override
    public void delete(Integer id) {

        try {

            String query = "DELETE FROM account WHERE id = ?";

            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new TransactionException();
        }

    }

    private Integer update(Account account) throws SQLException {


        String query = "UPDATE account SET balance = ?, version = ? WHERE id = ?";

        PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

        statement.setDouble(1, account.getBalance());
        statement.setInt(2, account.getVersion() + 1);
        statement.setInt(3, account.getId());

        statement.executeUpdate();
        statement.close();

        return account.getId();

    }


    private Integer insert(Account account) throws SQLException {


        String query = "INSERT INTO account(account_type, balance, customer_id) " +
                "VALUES (?, ?, ?)";

        PreparedStatement statement = sm.getCurrentSession().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, account.getAccountType().name());
        statement.setDouble(2, account.getBalance());
        statement.setInt(3, account.getCustomerId());

        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if (generatedKeys.next()) {
            account.setId(generatedKeys.getInt(1));
        }

        statement.close();
        return account.getId();

    }

}
