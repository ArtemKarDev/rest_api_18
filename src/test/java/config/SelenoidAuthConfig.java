package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/selenoid.properties"
})
public interface SelenoidAuthConfig extends Config {
    @Key("remoteUser")
    String getRemoteUsername();

    @Key("remotePassword")
    String getRemotePassword();





}
