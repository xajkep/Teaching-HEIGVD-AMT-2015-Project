package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Application;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface ApplicationsDAOLocal extends IGenericDAO<Application, Long>{
    
    public void disable(Application app);
    
    public void enable(Application app);
    
}
