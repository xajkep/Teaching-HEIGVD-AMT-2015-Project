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
    // By firstLinkInAppTableLocator = By.cssSelector("#appTable tbody tr:first td:nth-of-type(5) a:first");
    By tbody = By.tagName("tbody");
    By raws = By.tagName("tr");

    // Identificateur de la page
    By page = By.id("app");

    public AppPage(WebDriver driver) throws InterruptedException {
        super(driver);

        // Vérification si on est sur la bonne page
        Thread.sleep(2000);
        if (driver.findElements(By.id("app")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page (AppPage)");
        }
    }

    // Mettre ici méthode pour clicker sur le premier lien du tableau
    public App_editPage clickOnFirstAppLinkInAppsTable() throws InterruptedException {
        
        WebElement elem0 = driver.findElement(tbody);
        WebElement elem1 = elem0.findElements(raws).get(0);
        System.out.println(elem1.getAttribute("data-test"));
        elem1.findElement(By.className("btn-primary")).click();
        return new App_editPage(driver);
    }
    
}
