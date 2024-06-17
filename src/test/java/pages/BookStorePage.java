package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BookStorePage {

    private SelenideElement booksTable = $(".ReactTable");

    @Step("Check that specific book is displayed on page")
    public BookStorePage checkBooksListContainBook(String bookName) {

        booksTable.shouldHave(text(bookName));

        return this;
    }

    @Step("Open specific book")
    public BookStorePage openSpecificBook(String bookName, String isbnGit) {

        $(".rt-tr a[href=\"/profile?book="+isbnGit+"\"]")
                .shouldHave(text(bookName))
                .click();

        return this;
    }

}
