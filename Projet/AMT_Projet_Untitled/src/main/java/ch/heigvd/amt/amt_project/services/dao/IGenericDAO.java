package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.AbstractDomainModel;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for GenericDAO. Provide some mandatory method.
 * 
 * @author mberthouzoz
 */
@Local
public interface IGenericDAO<T extends AbstractDomainModel, PK> {

  public PK create(T t);

  public T createAndReturnManagedEntity(T t);

  public void update(T t);

  public void delete(T t);

  public long count();
  
  public T findById(PK id);

  public List<T> findAll();
  
  public List<T> findAllByPage(int pageSize, int pageIndex);

}
