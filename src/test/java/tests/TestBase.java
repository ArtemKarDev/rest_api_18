package tests;

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

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @Step("Open site in browser")
    @BeforeAll
    static void setUpConfig() {

        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.config();

        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUrl();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Step("Attach video, screenshot and browser logs. Close browser. ")
    @AfterEach
    void addAttachments(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!WebDriverRunner.isFirefox()) {
            Attach.browserConsoleLogs();
        }

        Attach.addVideo();
        closeWebDriver();
    }
}