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
    public List<EndUser> findByApp(long appId) {
        List<EndUser> results;
        try {
            results = em.createNamedQuery("EndUser.findByApp")
                    .getResultList();
                    //.setParameter("app", appId).getResultList();
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
}
