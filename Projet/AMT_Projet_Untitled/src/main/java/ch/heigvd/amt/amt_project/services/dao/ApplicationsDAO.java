package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.EndUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Data Access Object for Application
 *
 * @author mberthouzoz, xajkep
 */
@Stateless
public class ApplicationsDAO extends GenericDAO<Application, Long> implements ApplicationsDAOLocal {

    /**
     * Disable the application.
     *
     * @param app Application to disable
     */
    public void disable(Application app) {
        app.setEnable(Boolean.FALSE);
    }

    /**
     * Enable the application.
     *
     * @param app Application to enable
     */
    public void enable(Application app) {
        app.setEnable(Boolean.TRUE);
    }

    @Override
    public List<Application> findAllByUserId(long userId) throws BusinessDomainEntityNotFoundException {
        List<Application> results;
        try {
            results = em.createNamedQuery("Application.findAllByUserId")
                    .setParameter("user", userId).getResultList();
            return results;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }
    
    public Application findByApikey(String apikey) throws BusinessDomainEntityNotFoundException {
        Application result;
        try {
            result = (Application) em.createNamedQuery("Application.findByApikey")
                    .setParameter("apikey", apikey).getSingleResult();
            return result;
        } catch (NoResultException e) {
            throw new BusinessDomainEntityNotFoundException();
        }
    }

}
