package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.Role;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author YounTheory, mberthouzoz
 */
@Stateless
public class AccountsDAO extends GenericDAO<Account, Long> implements AccountsDAOLocal {

    @Override
    public void assignRoleToAccount(List<Role> roles, Account account) {
        for (Role role : roles) {
            assignRoleToAccount(role, account);
        }
    }

    @Override
    public void assignRoleToAccount(Role role, Account account) {
        role.addAccount(account);
        account.addRole(role);
    }

    @Override
    public void removeRoleFromAccount(List<Role> roles, Account account) {
        for (Role role : roles) {
            removeRoleFromAccount(role, account);
        }

    }

    @Override
    public void removeRoleFromAccount(Role role, Account account) {
        role.removeAccount(account);
        account.removeRole(role);
    }
    
    @Override
    public boolean exists(String email) {
        long res = (long) em.createQuery("SELECT COUNT(a) FROM Account a WHERE a.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        
        return res > 0;
    }

    @Override
    public Account login(String email, String password) {
        Account result;
        try {
            result = (Account) em.createNamedQuery("Account.login")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

}
