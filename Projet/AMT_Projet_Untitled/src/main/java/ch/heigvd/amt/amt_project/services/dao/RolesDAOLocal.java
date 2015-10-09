package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Role;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface RolesDAOLocal extends IGenericDAO<Role, Long>{
    
}
