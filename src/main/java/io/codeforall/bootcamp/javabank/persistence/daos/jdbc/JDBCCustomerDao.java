package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JDBCCustomerDao implements CustomerDao {//DAO faz a ligaçao entre java e sql

    private SessionManager sessionManager;
    private AccountDao accountDao;

    public void setSessionManager(SessionManager sessionManager){
        this.sessionManager=sessionManager;
    }

    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    @Override
    public List<Customer> findAll() {

        Map<Integer, Customer> customers = new HashMap<>();

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, customer.version as cVersion, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id";

            PreparedStatement statement = sessionManager.getCurrentSession().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (!customers.containsKey(resultSet.getInt("cid"))) {
                    Customer customer = buildCustomer(resultSet);
                    customers.put(customer.getId(), customer);
                }

                Account account = accountDao.findById(resultSet.getInt("aid"));//este metodo inicialmente ia buscar a base de dados pelo accountid a conta, agora temos de ir buscar pelo metodo findby id
                if (account != null) {
                    customers.get(resultSet.getInt("cid")).addAccount(account);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LinkedList<>(customers.values());

    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;

        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, customer.version AS cVersion, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id " +
                    "WHERE customer.id = ?";

            PreparedStatement statement = sessionManager.getCurrentSession().prepareStatement(query);// tamos a buscar a coneçao q ja foi criada no service
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (customer == null) {
                    customer = buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");
                Account account = accountDao.findById(accountId);

                if (account == null) {
                    break;
                }

                customer.addAccount(account);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public void add(Customer customer) throws SQLException {

        if (customer.getId() != null && findById(customer.getId()) != null) {
            return;
        }

        Connection connection = sessionManager.getCurrentSession();


            connection.setAutoCommit(false);

            String query = "INSERT INTO customer(first_name, last_name, email, phone) " +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }

            statement.close();






    }

    @Override
    public Customer saveOrUpdate(Customer user) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Customer findByUsername(String username) {
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        return null;
    }

    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setVersion(resultSet.getInt("cVersion"));

        return customer;
    }
}
