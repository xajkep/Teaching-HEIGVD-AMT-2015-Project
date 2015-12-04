package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionBadge;
import javax.ejb.Stateless;

/**
 *
 * @author mberthouzoz
 */
@Stateless
public class ActionBadgesDAO  extends GenericDAO<ActionBadge, Long>  implements ActionBadgesDAOLocal {

}
