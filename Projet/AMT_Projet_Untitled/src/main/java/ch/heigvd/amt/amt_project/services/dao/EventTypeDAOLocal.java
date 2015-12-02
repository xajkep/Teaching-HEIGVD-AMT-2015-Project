package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface EventTypeDAOLocal extends IGenericDAO<EventType, Long> {
    
}