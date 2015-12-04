package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionType;
import javax.ejb.Local;

/**
 *
 * @author xajkep
 */
@Local
public interface ActionTypesDAOLocal extends IGenericDAO<ActionType, Long>{
    
    public ActionType findByName(String name) throws BusinessDomainEntityNotFoundException;
}

