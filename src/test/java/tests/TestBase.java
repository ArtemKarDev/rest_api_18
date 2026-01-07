package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import config.WebDriverConfig;
import config.WebDriverProvider;
import helpers.Attach;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @Step("Open site in browser")
    @BeforeAll
    static void setUpConfig() {

        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.config();

        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUrl();

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "127.0");
        Configuration.browserSize = System.getProperty("windowSize", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("remote");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.headless = false;


    }

//    @Step("Attach video, screenshot and browser logs. Close browser. ")
//    @AfterEach
//    void addAttachments(){
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        if (!WebDriverRunner.isFirefox()) {
//            Attach.browserConsoleLogs();
//        }
//
//        Attach.addVideo();
//        closeWebDriver();
//    }

    @BeforeEach
    void beforeEach(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void closeUp() {
        closeWebDriver();
    }

    @AfterEach
    void tearDown() {
        // Очистка cookies после каждого теста
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterEach
    void addAttachments(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}