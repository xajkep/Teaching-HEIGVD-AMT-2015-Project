package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */


@Local
public interface ActionBadgesDAOLocal extends IGenericDAO<ActionBadge, Long>{
    
}
