package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EndUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface EndUsersDAOLocal extends IGenericDAO<EndUser, Long> {
    
    public List<EndUser> findByApp(long appId, long userId, int pageSize, int pageIndex) throws BusinessDomainEntityNotFoundException;
    
    public long getNumberOfUserDuringLast30Days() throws BusinessDomainEntityNotFoundException;
    
    public long getNumberOfUserByApp(long appId) throws BusinessDomainEntityNotFoundException;
    
    public long getPoints(long userId) throws BusinessDomainEntityNotFoundException;
    
    public List<EndUser> getBestUsers(String apiKey, int numberOfUser);
}
