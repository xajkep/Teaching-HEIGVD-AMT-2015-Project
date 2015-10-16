/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

public class App_editPage extends AbstractPageAMT {

    // Identificateurs formulaire
    By tfName = By.id("name");
    By tfDescription = By.id("description");
    By tfSubmit = By.id("submit");
    By tfCancel = By.id("cancel");
    
    // Identificateur de la page
    By page = By.id("app_edit");

    public App_editPage(WebDriver driver) throws InterruptedException {
        super(driver);
        // Vérification si on est sur la bonne page
        // WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
        //WebElement element = wait.until(); 
        
        
        
        // ATTENTION
        Thread.sleep(2000);
        if (driver.findElements(By.id("app_edit")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    // Méthodes
    public App_editPage typeName(String name) {
        driver.findElement(tfName).sendKeys(name);
        return this;
    }

    // Cancel
    public Page cancelForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(tfCancel).click();
        Page targetPage = null;
        try{
            targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
        }
        return targetPage;
    }
    
    // Submit
    public Page submitForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(tfSubmit).click();
        Page targetPage = null;
        try {
            targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
        }
        return targetPage;
    }

}
