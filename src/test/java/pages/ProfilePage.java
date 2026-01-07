package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private SelenideElement booksTable = $(".ReactTable"),
            userNameField = $("#userName-value"),
            bookDeleteButton = $("#delete-record-undefined"),
            allBooksDeleteButton = $x("//button[@id='submit' and contains(text(), 'Delete All Books')]");

    @Step("Open profile page")
    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }


    @Step("Check username on profile page")
    public ProfilePage checkUser(String userName) {
        userNameField.shouldHave(text(userName));

        return this;
    }

    @Step("Check that specific book is displayed in profile")
    public ProfilePage checkBooksListContainBook(String bookName) {

        booksTable.shouldHave(text(bookName));

        return this;
    }

    @Step("Delete specific book")
    public ProfilePage deleteSpecificBook(String bookName, String isbnGit) {

        $(".rt-tr a[href=\"/profile?book="+isbnGit+"\"]").shouldHave(text(bookName));
        bookDeleteButton.click();
        $("#closeSmallModal-ok").click();

        return this;
    }

    @Step("Delete all books")
    public ProfilePage deleteAllBooks() {
        allBooksDeleteButton.click();
        $("#closeSmallModal-ok").click();
        return this;
    }

    @Step("Check that specific book is not displayed in profile")
    public ProfilePage checkBooksListDoesNotContainBook(String bookName) {

        booksTable.shouldNotHave(text(bookName));

        return this;
    }


}