package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ActionPoints;
import javax.ejb.Local;

/**
 *
 * @author Michaël
 */


@Local
public interface ActionPointsDAOLocal extends IGenericDAO<ActionPoints, Long> {
    public ActionPoints findByPoints(long points) throws BusinessDomainEntityNotFoundException;
}
