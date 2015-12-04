package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import ch.heigvd.amt.amt_project.models.RuleProperties;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michaël
 */


@Local
public interface RulesDAOLocal extends IGenericDAO<Rule, Long> {
    public List<Rule> findByPropertiesAndEventType(List<RuleProperties> properties, EventType eventType) throws BusinessDomainEntityNotFoundException;
    
    public List<Rule> findByEventType(EventType eventType) throws BusinessDomainEntityNotFoundException;
}
