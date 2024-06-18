package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.function.Supplier;
import java.util.Map;

//public class WebDriverProvider implements Supplier<WebDriver> {
//
//    private final WebDriverConfig config;
//
//    public WebDriverProvider() {
//        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
//    }
//
//    @Override
//    public WebDriver get() {
//        WebDriver driver = createDriver();
//        driver.get(config.getBaseUrl());
//        driver.get(config.getBrowserSize());
//        driver.get(config.getBrowserVersion());
//        driver.get(config.getRemoteUrl());
//        driver.get(config.getPageLoadStrategy());
//
//        return driver;
//    }
public class WebDriverProvider {
    public static void config() {

        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen =  config.getHoldBrowserOpen();

        if (System.getProperty("env") == "remote") {
            Configuration.remote = "https://user1:1234@" + config.getRemoteUrl() + "/wd/hub";
        }


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


    //    public WebDriver createDriver() {
    //        switch (config.getBrowser()) {
    //            case "chrome": {
    //                WebDriverManager.chromedriver().setup();
    //                return new ChromeDriver();
    //            }
    //            case "fireox": {
    //                WebDriverManager.firefoxdriver().setup();
    //                return new FirefoxDriver();
    //            }
    //            default: {
    //                throw new RuntimeException("No such driver");
    //            }
    //        }
    //    }

    }
}
