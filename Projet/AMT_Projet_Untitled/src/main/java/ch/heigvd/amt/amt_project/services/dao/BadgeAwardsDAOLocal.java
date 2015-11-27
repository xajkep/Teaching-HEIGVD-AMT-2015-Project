package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.BadgeAward;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author xajkep
 */
@Local
public interface BadgeAwardsDAOLocal extends IGenericDAO<BadgeAward, Long> {
    public List<Object[]> getByUser(long userid);
}