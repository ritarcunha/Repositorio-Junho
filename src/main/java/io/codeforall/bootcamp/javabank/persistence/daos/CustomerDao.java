package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {


    // basic crud methods
    void setSessionManager(SessionManager sessionManager);
    void setAccountDao(AccountDao accountDao);
    List<Customer> findAll();
    Customer findById(Integer id);
    void add(Customer customer) throws SQLException;
    Customer saveOrUpdate(Customer user);
    void delete(Integer id);

    // additional methods
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}