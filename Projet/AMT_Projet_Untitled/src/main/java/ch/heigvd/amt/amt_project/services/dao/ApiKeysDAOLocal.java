package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ApiKey;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface ApiKeysDAOLocal extends IGenericDAO<ApiKey, Long>{
    
}
