package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Model;
import io.codeforall.bootcamp.javabank.persistence.SessionManager;
import io.codeforall.bootcamp.javabank.persistence.daos.Dao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public abstract class JDBCGenericDao<T extends Model> implements Dao<T> {

    protected JDBCSessionManager sm;
    protected Class<T> modelType;

    private JDBCTransactionManager tm;


    public JDBCGenericDao(Class<T> modelType) {
        this.modelType = modelType;
    }


    public void delete(Integer id) {
        sm = new JDBCSessionManager();
        tm = new JDBCTransactionManager();
        sm.startSession();

       // CriteriaBuilder builder = tm.getEm().getCriteriaBuilder();

        //TypedQuery<T> query= tm.getEm().createQuery("DELETE FROM ");

            String query = "DELETE from " + modelType.getSimpleName().toLowerCase(Locale.ROOT) + " WHERE id =?";
            //PreparedStatement statement = sm.getCurrentSession().prepareStatement(query);

            //"DELETE customer FROM Customer customer WHERE user.name = :name", User.class)

            //statement.setInt(1, id);

            //statement.executeUpdate();
            //statement.close();

    }

}
