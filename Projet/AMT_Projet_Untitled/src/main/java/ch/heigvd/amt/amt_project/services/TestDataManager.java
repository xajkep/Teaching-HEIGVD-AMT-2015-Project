package ch.heigvd.amt.amt_project.services;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ApiKey;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.models.Role;
import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApiKeysDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RolesDAOLocal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Generate Data
 *
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

    @EJB
    EndUsersDAOLocal endUsersDAO;

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
        Application app1 = applicationsDAO.createAndReturnManagedEntity(new Application("app1", "testapp1", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));
        Application app2 = applicationsDAO.createAndReturnManagedEntity(new Application("app2", "testapp2", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, bob));
        Application app3 = applicationsDAO.createAndReturnManagedEntity(new Application("app3", "testapp3", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));
        Application app4 = applicationsDAO.createAndReturnManagedEntity(new Application("app4", "testapp4", apiKeysDAO.createAndReturnManagedEntity(new ApiKey()), true, toto));

        /**
         * Generate endUser
         */
        System.out.println("Generate endUsers");
        for (int i = 0; i < 25; ++i) {
            endUsersDAO.create(new EndUser(app1, "endUser1" + i, new Date(System.currentTimeMillis())));
        }
        
        for (int i = 0; i < 30; ++i) {
            endUsersDAO.create(new EndUser(app2, "endUser2" + i, new Date(System.currentTimeMillis())));
        }
        
        for (int i = 0; i < 20; ++i) {
            endUsersDAO.create(new EndUser(app3, "endUser3" + i, new Date(System.currentTimeMillis())));
        }
        
        for (int i = 0; i < 15; ++i) {
            endUsersDAO.create(new EndUser(app4, "endUser4" + i, new Date(System.currentTimeMillis())));
        }

    }
}
