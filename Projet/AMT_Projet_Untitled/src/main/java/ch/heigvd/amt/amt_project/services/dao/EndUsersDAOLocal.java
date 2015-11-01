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
    
    public List<EndUser> findByApp(long appId, int pageSize, int pageIndex);
    
    public long getNumberOfUserDuringLast30Days();
    
    public long getNumberOfUserByApp(long appId);
}
