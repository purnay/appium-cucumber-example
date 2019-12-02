package com.purna.example.pages;

import java.util.List;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

  @FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
  private WebElement alertAllowBtn;

  @FindBy(id = "com.android.packageinstaller:id/permission_deny_button")
  private WebElement alertDenyBtn;

  @FindBy(id = "android:id/message")
  private WebElement alertDeniedMessage;

  @FindBy(id = "android:id/button1")
  private WebElement alertOkBtn;

  @FindBy(id = "com.donato.contactsapp:id/fab")
  private WebElement addContactBtn;

  @FindBy(className = "android.widget.EditText")
  private List<WebElement> editableTextFields;

  @FindBy(xpath = "//android.view.ViewGroup/android.widget.TextView")
  private WebElement homePageToolBar;

  @FindBy(className = "android.widget.TextView")
  private WebElement homePageTitle;

  @FindBy(id = "android:id/message")
  private WebElement noContactsAlertMsg;


  public HomePage() {
    PageFactory.initElements(driver, this);
  }

  public void tapAllowBtn() {
    alertAllowBtn.click();
  }

  public void tapDenyBtn() {
    alertDenyBtn.click();
  }

  public void tapAlertOkBtn() {
    alertOkBtn.click();
  }

  public void tapAddContact() {
    wait.forElementToBeDisplayed(5, addContactBtn, "Add contact button");
    addContactBtn.click();
  }

  public int getEditableFields() {
    return editableTextFields.size();
  }

  public String getDenyMessage() {
    wait.forElementToBeDisplayed(5, alertDeniedMessage, "Alert denied message");
    return alertDeniedMessage.getText();
  }

  public boolean emptyContAlertDisplayed() {
    try {
      wait.forElementToBeDisplayed(5, noContactsAlertMsg, "Empty alert message");
      return (noContactsAlertMsg.isDisplayed());
    } catch (TimeoutException e) {
      return false;
    }
  }

  public String getHomePageTitle() {
    try {
      wait.forElementToBeDisplayed(5, homePageToolBar, "Homepage tool bar");
      return homePageTitle.getText();
    } catch (TimeoutException e) {
      return null;
    }
  }

}
