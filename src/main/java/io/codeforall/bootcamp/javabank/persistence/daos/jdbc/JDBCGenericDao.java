package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Model;
import io.codeforall.bootcamp.javabank.persistence.daos.Dao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public abstract class JDBCGenericDao<T extends Model> implements Dao<T> {

    protected JDBCSessionManager sm;
    protected Class<T> modelType;

    public JDBCGenericDao(Class<T> modelType){
        this.modelType = modelType;
    }

    public void setSm(JDBCSessionManager sm) {
        this.sm = sm;
    }

    public void delete(Integer id){
        try{
            String query = "DELETE from " + modelType.getSimpleName().toLowerCase(Locale.ROOT) + " WHERE id =?";
            PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
