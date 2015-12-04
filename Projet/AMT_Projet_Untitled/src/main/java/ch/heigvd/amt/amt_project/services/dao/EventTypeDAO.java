package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class EventTypeDAO extends GenericDAO<EventType, Long> implements EventTypeDAOLocal {

    @Override
    public EventType findByName(String type, Long appId) throws BusinessDomainEntityNotFoundException {
        EventType result;
        try {
            result = (EventType) em.createNamedQuery("EventType.findByName")
                    .setParameter("app", appId)
                    .setParameter("name", type)
                    .getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }

}
