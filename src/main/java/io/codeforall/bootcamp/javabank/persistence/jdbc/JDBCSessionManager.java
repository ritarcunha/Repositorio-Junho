package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSessionManager implements SessionManager {

    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_DB = "javabank";

    private static final String CONNECTOR = "jdbc:mysql:";

    //private String dbUrl;
    //private String user;
    //private String pass;
    //private Connection connection;

    private EntityManagerFactory emf;

    public JDBCSessionManager() {

    }
    public EntityManagerFactory getEmf (){
        return emf;
    }


    @Override
    public EntityManagerFactory startSession() {
        return emf = Persistence.createEntityManagerFactory("test");
    }

    @Override
    public void stopSession() {
        emf.close();
    }
}
