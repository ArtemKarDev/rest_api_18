package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.function.Supplier;

public class WebDriverProvider  {
    private WebDriverConfig config;
    public static void config() {

        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        SelenoidAuthConfig selenoidAuthConfig = ConfigFactory.create(SelenoidAuthConfig.class, System.getProperties());

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen = config.getHoldBrowserOpen();

        if (config.getRemote()) {
            String remoteUrl = "https://" + selenoidAuthConfig.getRemoteUsername() + ":" +
                    selenoidAuthConfig.getRemotePassword() + "@" +
                    config.getRemoteUrl() + "/wd/hub";
            Configuration.remote = remoteUrl;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", config.getBrowser());
            capabilities.setCapability("browserVersion", config.getBrowserVersion());

            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", config.enableVNC(),
                    "enableVideo", config.enableVideo()
            ));

        Configuration.browserCapabilities = capabilities;
            System.out.println("-> Remote mode: Selenoid " + remoteUrl);
        } else {
            System.out.println("-> Local mode");
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }
}
