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
public abstract class AbstractPageAMT extend Page{
    /**
   * These locators are used to find elements in the web page. In the code, 'id'
   * is the HTML id attribute that is defined in the page markup. This shows
   * that when you write automated tests with Selenium, you have to prepare your
   * HTML code so that important elements have an id (which can then be
   * referenced in the test). Note that there are other types of locators. See:
   * http://www.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
   * 
   * En gros vu que c'est la classe abstraite on met les éléments de menu (header footer etc) ici.
   */
  By menuLocator = By.id("menu_button");
  By menuOption1 = By.id("option1");
  

  public AbstractPageAMT(WebDriver driver) {
    super(driver);
  }

  /**
   * This method illustrates two aspects of the Page Object pattern.
   *
   * Firstly, notice that we expose a user intent (he wants to go to the Beers
   * List page by selecting the proper item in the navigation menu), but we hide
   * the details (when calling this method, you don't need to know about HTML
   * IDs and other implementation details that could change). It makes the tests
   * easier to read and more robust (if an HTML ID changes, you only need to
   * change the Page and not all the tests that use it).
   *
   * Secondly, notice that we return a page object. It is a new instance of the
   * page where we should land after this action is performed. 
   *
   * @return the Beer page
   */
  public BeersPage goToBeersPageViaMenu() {
    driver.findElement(menuExamplesLocator).click();
    driver.findElement(menuItemBeersLocator).click();
    return new BeersPage(driver);
  }

  public AJAXPage goToAJAXPageViaMenu() {
    driver.findElement(menuExamplesLocator).click();
    driver.findElement(menuItemAJAXPageLocator).click();
    return new AJAXPage(driver);
  }

  public GenerateTestDataPage goToGenerateTestDataPageViaMenu() {
    driver.findElement(menuExamplesLocator).click();
    driver.findElement(menuItemGenerateTestDataLocator).click();
    return new GenerateTestDataPage(driver);
  }

  public CorporateInformationPage goToCorporateInformationPageViaMenu() {
    driver.findElement(menuExamplesLocator).click();
    driver.findElement(menuItemShowCorporateInformationLocator).click();
    return new CorporateInformationPage(driver);
  }

}
