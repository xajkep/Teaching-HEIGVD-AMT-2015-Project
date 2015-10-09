/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import java.util.List;
import static org.junit.Assert.assertFalse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author thsch
 */
public class AppPage extends AbstractPageAMT {

    // Mettre ici les By locateur -> voir exemple CorporateInformationPage pour le selecteur
    //By bEdit = By.id("app_edit_button");
    //By bState = By.id("app_state_button");
    By allAppLinksInAppTableLocator = By.cssSelector("#appTable tbody tr td:nth-of-type(1) a");

    // Identificateur de la page
    By page = By.id("app");

    public AppPage(WebDriver driver) {
        super(driver);

        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("app")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    // Mettre ici méthode pour clicker sur le premier lien du tableau
    public App_editPage clickOnFirstAppLinkInAppsTable() {
        List<WebElement> links = driver.findElements(allAppLinksInAppTableLocator);

        assertFalse(links.isEmpty());
        
        links.get(0).click();
        
        return new App_editPage(driver);
    }
    
}
