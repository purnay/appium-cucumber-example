package com.purna.example.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

  private WebDriver driver;

  public Wait(WebDriver driver) {
    this.driver = driver;
  }

  public void
  forLoading(int timeout) {
    ExpectedCondition<Object> condition = ExpectedConditions
        .jsReturnsValue("return document.readyState==\"complete\";");
    String timeoutMessage = "Page didn't load after " + Integer.toString(timeout) + " seconds.";
    waitUntilCondition(condition, timeoutMessage, timeout);
  }

  public void forElementToBeDisplayed(int timeout, WebElement webElement, String webElementName) {
    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
    String timeoutMessage =
        webElementName + " wasn't displayed after " + Integer.toString(timeout) + " seconds.";
    waitUntilCondition(condition, timeoutMessage, timeout);
  }

  private void
  waitUntilCondition(ExpectedCondition condition, String timeoutMessage, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.withMessage(timeoutMessage);
    wait.until(condition);
  }

}

