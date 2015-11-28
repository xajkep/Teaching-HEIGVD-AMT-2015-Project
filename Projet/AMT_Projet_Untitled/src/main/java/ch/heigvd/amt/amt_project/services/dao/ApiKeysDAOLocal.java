package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ApiKey;
import javax.ejb.Local;

/**
 *
 * @author YounTheory, xajkep
 */
@Local
public interface ApiKeysDAOLocal extends IGenericDAO<ApiKey, Long>{
    public boolean exists(String apikey);
}
