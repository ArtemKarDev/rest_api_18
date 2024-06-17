package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/tests.properties"
})

public interface TestsConfig extends Config {
    String webUrl();

    String apiUrl();

    String userLogin();

    String userPassword();

    String userToken();

}
