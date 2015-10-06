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
public class Edit_accountPage extends AbstractPageAMT {

    // Champs de la page edit_account
    By tfFirstLocator = By.id("firstname");
    By tfLastnameLocator = By.id("lastname");
    By tfPasswordLocator = By.id("password");
    By tfPasswordConfirmLocator = By.id("password_confirm");

    // Boutons de la page edit_account
    By bSubmit = By.id("btnConfirm");
    By bCancel = By.id("btnCancel");

    // Identificateur de la page
    By page = By.id("edit_account");

    public Edit_accountPage(WebDriver driver) {
        super(driver);
        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("page")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    public Edit_accountPage typeFirstName(String firstname) {
        driver.findElement(tfFirstLocator).sendKeys(firstname);
        return this;
    }

    public Edit_accountPage typeLasttName(String lastname) {
        driver.findElement(tfLastnameLocator).sendKeys(lastname);
        return this;
    }

    public Edit_accountPage typePwd(String pwd) {
        driver.findElement(tfPasswordLocator).sendKeys(pwd);
        return this;
    }

    public Edit_accountPage typePwdConfirm(String pwd) {
        driver.findElement(tfPasswordConfirmLocator).sendKeys(pwd);
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

    public Edit_accountPage submitFormExpectingFailure() {
        driver.findElement(bCancel).click();
        return this; //new LoginPage(driver);
    }
}
