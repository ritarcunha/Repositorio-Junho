package io.codeforall.bootcamp.javabank.services.jdbc;

import io.codeforall.bootcamp.javabank.factories.AccountFactory;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import io.codeforall.bootcamp.javabank.services.AccountService;

import java.sql.*;

public class JdbcAccountService implements AccountService {

    private AccountDao accountDao;
    private AccountFactory accountFactory;

    private SessionManager sessionManager;

    private TransactionManager transactionManager;

    public JdbcAccountService(AccountDao accountDao, AccountFactory accountFactory, SessionManager sessionManager, TransactionManager transactionManager) {
        this.accountDao = accountDao;
        this.accountFactory = accountFactory;
        this.sessionManager=sessionManager;
        this.transactionManager=transactionManager;
    }

    @Override
    public Account get(Integer id) {//transforma a conta da base de dados num objeto de java

       transactionManager.beginRead();// ja tenho uma conexao criada
       Account account= accountDao.findById(id);
       sessionManager.stopSession();
       return account;
    }

    @Override
    public void add(Account account) {

        try {
            transactionManager.beginWrite();
            accountDao.add(account);
            transactionManager.commit();
        }
        catch(SQLException e){
            transactionManager.rollback();
        }
    }

    @Override
    public void deposit(int id, double amount) {

        Connection connection = sessionManager.getCurrentSession();
        transactionManager.beginWrite();
        Account account = null;

        try {


            account = get(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);

           accountDao.saveOrUpdate(account);

            transactionManager.commit();

        } catch (SQLException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void withdraw(int id, double amount) {

        Connection connection = sessionManager.getCurrentSession();


        try {
           transactionManager.beginWrite();

            Account account = get(id);

            if (account == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            account.debit(amount);

            accountDao.saveOrUpdate(account);

            transactionManager.commit();

        } catch (SQLException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Connection connection = sessionManager.getCurrentSession();

        try {
           transactionManager.beginWrite();

            Account srcAccount = get(srcId);
            Account dstAccount = get(dstId);

            if (srcAccount == null || dstAccount == null) {
                throw new IllegalArgumentException("invalid account id");
            }

            if (srcAccount.canDebit(amount)) {

                srcAccount.debit(amount);
                dstAccount.credit(amount);

               accountDao.saveOrUpdate(srcAccount);
               accountDao.saveOrUpdate(dstAccount);

                transactionManager.commit();

            } else {
                transactionManager.rollback();
            }

        } catch (SQLException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }


}
