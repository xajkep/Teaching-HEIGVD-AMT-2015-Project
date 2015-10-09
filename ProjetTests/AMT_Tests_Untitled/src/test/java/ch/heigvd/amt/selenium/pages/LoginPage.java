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

    By tfEmail = By.id("inputEmail");
    By tfPwd = By.id("inputPassword");

    By bSubmit = By.id("btnConfirm");

    public LoginPage(WebDriver driver) {
        super(driver);
        // Vérification si on est sur la bonne page
        // Check that we're on the right page.
        if (!"Login Page".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    // Méthodes
    public LoginPage typeName(String email) {
        driver.findElement(tfEmail).sendKeys(email);
        return this;
    }

    public LoginPage typePwd(String pwd) {
        driver.findElement(tfPwd).sendKeys(pwd);
        return this;
    }

    public Page submitForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(bSubmit).click();
        Page targetPage = null;
        try {
            targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
        }
        return targetPage;
    }

    public LoginPage submitFormExpectingFailure() {
        driver.findElement(bSubmit).click();
        return this; //new LoginPage(driver);
    }

}
