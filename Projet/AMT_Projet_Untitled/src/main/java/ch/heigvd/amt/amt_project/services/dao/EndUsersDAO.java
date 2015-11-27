package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.rest.resources.LeaderboardResource;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author YounTheory, mberthouzoz
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
     * @param apiKey
     * @param appId
     * @param numberOfUser
     * @return 
     * @throws ch.heigvd.amt.amt_project.services.dao.BusinessDomainEntityNotFoundException 
     */
    @Override
    public List<Object[]> getBestUsers(String apiKey, int numberOfUser) {
        List<Object[]> results = null;
        try {
            results = em.createNamedQuery("EndUser.getBestUsers")
                    .setParameter("apikey", apiKey)
                    .setMaxResults(numberOfUser)
                    .getResultList();
            return results;
        } catch (Exception e) {
            Logger.getLogger(EndUsersDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void assignBadgeAwardsToEndUser(List<BadgeAward> badges, EndUser endUser) {
        for (BadgeAward badge : badges){
            assignBadgeAwardsToEndUser(badge, endUser);
        }
    }

    @Override
    public void assignBadgeAwardsToEndUser(BadgeAward badge, EndUser endUser) {
        endUser.getBadgeAwards().add(badge);
    }
}
