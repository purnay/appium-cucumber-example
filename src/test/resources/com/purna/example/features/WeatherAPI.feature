Feature: REST API - Operations

  @apiTest1
  Scenario Outline: Using Open Weather API, get 5 day weather forecast for given number of cities
    Given I send get request to 5 day 3 hour forecast data API for the "<cityName>" city
    When weather details for five days in future at 3 hour intervals for each day are retrieved in response
    Then I can find the hottest day for each "<cityName>" in the coming five days
    Examples:
      | cityName     |
      | London,uk    |
      | Cardiff,uk   |
      | Belfast,uk   |
      | Edinburgh,uk |
      | Slough,uk    |

  @apiTest2
  Scenario: Using Open Weather API, get the minimum and maximum temperature in a city
    When I send get request to current weather data API for "Los Angeles,us" city
    Then weather details for current day of the city are received in response
    And I can find the minimum and maximum temperature in the city




