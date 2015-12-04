package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EventType;
import ch.heigvd.amt.amt_project.models.Rule;
import java.util.HashMap;
import javax.ejb.Stateless;

/**
 *
 * @author mberthouzoz
 */


@Stateless
public class RulesDAO extends GenericDAO<Rule, Long> implements RulesDAOLocal {

    @Override
    public Rule findByPropertiesAndEventType(HashMap<String, String> properties, EventType eventType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
