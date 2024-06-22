package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${env}.properties",
        "classpath:config/local.properties"
})
public interface SelenoidAuthConfig extends Config {
    @Key("remoteUser")
    //@DefaultValue("user1")
    String getRemoteUsername();

    @Key("remotePassword")
    //DefaultValue("1234")
    String getRemotePassword();





}
