package tests;
import config.AuthConfig;
import models.AuthRequestModel;
import org.aeonbits.owner.ConfigFactory;

public class TestData {

    public static AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
    public static AuthRequestModel credentials = new AuthRequestModel(authConfig.getUserLogin(), authConfig.getUserPassword());  // authConfig.userLogin(), AuthConfig.userPassword());
    public static final String isbnSpeakJS = "9781449365035";
    public static final String SpeakJSBookName = "Speaking JavaScript";

    public static final String isbnGit = "9781449325862";
    public static final String gitBookName = "Git Pocket Guide";
}