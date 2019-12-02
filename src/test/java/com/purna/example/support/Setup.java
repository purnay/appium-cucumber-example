package com.purna.example.support;

import io.cucumber.java.Before;
import io.appium.java_client.android.AndroidDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Setup {

  public static WebDriver driver;
  private static Properties prop = new Properties();
  private static String currentPath = System.getProperty("user.dir");

  @Before
  public void setWebDriver() throws IllegalArgumentException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    try {
      InputStream input = new FileInputStream(
          currentPath + "/src/test/java/com/purna/example/configs/androidlocal.properties");
      prop.load(input);
      String appName = prop.getProperty("app");
      String appPath = currentPath + "/src/test/java/com/purna/example/apps/" + appName;
      prop.setProperty("app", appPath);

      // set capabilities
      Enumeration<Object> enuKeys = prop.keys();
      while (enuKeys.hasMoreElements()) {
        String key = (String) enuKeys.nextElement();
        String value = prop.getProperty(key);
        capabilities.setCapability(key, value);
      }
      input.close();
      prop.clear();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
    String port = "4723";
    try {
      driver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"),
          capabilities);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

}
