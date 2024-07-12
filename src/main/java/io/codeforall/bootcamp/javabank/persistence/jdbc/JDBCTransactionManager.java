package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

public class JDBCTransactionManager implements TransactionManager {

    private JDBCSessionManager sm;

    private EntityManagerFactory emf;

    private EntityManager em;

    public JDBCTransactionManager() {
        this.emf = sm.startSession();
        this.emf = sm.getEmf();
    }

    public EntityManager getEm() {
        return this.em;
    }

    public void setSessionManager(JDBCSessionManager JDBCSessionManager) {
        this.sm = JDBCSessionManager;
    }

    public void beginRead() {
        sm.startSession();
    }

    public void beginWrite() {
        em.getTransaction().begin();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public void rollback() {
        em.getTransaction().rollback();
    }

    public void close() {
        em.close();
        sm.stopSession();
    }
}
