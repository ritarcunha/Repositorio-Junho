package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JDBCAccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JDBCCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcAccountService;
import io.codeforall.bootcamp.javabank.services.jdbc.JdbcCustomerService;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        CustomerDao customerDao = new JDBCCustomerDao();
        AccountDao accountDao= new JDBCAccountDao();
        SessionManager sessionManager= new JDBCSessionManager();
        TransactionManager transactionManager = new JDBCTransactionManager();

        AccountFactory accountFactory = new AccountFactory();
        JdbcAccountService accountService = new JdbcAccountService(accountDao, accountFactory, sessionManager, transactionManager);
        JdbcCustomerService customerService = new JdbcCustomerService(customerDao,transactionManager,sessionManager);
        customerService.setAccountService(accountService);
        customerDao.setSessionManager(sessionManager);
        customerDao.setAccountDao(accountDao);
        accountDao.setCustomerDao(customerDao);
        accountDao.setSessionManager(sessionManager);
        accountDao.setAccountFactory(accountFactory);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        bootstrap.setAccountFactory(accountFactory);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
