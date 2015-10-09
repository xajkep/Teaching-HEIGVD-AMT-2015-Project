package ch.heigvd.amt.amt_project.services;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ApiKey;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Role;
import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApiKeysDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RolesDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Generate Data
 * @author mberthouzoz
 */
@Stateless
public class TestDataManager implements TestDataManagerLocal {

    @EJB
    ApplicationsDAOLocal applicationsDAO;
    
    @EJB
    RolesDAOLocal rolesDAO;
    
    @EJB
    AccountsDAOLocal accountsDAO;
    
    @EJB
    ApiKeysDAOLocal apiKeysDAO;
  
    
    public void generateTestData() {
        
        /**
         * Generate Roles
         */
        System.out.println("Generate Roles");
        Role admin = rolesDAO.createAndReturnManagedEntity(new Role("Admin"));
        Role user = rolesDAO.createAndReturnManagedEntity(new Role("User"));
        
        /**
         * Generate Accounts
         */
        System.out.println("Generate Accounts");
        List<Role> adminRole = new ArrayList<>();
        adminRole.add(admin);
        adminRole.add(user);
        
        List<Role> userRole = new ArrayList<>();
        adminRole.add(user);
        Account toto = accountsDAO.createAndReturnManagedEntity(new Account("toto@contoso.com", "Toto", "Smith", "12345"));
        Account bob = accountsDAO.createAndReturnManagedEntity(new Account("bob@contoso.com", "Bob", "Lenon", "54321"));
        
        accountsDAO.assignRoleToAccount(userRole, bob);
        accountsDAO.assignRoleToAccount(adminRole, toto);
        
        /**
         * Generate Applications
         */
        System.out.println("Generate Applications");
        applicationsDAO.create(new Application("app1", "testapp1", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));
        applicationsDAO.create(new Application("app2", "testapp2", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, bob));
        applicationsDAO.create(new Application("app3", "testapp3", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));
        applicationsDAO.create(new Application("app4", "testapp4", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));
    }
}
