package tests;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import config.WebDriverProvider;
import helpers.Attach;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    //private WebDriver driver = new WebDriverProvider().get();

    @Step("Open site in browser")
    @BeforeAll
    static void setUpConfig() {

        // убрал конфигурацию в webDriverProvider
//        String browser = System.getProperty("browser", "chrome");
//        String version = System.getProperty("webDriverVersion", "122.0");
//        String windowSize = System.getProperty("browserWindowSize", "1920x1080");
//        String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
//        Configuration.remote = "https://user1:1234@" + wdHost + "/wd/hub";
//        Configuration.browser = browser;
//        Configuration.browserVersion = version;
//        Configuration.browserSize = windowSize;
//        Configuration.baseUrl = "https://demoqa.com";

        WebDriverProvider webDriverProvider = new WebDriverProvider();
        webDriverProvider.config();

        RestAssured.baseURI = "https://demoqa.com";

        //пожалуйста, позвольте оставить эту строчку
        //Configuration.holdBrowserOpen = true;

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//
//        Configuration.browserCapabilities = capabilities;
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @Step("Attach video, screenshot and browser logs. Close browser. ")
    @AfterEach
    void addAttachments(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!WebDriverRunner.isFirefox()) {
            Attach.browserConsoleLogs();
        }
        Attach.browserConsoleLogs();

        Attach.addVideo();
        closeWebDriver();
    }
}