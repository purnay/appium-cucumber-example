Feature: To verify features of Android native app

  @uiTest1
  Scenario: Navigation and validation of screens of custom Contacts app when permission not given to contacts
    When I open custom contacts app I give permission to "deny" access to contacts
    Then permission denied warning message alert is displayed
    And I click OK on the warning message alert
    Then home page is displayed
    When I click add contact button
    Then I should not see any fields to add contact details due to permission denial

  @uiTest2
  Scenario: Able to add a contact in custom Contacts app when permission given to contacts
    When I open custom contacts app I give permission to "allow" access to contacts
    And I click OK button on empty contacts alert if present
    Then home page is displayed
    When I click add contact button
    Then I should see fields to add contact details