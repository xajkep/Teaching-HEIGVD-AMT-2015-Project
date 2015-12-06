package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.Badge;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */


@Local
public interface ActionBadgesDAOLocal extends IGenericDAO<ActionBadge, Long>{

    public ActionBadge findByBadge(Badge badge) throws BusinessDomainEntityNotFoundException;
    
}
