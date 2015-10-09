package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.AbstractDomainModel;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Data Access Object Generic. Each DAO class extend of this class.
 *
 * @author mberthouzoz
 */
public class GenericDAO<T extends AbstractDomainModel<PK>, PK> implements IGenericDAO<T, PK> {

    @PersistenceContext
    EntityManager em;

    private final Class<T> jpaEntityClass;

    public GenericDAO() {
        this.jpaEntityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public PK create(T t) {
        T managedEntity = createAndReturnManagedEntity(t);
        PK id = managedEntity.getId();
        return id;
    }

    @Override
    public T createAndReturnManagedEntity(T t) {
        em.persist(t);
        em.flush();
        return t;
    }

    @Override
    public void update(T t) {
        em.merge(t);
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }

    @Override
    public long count() {
        return (long) em.createQuery("Select count(t) from " + jpaEntityClass.getSimpleName() + " t").getSingleResult();
    }

    @Override
    public T findById(PK id) {
        return em.find(jpaEntityClass, id);
    }

    @Override
    public List<T> findAll() {
        List<T> result = result = em.createQuery("Select t from " + jpaEntityClass.getSimpleName() + " t").getResultList();        
        return result;
    }

    @Override
    public List<T> findAllByPage(int pageSize, int pageIndex) {
        List<T> result = em.createQuery("Select t from " + jpaEntityClass.getSimpleName() + " t").setMaxResults(pageSize).setFirstResult(pageIndex * pageSize).getResultList();
        
        return result;
    }

}
