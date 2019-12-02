package com.purna.example.pages;

import com.purna.example.support.*;;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

  protected WebDriver driver;
  protected Wait wait;

  protected BasePage() {
    this.driver = Setup.driver;
    this.wait = new Wait(this.driver);
  }

  public void openUrl(String url) {
    driver.get(url);
    wait.forLoading(5);
  }

  public void clickOnLink(String linkText) {
    WebElement link = driver.findElement(By.linkText(linkText));
    wait.forElementToBeDisplayed(5, link, "Link by link text");
    link.click();
  }

}
