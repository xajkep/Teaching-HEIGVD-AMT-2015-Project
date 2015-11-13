package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.PointAwards;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface PointAwardsDAOLocal extends IGenericDAO<PointAwards, Long> {
    
}
