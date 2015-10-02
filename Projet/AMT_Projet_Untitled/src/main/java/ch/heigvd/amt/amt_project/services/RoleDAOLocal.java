/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.services;

import ch.heigvd.amt.amt_project.models.Role;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface RoleDAOLocal extends IGenericDAO<Role, Long>{
    
}
