/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author thsch
 */
public class LoginPage extends AbstractPageAMT {
    // Identificateurs des éléments
    
    // ATTENTION
    // ATTENTION -> login particulier, pas d'identificateur de page voir avec Benoit
    // ATTENTION
    // Identificateur de la page
    By page = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("page")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
    // Méthodes
    
}
