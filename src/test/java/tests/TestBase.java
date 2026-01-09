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

        System.out.println("-> Режим: " + (config.remote() ? "Удалённый" : "Локальный"));
        System.out.println("-> Browser: " + Configuration.browser);
        System.out.println("-> Base URL: " + Configuration.baseUrl);

    }

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