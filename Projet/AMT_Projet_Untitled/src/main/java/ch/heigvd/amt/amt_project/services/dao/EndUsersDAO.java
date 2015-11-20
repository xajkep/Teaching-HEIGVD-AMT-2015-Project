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
    public List<EndUser> findByApp(long appId, long userId, int pageSize, int pageIndex) throws BusinessDomainEntityNotFoundException {
        List<EndUser> results;
        try {
            results = em.createNamedQuery("EndUser.findByApp")
                    .setParameter("app", appId)
                    .setParameter("user", userId)
                    .setMaxResults(pageSize)
                    .setFirstResult(pageIndex * pageSize).getResultList();
            return results;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    @Override
    public long getNumberOfUserDuringLast30Days() throws BusinessDomainEntityNotFoundException {
        long results = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Date date = new Date(cal.getTimeInMillis());
                
        try {
            results = (long) em.createNamedQuery("EndUser.getNumberOfUserDuringLastDays")
                    .setParameter("date", date).getSingleResult();
            return results;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    @Override
    public long getNumberOfUserByApp(long appId) throws BusinessDomainEntityNotFoundException {
        long result = 0;
        try {
            result = (long) em.createNamedQuery("EndUser.getNumberOfUserByApp")
                    .setParameter("app", appId).getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    public long getPoints(long userId) throws BusinessDomainEntityNotFoundException {
        long result;
        try {
            result = (long) em.createNamedQuery("EndUser.getPoints")
                    .setParameter("user", userId).getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    /**
     * Get the TOP user order by their points
     * @author xajkep
     * @param appId
     * @param numberOfUser
     * @return 
     */
    public List<EndUser> getBestUsers(long appId, long numberOfUser) throws BusinessDomainEntityNotFoundException {
        List<EndUser> results;
        try {
            results = (List<EndUser>) em.createNamedQuery("EndUser.getBestUsers")
                    .setParameter("app", appId)
                    .setParameter("numberOfUser", numberOfUser);
            return results;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
}
