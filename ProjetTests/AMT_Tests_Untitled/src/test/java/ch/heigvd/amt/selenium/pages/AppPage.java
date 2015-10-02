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
public class AppPage extends AbstractPageAMT {
    
    // Mettre ici les By locateur -> voir exemple CorporateInformationPage pour le selecteur
    //By bEdit = By.id("app_edit_button");
    //By bState = By.id("app_state_button");
    
    // Identificateur de la page
    By page = By.id("app");

    
    public AppPage(WebDriver driver) {
        super(driver);
        
        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("page")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
    // Mettre ici méthode pour clicker sur le premier lien du tableau
}
