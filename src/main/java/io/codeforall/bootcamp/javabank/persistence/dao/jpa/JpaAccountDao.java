package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;
<<<<<<< HEAD
=======
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
>>>>>>> a9c2c77bc56e61f0a8a0f6a825227f401a988522
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link AccountDao} implementation
 */
@Repository
public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaAccountDao() {
        super(Account.class);
    }
}
