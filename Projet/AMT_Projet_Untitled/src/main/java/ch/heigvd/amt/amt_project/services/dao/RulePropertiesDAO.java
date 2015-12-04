package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.RuleProperties;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class RulePropertiesDAO extends GenericDAO<RuleProperties, Long> implements RulePropertiesDAOLocal {

    @Override
    public RuleProperties findByValueAndName(String value, String name) throws BusinessDomainEntityNotFoundException {
        RuleProperties result;
        try {
            result = (RuleProperties) em.createNamedQuery("RuleProperties.findByValueAndName")
                    .setParameter("value", value)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
        
        return result;
    }
}
