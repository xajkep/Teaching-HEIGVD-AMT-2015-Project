package ch.heigvd.amt.amt_project.services;

import ch.heigvd.amt.amt_project.models.Account;
import ch.heigvd.amt.amt_project.models.ApiKey;
import ch.heigvd.amt.amt_project.models.Application;
import ch.heigvd.amt.amt_project.models.Badge;
import ch.heigvd.amt.amt_project.models.BadgeAward;
import ch.heigvd.amt.amt_project.models.EndUser;
import ch.heigvd.amt.amt_project.models.PointAwards;
import ch.heigvd.amt.amt_project.models.Role;
import ch.heigvd.amt.amt_project.services.dao.AccountsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApiKeysDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.ApplicationsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.BadgesDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.EndUsersDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.PointAwardsDAOLocal;
import ch.heigvd.amt.amt_project.services.dao.RolesDAOLocal;
import java.sql.Date;
import java.util.ArrayList;
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

    @EJB
    BadgesDAOLocal badgesDAO;

    @EJB
    PointAwardsDAOLocal pointAwardsDAO;

    @Override
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

        /**
         * Generate Badge for App 1
         */
        System.out.println("Generate badges for app1");
        Badge badge1App1 = badgesDAO.createAndReturnManagedEntity(new Badge("Description 1 badge app1", "urlbadge1", app1));
        Badge badge2App1 = badgesDAO.createAndReturnManagedEntity(new Badge("Description 2 badge app1", "urlbadge2", app1));

        /**
         * Generate Badge for App 2
         */
        System.out.println("Generate badges for app2");
        Badge badge1App2 = badgesDAO.createAndReturnManagedEntity(new Badge("Description 1 badge app2", "urlbadge1", app2));
        Badge badge2App2 = badgesDAO.createAndReturnManagedEntity(new Badge("Description 2 badge app2", "urlbadge2", app2));
        Badge badge3App2 = badgesDAO.createAndReturnManagedEntity(new Badge("Description 3 badge app2", "urlbadge3", app2));

        /**
         * Set badge for enduser1
         */
        EndUser enduser1 = endUsersDAO.findById(Integer.toUnsignedLong(1));
        BadgeAward badgeAward1 = new BadgeAward(badge1App1, enduser1, new Date(System.currentTimeMillis()));
        endUsersDAO.assignBadgeAwardsToEndUser(badgeAward1, enduser1);

        /**
         * Set badge for enduser30
         */
        EndUser enduser30 = endUsersDAO.findById(Integer.toUnsignedLong(30));
        BadgeAward badgeAward2 = new BadgeAward(badge1App2, enduser30, new Date(System.currentTimeMillis()));
        endUsersDAO.assignBadgeAwardsToEndUser(badgeAward2, enduser30);

        BadgeAward badgeAward3 = new BadgeAward(badge2App2, enduser30, new Date(System.currentTimeMillis()));
        endUsersDAO.assignBadgeAwardsToEndUser(badgeAward3, enduser30);

        BadgeAward badgeAward4 = new BadgeAward(badge3App2, enduser30, new Date(System.currentTimeMillis()));
        endUsersDAO.assignBadgeAwardsToEndUser(badgeAward4, enduser30);

        /**
         * Set point
         */
        for (int i = 0; i < 30; i++) {
            pointAwardsDAO.create(new PointAwards(enduser1, 50, "Reason " + i));
        }
        
        for (int i = 0; i < 30; i++) {
            pointAwardsDAO.create(new PointAwards(enduser30, 100, "Reason " + i));
        }

    }
}
