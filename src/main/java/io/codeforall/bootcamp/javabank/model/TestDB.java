package io.codeforall.bootcamp.javabank.model;

import io.codeforall.bootcamp.javabank.model.account.AbstractAccount;
import io.codeforall.bootcamp.javabank.model.account.CheckingAccount;
import io.codeforall.bootcamp.javabank.model.account.SavingsAccount;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class TestDB {

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setFirstName("Marcelo");
        customer.setLastName("Oliveira");
        customer.setEmail("ola@adeus.com");
        customer.setPhone("913918291");
        customer.addAccount(new SavingsAccount());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        JDBCTransactionManager transactionManager = new JDBCTransactionManager();

        try {

            transactionManager.beginWrite();
            em.persist(customer);
            transactionManager.commit();

        } catch (RollbackException ex) {
            transactionManager.rollback();

        } finally {
            if (em != null) {
                transactionManager.close();
            }
        }
    }
}
