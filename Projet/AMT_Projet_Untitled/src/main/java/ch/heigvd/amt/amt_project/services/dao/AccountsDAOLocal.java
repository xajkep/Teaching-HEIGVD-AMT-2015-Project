/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.Role;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author YounTheory
 */
@Local
public interface AccountsDAOLocal extends IGenericDAO<Account, Long>{
    
    public void assignRoleToAccount(List<Role> roles, Account account );
    public void assignRoleToAccount(Role role, Account account );
    public void removeRoleFromAccount(List<Role> roles, Account account);
    public void removeRoleFromAccount(Role role, Account account);
    
}
