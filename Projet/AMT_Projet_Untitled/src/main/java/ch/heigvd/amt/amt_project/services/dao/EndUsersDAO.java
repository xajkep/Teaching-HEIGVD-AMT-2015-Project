package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EndUser;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author YounTheory
 */
@Stateless
public class EndUsersDAO extends GenericDAO<EndUser, Long> implements EndUsersDAOLocal {
            
    @Override
    public List<EndUser> findByApp(long appId, long userId, int pageSize, int pageIndex) {
        List<EndUser> results;
        try {
            results = em.createNamedQuery("EndUser.findByApp")
                    .setParameter("app", appId)
                    .setParameter("user", userId)
                    .setMaxResults(pageSize)
                    .setFirstResult(pageIndex * pageSize).getResultList();
            return results;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public long getNumberOfUserDuringLast30Days() {
        long results = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("1");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        System.out.println("2");
        Date date = new Date(cal.getTimeInMillis());
        System.out.println("3");
                
        try {
            results = (long) em.createNamedQuery("EndUser.getNumberOfUserDuringLastDays")
                    .setParameter("date", date).getSingleResult();
            return results;
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    @Override
    public long getNumberOfUserByApp(long appId) {
        long result = 0;
        try {
            result = (long) em.createNamedQuery("EndUser.getNumberOfUserByApp")
                    .setParameter("app", appId).getSingleResult();
            return result;
        } catch (NoResultException e) {
            return 0;
        }
    }
    
}
