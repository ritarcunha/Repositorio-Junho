package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {

    // basic crud methods
    void setAccountFactory(AccountFactory accountFactory);
    void setSessionManager(SessionManager sessionManager);
    void setCustomerDao(CustomerDao customerDao);
    List<Account> findAll();
    Account findById(Integer id);
    Account saveOrUpdate(Account account) throws SQLException;
    void delete(Integer id);
    void add(Account account) throws SQLException;

}
