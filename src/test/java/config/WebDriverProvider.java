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

        String remoteUrl = System.getProperty("remote");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", config.enableVNC(),
                    "enableVideo", config.enableVideo()
            ));

        Configuration.browserCapabilities = capabilities;
            System.out.println("-> Режим: Удалённый запуск через Selenoid: " + remoteUrl);
        } else {
            System.out.println("-> Режим: Локальный запуск");
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }
}
