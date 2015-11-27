package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Badge;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author thsch
 */

@Local
public interface BadgesDAOLocal extends IGenericDAO<Badge, Long> {

}
