package ch.heigvd.amt.selenium.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author thsch
 */
public class RegisterPage extends AbstractPageAMT {
    // Identificateurs des éléments

    // ATTENTION
    // ATTENTION -> login particulier, pas d'identificateur de page voir avec Benoit
    // ATTENTION
    // Identificateur de la page
    By page = By.id("register");
    //###############################
    
    By tfName = By.id("name");
    By tfSurname = By.id("surname");
    By tfMail = By.id("email");
    By tfPwd = By.id("password");
    By tfPwd_conf = By.id("password_conf");
    By tfCancel = By.id("cancel");
    By tfSubmit = By.id("submit");

    public RegisterPage(WebDriver driver) {
        super(driver);
        // Vérification si on est sur la bonne page
        if (driver.findElements(By.id("page")).isEmpty()) {
            throw new IllegalStateException("This is not the correct page");
        }
    }

    // Méthodes
    public RegisterPage typeName(String name) {
        driver.findElement(tfName).sendKeys(name);
        return this;
    }

    public RegisterPage typeSurname(String surname) {
        driver.findElement(tfSurname).sendKeys(surname);
        return this;
    }

    public RegisterPage typeMail(String mail) {
        driver.findElement(tfMail).sendKeys(mail);
        return this;
    }

    public RegisterPage typePwd(String pwd) {
        driver.findElement(tfSurname).sendKeys(pwd);
        return this;
    }

    public RegisterPage typePwd_conf(String pwd) {
        driver.findElement(tfSurname).sendKeys(pwd);
        return this;
    }

    // Cancel
    public Page cancelForm(Class<? extends Page> expectedPageClass) {
        driver.findElement(tfCancel).click();
        Page targetPage = null;
        try {
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
