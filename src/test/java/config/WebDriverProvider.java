package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {
    public static void config() {

        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen =  config.getHoldBrowserOpen();
        Configuration.remote = config.getRemoteUrl();

        if (System.getProperty("env") == "remote") {
            Configuration.remote = "https://"
                    + config.remoteUsername() + ":"
                    + config.remotePassword() + "@"
                    + Configuration.remote
                    + "/wd/hub";
        }


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }
}
