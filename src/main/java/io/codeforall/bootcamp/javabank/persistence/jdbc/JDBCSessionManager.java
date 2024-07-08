package io.codeforall.bootcamp.javabank.persistence.jdbc;

import io.codeforall.bootcamp.javabank.persistence.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSessionManager implements SessionManager {

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    //session tem relacao com a conexao da base dados
    //e neste caso o sistema de base de dados é o mysql, que contem as transations e por isso precisa de fazer autocommit ainda
    //caso eu queira trocar o sistema de base de dados / colocar um fihceiro deixo de usar transactions

    public void startSession() {//antes estavamos a usar transaçoes que lidavam so com a liguagem de sql, as sessoes sao agnosticas a todas as formas de persistencia lingugens de base de daods/ esturutra de dados(file)

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
    }

    public void stopSession() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

    public Connection getCurrentSession() {
        startSession();
        return connection;
    }
}