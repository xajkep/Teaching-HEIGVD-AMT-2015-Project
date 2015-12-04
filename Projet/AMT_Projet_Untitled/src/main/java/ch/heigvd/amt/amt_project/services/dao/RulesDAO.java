package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz
 */


@Stateless
public class RulesDAO extends GenericDAO<Rule, Long> implements RulesDAOLocal {

    @Override
    public List<Rule> findByPropertiesAndEventType(List<RuleProperties> properties, String eventType) throws BusinessDomainEntityNotFoundException{
        List<Rule> result = null;
        try {
            result = findByEventType(eventType);
            
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }

    @Override
    public List<Rule> findByEventType(String eventType) throws BusinessDomainEntityNotFoundException {
        List<Rule> result = null;
        try {
            result = em.createNamedQuery("Rule.findByEventType")
                .setParameter("eventType", eventType)
                .getResultList();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }

}
