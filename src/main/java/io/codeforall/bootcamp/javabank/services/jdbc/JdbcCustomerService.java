package io.codeforall.bootcamp.javabank.services.jdbc;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.services.CustomerService;

import java.sql.*;
import java.util.*;

public class JdbcCustomerService implements CustomerService {

    private AccountService accountService;
    private CustomerDao customerDao;

    private TransactionManager transactionManager;

    private SessionManager sessionManager;

    public JdbcCustomerService(CustomerDao customerDao,TransactionManager transactionManager, SessionManager sessionManager ) {
        this.customerDao = customerDao;
        this.transactionManager=transactionManager;
        this.sessionManager=sessionManager;
    }

    public void setCustomerDao(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Customer get(Integer id) {//com o id que nos lhe damos ele vai buscar o customer, associado a conta
        //como estamos perante uma operaçao de leitura nao precisamos de iniciar uma transaçao
        transactionManager.beginRead();
        Customer customer= customerDao.findById(id);

        sessionManager.stopSession();
        return customer;
    }

    @Override
    public List<Customer> list() {//vai retornar a lista dos customers

        transactionManager.beginRead();
        List <Customer>customerList= customerDao.findAll();
        sessionManager.stopSession();
        return customerList;

    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {//retorna a lista dos ids da conta de um customer

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        if (accounts.size() == 0) {
            return Collections.emptySet();
        }

        Set<Integer> customerAccountIds = new HashSet<>();

        for (Account account : accounts) {
            customerAccountIds.add(account.getId());
        }

        return customerAccountIds;
    }

    @Override
    public double getBalance(int id) {

        Customer customer = get(id);

        if (customer == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        List<Account> accounts = customer.getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }

    @Override
    public void add(Customer customer) {

        try{
            transactionManager.beginWrite();
            customerDao.add(customer);
            transactionManager.commit();
        }
        catch (SQLException e){
            transactionManager.rollback();

        }

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
