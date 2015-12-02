package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.BadgeAward;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author xajkep
 */

@Stateless
public class BadgeAwardsDAO extends GenericDAO<BadgeAward, Long> implements BadgeAwardsDAOLocal{
    public List<Object[]> getByUser(long userid) {
        List<Object[]> results = null;
        try {
            results = em.createNamedQuery("BadgeAward.getByUser")
                    .setParameter("userid", userid)
                    .getResultList();
            return results;
        } catch (Exception e) {
            Logger.getLogger(BadgeAwardsDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
