package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/tests.properties"
})

public interface AuthConfig extends Config {

    @Key("userLogin")
    String getUserLogin();

    @Key("userPassword")
    String getUserPassword();


}
