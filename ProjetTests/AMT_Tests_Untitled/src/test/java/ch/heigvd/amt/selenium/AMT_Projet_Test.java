/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium;

import ch.heigvd.amt.selenium.pages.HomePage;
import ch.heigvd.amt.selenium.pages.LoginPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author thsch
 */
public class AMT_Projet_Test {

    private String baseUrl = "localhost:8080/AMT_Projet_Untitled";
    private WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        //driver = new ChromeDriver();
    }

    @Test
    //@ProbeTest(tags = "WebUI")
    public void canNotLogInWithInvalidEmail() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeName("this is not a valid email address");
        loginPage.typePwd("any password");
        loginPage.submitFormExpectingFailure();
    }
    
    @Test
    public void  canLoginWithValidEmail(){
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeName("asdf@adsaf.asdf");
        loginPage.typePwd("any");
        HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
    }


    @After
    public void closeBrowser() {
        driver.close();
    }
}
