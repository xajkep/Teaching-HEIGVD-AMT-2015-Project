/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EndUser;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author YounTheory
 */
@Stateless
public class EndUsersDAO extends GenericDAO<EndUser, Long> implements EndUsersDAOLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
            
    @Override
    public List<EndUser> findByApp(long appId) {
        List<EndUser> results;
        try {
            results = em.createNamedQuery("EndUser.findByApp")
                    .setParameter("appId", appId).getResultList();
            return results;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public int getNumberOfUserDuringLast30Days(long appId) {
        int results;
        
        String date = "2015-09-30";
        
        try {
            results = (int) em.createNamedQuery("EndUser.getNumberOfUserDuringLast30Days")
                    .setParameter("date", date).getSingleResult();
            return results;
        } catch (NoResultException e) {
            return 0;
        }
    }
    
    
    
    
    
}
