package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.RuleProperties;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */

@Local
public interface RulePropertiesDAOLocal extends IGenericDAO<RuleProperties, Long>{
    
    public RuleProperties findByValueAndName(String value, String name) throws BusinessDomainEntityNotFoundException;
    
}
