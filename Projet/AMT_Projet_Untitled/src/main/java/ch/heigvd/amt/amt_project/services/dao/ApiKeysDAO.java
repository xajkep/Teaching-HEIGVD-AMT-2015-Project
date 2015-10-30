package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ApiKey;
import javax.ejb.Stateless;

/**
 *
 * @author YounTheory
 */
@Stateless
public class ApiKeysDAO extends GenericDAO<ApiKey, Long> implements ApiKeysDAOLocal {

}
