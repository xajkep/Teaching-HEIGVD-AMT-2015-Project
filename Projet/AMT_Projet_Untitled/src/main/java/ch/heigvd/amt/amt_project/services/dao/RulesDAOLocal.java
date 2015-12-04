package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author Michaël
 */


@Local
public interface RulesDAOLocal extends IGenericDAO<Rule, Long> {
    public Rule findByPropertiesAndEventType(HashMap<String, String> properties, EventType eventType);
}
