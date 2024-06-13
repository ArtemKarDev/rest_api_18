import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import helpers.Attach;
import io.qameta.allure.internal.shadowed.jackson.databind.cfg.ConfigFeature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {





    @BeforeAll
    static void setUpConfig() {

        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "101");
        String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
        String windowSize = System.getProperty("windowSize", "1920x1080");

        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.browserSize = windowSize;
        Configuration.remote = "https://user1:1234@" + wdHost + "/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //пожалуйста, позвольте оставить эту строчку , хоть в каком нибудь виде
        //Configuration.holdBrowserOpen = true;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void closeUp() {
        closeWebDriver();
    }

    @AfterEach
    void addAttachments(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        Attach.addVideo();
    }
}