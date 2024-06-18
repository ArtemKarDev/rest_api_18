package config;

import org.aeonbits.owner.Config;


@Config.Sources({"classpath:config/${env}.properties"})
public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com/")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("remoteUrl")
    //@DefaultValue("http://localhost:4444")
    String getRemoteUrl();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("122.0")
    String getBrowserVersion();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("holdBrowserOpen")
    @DefaultValue("false")
    Boolean getHoldBrowserOpen();

}
