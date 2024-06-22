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
        SelenoidAuthConfig selenoidAuthConfig = ConfigFactory.create(SelenoidAuthConfig.class, System.getProperties());

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen =  config.getHoldBrowserOpen();

        if ("remote".equals(System.getProperty("env"))) {
            Configuration.remote = "https://"
                    + selenoidAuthConfig.getRemoteUsername() + ":"
                    + selenoidAuthConfig.getRemotePassword() + "@"
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
