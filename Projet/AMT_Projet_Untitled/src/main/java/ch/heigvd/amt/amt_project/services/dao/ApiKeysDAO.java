package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ApiKey;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author YounTheory, xajkep
 */
@Stateless
public class ApiKeysDAO extends GenericDAO<ApiKey, Long> implements ApiKeysDAOLocal {
    public boolean exists(String apikey) {
        ApiKey result = null;
        
        try {
            result = (ApiKey) em.createNamedQuery("ApiKey.exists")
                    .setParameter("apikey", apikey)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
