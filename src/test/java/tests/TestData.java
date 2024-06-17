package tests;
import config.Tests;
import models.AuthRequestModel;

public class TestData {
    public static AuthRequestModel credentials = new AuthRequestModel(Tests.config.userLogin(), Tests.config.userPassword());

    public static final String isbnSpeakJS = "9781449365035";
    public static final String SpeakJSBookName = "Speaking JavaScript";

    public static final String isbnGit = "9781449325862";
    public static final String gitBookName = "Git Pocket Guide";
}