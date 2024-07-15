package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.model.Model;
import io.codeforall.bootcamp.javabank.persistence.dao.Dao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A generic jpa data access object to be used as a base for concrete jpa service implementations
 *
 * @param <T> the model type
 * @see Dao
 */
public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected EntityManager em;
    protected Class<T> modelType;

    /**
     * Initializes a new JPA DAO instance given a model type
     *
     * @param modelType the model type
     */
    public GenericJpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    /**
     * Sets the session manager
     *
     * @param em the session manager to set
     */
    @Transactional
    public void setSm(EntityManager em) {
        this.em = em;
    }

    /**
     * @see Dao#findAll()
     */
   @Transactional
    @Override
    public List<T> findAll() {

            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
            Root<T> root = criteriaQuery.from(modelType);
            return em.createQuery(criteriaQuery).getResultList();

            // Using JPQL
            // return em.createQuery( "from " + modelType.getSimpleName(), modelType).getResultList();

    }

    /**
     * @see Dao#findById(Integer)
     */
   @Transactional
    @Override
    public T findById(Integer id) {
            return em.find(modelType, id);
    }

    /**
     * @see Dao#saveOrUpdate(Model)
     */
    @Transactional
    @Override
    public T saveOrUpdate(T modelObject) {
            return em.merge(modelObject);
    }

    /**
     * @see Dao#delete(Integer)
     */
   @Transactional
    @Override
    public void delete(Integer id) {
            em.remove(em.find(modelType, id));
    }
}
