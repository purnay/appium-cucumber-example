package com.purna.example.apiactions;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeatherApiActions {

  private static Properties properties = new Properties();
  private String currentPath = System.getProperty("user.dir");

  public Response getFiveDayWeather(String cityName) {
    return getWeatherForecast("fivedaypath", cityName);
  }

  public Response getCurrentWeather(String cityName) {
    return getWeatherForecast("currentpath", cityName);
  }

  private Response getWeatherForecast(String forecastPath, String cityName) {
    Response response = null;
    try {
      InputStream input = new FileInputStream(
          currentPath + "/src/test/java/com/purna/example/configs/weatherapi.properties");
      properties.load(input);
      String baseUrl = properties.getProperty("host");
      String path = properties.getProperty(forecastPath);
      String units = properties.getProperty("tempunits");
      String apiKey = properties.getProperty("appid");
      input.close();
      properties.clear();
      response =
          given().
              param("q", cityName).
              param("units", units).
              param("APPID", apiKey).
              when().
              get(baseUrl + path).
              then().
              assertThat().
              statusCode(200).
              extract().
              response();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

}
