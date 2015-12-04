package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface EventTypesDAOLocal extends IGenericDAO<EventType, Long> {

    public EventType findByName(String type, Long appId) throws BusinessDomainEntityNotFoundException;
    
}
