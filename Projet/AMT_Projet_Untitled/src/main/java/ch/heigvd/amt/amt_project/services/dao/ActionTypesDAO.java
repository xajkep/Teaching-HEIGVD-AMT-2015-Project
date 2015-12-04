package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionType;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author xajkep, mberthouzoz
 */
@Stateless
public class ActionTypesDAO extends GenericDAO<ActionType, Long> implements ActionTypesDAOLocal {
    
    public ActionType findByName(String name) 
            throws BusinessDomainEntityNotFoundException {
        
        ActionType result = null;
        
        try {
            result = (ActionType) em.createNamedQuery("ActionType.findByName")
                    .setParameter("name", name)
                    .getSingleResult();
        }  catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
        
        return result;
    }
}
