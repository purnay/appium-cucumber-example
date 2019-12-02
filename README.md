# appium-cucumber-example
Example of Appium, Cucumber with Java


This project has sample tests to demonstrate the automation of mobile navigation of a native app on Android platform and couple of automated tests for API calls. 
Tests are written in Java 8 using Selenium WebDriver, Appium with Cucumber framework.
REST Assured library is used for automation of API tests. Both UI and API tests share same cucumber framework with separate test runner and feature file for each. 

## Prerequisites

- Node version 8.11.0 (for Appium)
- JDK 1.8
- Appium 1.15.1
- Android emulator (Android version 9 or 10). Make sure that Chrome driver version available in Appium is compatible with Chrome browser version of emulator.

## Quick Start

### Mobile App UI tests
- Clone this repo and build the project using maven (macOS)
- Change Android emulator capabilities including platform version in the properties file `androidlocal.properties` 
- Start Appium server and emulators
- Open the project in an IDE and execute the `RunUiFeatures` runner
- One of the scenarios will be failed as test expects to add a contact in the app, but app has no such feature
- Test results (cucumber) report is available including screenshots for failed tests in directory `target/cucumber-reports/` (open the file `index.html`) 

### RESTful API tests
- Couple of APIs (5 day weather forecast and current day forecast by a city) from OpenWeather are used in the tests
- Please create a test account at https://openweathermap.org/ and get API key to access above two free APIs
- Update the property `appid` in the properties file `weatherapi.properties` with your API key
- Execute the API tests from the runner `RunApiFeatures`
- All API tests should pass and report can be retrieved from same reports directory