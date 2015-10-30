/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.EndUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface EndUsersDAOLocal extends IGenericDAO<EndUser, Long> {
    
    public List<EndUser> findByApp(long appId);
    
    public int getNumberOfUserDuringLast30Days(long appId);
}
