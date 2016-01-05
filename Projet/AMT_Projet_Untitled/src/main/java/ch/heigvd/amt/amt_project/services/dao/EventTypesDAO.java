package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.EventType;
import javax.ejb.Stateless;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class EventTypesDAO extends GenericDAO<EventType, Long> implements EventTypesDAOLocal {

    /*public EventType createAndReturnManagedEntity(){
        
    }
    */
    @Override
    public EventType findByName(String type, Application app) throws BusinessDomainEntityNotFoundException {
        EventType result;
        try {
            System.out.println(app.getId() + " " + type);
            result = (EventType) em.createNamedQuery("EventType.findByName")
                    .setParameter("app", app.getId())
                    .setParameter("name", type)
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }

}
