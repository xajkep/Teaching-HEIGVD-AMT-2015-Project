/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This is an abstract page, which represents a page served by the application
 * and visited by the user. The Page Object Pattern is a best practice for writing
 * Selenium tests. It is documented here:
 * 
 * - https://code.google.com/p/selenium/wiki/PageObjects
 * - http://www.seleniumhq.org/docs/06_test_design_considerations.jsp#page-object-design-pattern
 * 
 * 
 * @author Olivier Liechti
 */
public abstract class Page {

  final WebDriver driver;

  public Page(WebDriver driver) {
    this.driver = driver;
  }

}

