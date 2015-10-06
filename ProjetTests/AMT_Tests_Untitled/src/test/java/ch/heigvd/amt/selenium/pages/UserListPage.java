
package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class UserListPage extends AbstractPageAMT {
    // Identificateur de la page
    By page = By.id("userlist");

    public UserListPage(WebDriver driver) {
        super(driver);
        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("page")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
    // Méthodes
    
}
