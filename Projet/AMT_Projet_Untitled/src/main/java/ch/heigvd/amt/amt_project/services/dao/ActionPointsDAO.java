package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ActionBadge;
import ch.heigvd.amt.amt_project.models.ActionPoints;
import ch.heigvd.amt.amt_project.models.ActionType;
import ch.heigvd.amt.amt_project.models.Badge;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Michaël
 */


@Stateless
public class ActionPointsDAO extends GenericDAO<ActionPoints, Long>  implements ActionPointsDAOLocal {
    @Override
    public ActionPoints findByPoints(long points) throws BusinessDomainEntityNotFoundException {
        ActionPoints res = null;

        try {
            res = (ActionPoints) em.createNamedQuery("ActionPoints.findByPoints")
                    .setParameter("points", points)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }

        return res;
    }
}
