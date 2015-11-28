package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.PointAwards;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mberthouzoz, xajkep
 */
@Local
public interface PointAwardsDAOLocal extends IGenericDAO<PointAwards, Long> {
    public List<PointAwards> findByApikey(String apikey) throws BusinessDomainEntityNotFoundException;
    
    public PointAwards findByIdAndApikey(long pointawardid, String apikey) throws BusinessDomainEntityNotFoundException;
    
}
