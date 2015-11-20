package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Badge;
import javax.ejb.Stateless;

/**
 *
 * @author thsch
 */

@Stateless
public class BadgesDAO extends GenericDAO<Badge, Long> implements BadgesDAOLocal{
    
}
