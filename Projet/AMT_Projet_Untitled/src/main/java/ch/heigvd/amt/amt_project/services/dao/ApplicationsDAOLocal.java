package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Application;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface ApplicationsDAOLocal extends IGenericDAO<Application, Long>{
    
    public void disable(Application app);
    
    public void enable(Application app);
    
    public List<Application> findAllByUserId(long id) throws BusinessDomainEntityNotFoundException;
}
