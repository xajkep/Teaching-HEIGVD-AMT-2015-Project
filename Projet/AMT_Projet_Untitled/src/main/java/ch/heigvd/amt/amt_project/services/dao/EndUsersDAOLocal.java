package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface EndUsersDAOLocal extends IGenericDAO<EndUser, Long> {
    
    public List<EndUser> findByApikey(String apikey);
    
    public List<EndUser> findByApp(long appId, long userId, int pageSize, int pageIndex) throws BusinessDomainEntityNotFoundException;
    
    public long getNumberOfUserDuringLast30Days() throws BusinessDomainEntityNotFoundException;
    
    public long getNumberOfUserByApp(long appId) throws BusinessDomainEntityNotFoundException;
    
    public long getPoints(long userId) throws BusinessDomainEntityNotFoundException;
    
    public void assignBadgeAwardsToEndUser(List<BadgeAward> badges,  EndUser endUser);
    
    public void assignBadgeAwardsToEndUser(BadgeAward badge, EndUser endUser);

    public List<Object[]> getBestUsers(String apiKey, int numberOfUser);

    public EndUser findByName(String name, long appId) throws BusinessDomainEntityNotFoundException;
}
