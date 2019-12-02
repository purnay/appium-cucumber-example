package com.purna.example.steps;

import static io.restassured.path.json.JsonPath.from;

import com.purna.example.apiactions.WeatherApiActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import io.restassured.response.Response;
import org.junit.Assert;

public class WeatherApiSteps {
  private Response response;
  private WeatherApiActions weatherApiActions;
  private String city;

  public WeatherApiSteps() {
    this.weatherApiActions = new WeatherApiActions();
  }

  @Given("I send get request to 5 day 3 hour forecast data API for the {string} city")
  public void i_send_get_request_to_day_hour_forecast_data_API_for_the_city(String cityName) {
    response = weatherApiActions.getFiveDayWeather(cityName);
    Assert.assertEquals(cityName.substring(0, cityName.indexOf(",")), response.path("city.name"));
  }

  @When("weather details for five days in future at 3 hour intervals for each day are retrieved in response")
  public void weather_details_for_five_days_in_future_at_hour_intervals_for_each_day_are_retrieved_in_response() {
    // Verify that forecast items returned for five days
    List<String> dateTimes = from(response.asString()).getList("list.dt_txt");
    Assert.assertEquals(40, dateTimes.size());
    LocalDate currentDate = LocalDate.now();
    LocalDate maxDateReturned = LocalDate.parse(dateTimes.get(39).substring(0, 10));
    Assert.assertTrue(maxDateReturned.compareTo(currentDate) >= 5);
  }

  @Then("I can find the hottest day for each {string} in the coming five days")
  public void i_can_find_the_hottest_day_for_each_city_in_the_coming_five_days(String cityName) {
    String dateOfMaxTemp = response.path("list.max { it.main.temp_max }.dt_txt");
    String maxTemp = response.path("list.main.max { it.temp_max }.temp_max").toString();
    System.out.println(
        System.lineSeparator() + "Hottest date and time for city " + cityName + " is "
            + dateOfMaxTemp);
    System.out.println("Predicted temperature at that time would be " + maxTemp + " degrees.");
  }

  @When("I send get request to current weather data API for {string} city")
  public void i_send_get_request_to_current_weather_data_API_for_city(String cityName) {
    this.city = cityName;
    response = weatherApiActions.getCurrentWeather(cityName);
    Assert.assertEquals(cityName.substring(0, cityName.indexOf(",")), response.path("name"));
  }

  @Then("weather details for current day of the city are received in response")
  public void weather_details_for_current_day_of_the_city_are_received_in_response() {
    int apiTime = response.path("dt");
    Date apiUnixTime = new Date((long)apiTime * 1000L);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(apiUnixTime);
    Assert.assertEquals(currentDate, (LocalDate.now().toString()));
  }

  @Then("I can find the minimum and maximum temperature in the city")
  public void i_can_find_the_minimum_and_maximum_temperature_in_the_city() {
    String minTemp = response.path("main.temp_min").toString();
    String maxTemp = response.path("main.temp_max").toString();
    System.out.println(
        "Minimum and maximum temperature in the city of " + city + " are " + minTemp + " and "
            + maxTemp + " degrees respectively.");
  }

}
