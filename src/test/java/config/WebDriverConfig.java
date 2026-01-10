package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${env}.properties",
        "classpath:config/local.properties",
        "classpath:config/auth.properties"
})

public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("127.0")
    String getBrowserVersion();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("holdBrowserOpen")
    @DefaultValue("false")
    Boolean getHoldBrowserOpen();

    @Key("remoteUrl")
    @DefaultValue("")
    String getRemoteUrl();

    @Key("remote")
    @DefaultValue("false")
    boolean getRemote();

    @Key("env")
    @DefaultValue("local")
    String getEnv();

    @Key("enableVNC")
    @DefaultValue("false")
    boolean enableVNC();

    @Key("enableVideo")
    @DefaultValue("false")
    boolean enableVideo();

}
