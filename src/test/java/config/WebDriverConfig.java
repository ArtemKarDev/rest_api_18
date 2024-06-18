package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${env}.properties"})
public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browser")
    String getBrowser();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("pageLoadStrategy")
    String getPageLoadStrategy();

    @Key("holdBrowserOpen")
    @DefaultValue("false")
    Boolean getHoldBrowserOpen();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("remote.user")
    @DefaultValue("user1")
    String remoteUsername();

    @Key("remote.password")
    @DefaultValue("1234")
    String remotePassword();

}
