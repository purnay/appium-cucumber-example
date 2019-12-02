package com.purna.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.purna.example.pages.HomePage;

public class UiAppSteps {

  private HomePage homePage;

  public UiAppSteps() {
    this.homePage = new HomePage();
  }

  @When("I open custom contacts app I give permission to {string} access to contacts")
  public void i_open_custom_contacts_app_I_give_permission_to_allow_access_to_contacts(
      String permission) {
    if (permission.equals("allow")) {
      homePage.tapAllowBtn();
    } else if (permission.equals("deny")) {
      homePage.tapDenyBtn();
    }
  }

  @Then("permission denied warning message alert is displayed")
  public void permission_denied_warning_message_alert_is_displayed() {
    String message = homePage.getDenyMessage();
    Assert.assertEquals("The app needs to access to your contacts to allow you seeing them here.",
        message);
  }

  @Then("I click OK on the warning message alert")
  public void i_click_OK_on_the_warning_message_alert() {
    homePage.tapAlertOkBtn();
  }

  @Then("I click OK button on empty contacts alert if present")
  public void i_click_OK_on_the_empty_contacts_alert() {
    if (homePage.emptyContAlertDisplayed()) {
      homePage.tapAlertOkBtn();
    }
  }

  @Then("home page is displayed")
  public void home_page_is_displayed() {
    String text = homePage.getHomePageTitle();
    Assert.assertEquals("ContactsApp", text);

  }

  @When("I click add contact button")
  public void i_click_add_contact_button() {
    homePage.tapAddContact();
  }

  @Then("I should not see any fields to add contact details due to permission denial")
  public void i_should_not_see_any_fields_to_add_contact_details_due_to_permission_denial() {
    int editableFieldCount = homePage.getEditableFields();
    Assert.assertEquals(0, editableFieldCount);
  }

  @Then("I should see fields to add contact details")
  public void i_should_see_fields_to_add_contact_details() {
    int editableFieldCount = homePage.getEditableFields();
    Assert.assertTrue("No fields present to add contact details to create a new contact",
        editableFieldCount > 0);
  }

}
