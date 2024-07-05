package io.codeforall.bootcamp.javabank;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection=null;

    private String dbUrl=  "jdbc:mysql://[127.0.0.1:3306][,failoverhost:port]/[javabank]";

    public Connection getConnection(){
        try{
            if(connection==null){
                connection= DriverManager.getConnection(dbUrl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;


    }
    public void close(){
        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
