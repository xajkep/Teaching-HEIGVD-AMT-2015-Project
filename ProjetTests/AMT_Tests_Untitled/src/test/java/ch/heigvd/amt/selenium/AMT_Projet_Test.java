/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium;

import ch.heigvd.amt.selenium.pages.HomePage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author thsch
 */
public class AMT_Projet_Test {

    private String baseUrl = "localhost:8080/AMT_Projet/";
    private WebDriver driver;

    @Before
    public void openBrowser() {
        driver = new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", "/Users/admin/Downloads/chromedriver");
        //driver = new ChromeDriver();
    }

    @Test
    @ProbeTest(tags = "WebUI")
    public void canGoFromHomeToAccount() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.goToAccountViaMenu();
        // Je sais pas quoi mettre ici
    }

    // pas fini du tout
    @Test
    @ProbeTest(tags = "WebUI")
    public void canDoSomethingCool(){
        driver.get(baseUrl + "/register");
        
    }
    @After
    public void closeBrowser() {
        driver.close();
    }
}
