package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import javax.ejb.Stateless;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class EventTypeDAO extends GenericDAO<EventType, Long> implements EventTypeDAOLocal {

}
