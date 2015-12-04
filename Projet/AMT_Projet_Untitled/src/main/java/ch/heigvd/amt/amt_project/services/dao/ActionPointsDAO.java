package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.ActionType;
import javax.ejb.Stateless;

/**
 *
 * @author Michaël
 */


@Stateless
public class ActionPointsDAO extends GenericDAO<ActionPoints, Long>  implements ActionPointsDAOLocal {

}
