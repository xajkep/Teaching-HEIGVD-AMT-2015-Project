/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author thsch
 */
public class AppPage extends AbstractPageAMT {
    
    // Mettre ici les By locateur -> voir exemple CorporateInformationPage pour le selecteur
    
    
    public AppPage(WebDriver driver) {
        super(driver);
        
        // Vérification si on est sur la bonne page
        if (!"Applications".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
    // Mettre ici méthode pour clicker sur le premier lien du tableau
}
