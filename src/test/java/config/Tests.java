package config;

import org.aeonbits.owner.ConfigFactory;

public class Tests {
    public static TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());
}
