package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.PointAwards;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author mberthouzoz, xajkep
 */
@Stateless
public class PointAwardsDAO extends GenericDAO<PointAwards, Long> implements PointAwardsDAOLocal {
    
    @Override
    public List<PointAwards> findByApikey(String apikey)
            throws BusinessDomainEntityNotFoundException {
        
        List<PointAwards> results = null;
        
        try {
            results = em.createNamedQuery("PointAwards.findByApikey")
                    .setParameter("apikey", apikey).getResultList();
            return results;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    @Override
    public PointAwards findByIdAndApikey(long pointawardid, String apikey)
            throws BusinessDomainEntityNotFoundException {
        
        PointAwards result = null;
        
        try {
            result = (PointAwards) em.createNamedQuery("PointAwards.findByIdAndApikey")
                    .setParameter("pointawardid", pointawardid)
                    .setParameter("apikey", apikey)
                    .getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
}
