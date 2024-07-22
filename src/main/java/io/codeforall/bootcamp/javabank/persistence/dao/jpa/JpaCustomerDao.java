package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.List;
=======
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import org.springframework.stereotype.Repository;
>>>>>>> a9c2c77bc56e61f0a8a0f6a825227f401a988522

/**
 * A JPA {@link CustomerDao} implementation
 */
@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Customer.class);
    }
}
