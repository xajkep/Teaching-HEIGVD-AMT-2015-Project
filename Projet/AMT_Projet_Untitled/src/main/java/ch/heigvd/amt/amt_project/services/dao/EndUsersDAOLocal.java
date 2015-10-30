package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EndUser;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface EndUsersDAOLocal extends IGenericDAO<EndUser, Long>{
    
}
