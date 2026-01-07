package tests;

import api.BooksApi;
import models.AddBookRequestModel;
import models.AuthResponseModel;
import models.DeleteOneBookModel;
import models.IsbnModel;
import extensions.WithLogin;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static api.AuthorizationApi.getAuthResponse;
import static tests.TestData.*;

@Feature("Profile tests on demoqa.com")
public class CollectionTests extends TestBase {
    AuthResponseModel authResponse = getAuthResponse(credentials);
    BooksApi booksApi = new BooksApi();
    ProfilePage profilePage = new ProfilePage();
    IsbnModel isbnGitBook = new IsbnModel(isbnGit);
    IsbnModel isbnSpeakJSBook = new IsbnModel(isbnSpeakJS);
    AddBookRequestModel addBookRequestData = new AddBookRequestModel();
    DeleteOneBookModel deleteOneBookRequestData = new DeleteOneBookModel();

    @Test
    @DisplayName("Delete all books from profile with button Delete all")
    @WithLogin
    @Tag("collectionBooks")
    void addBookInCollection() {

        booksApi.deleteAllBooks(authResponse);

        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnSpeakJSBook);
        isbnList.add(isbnGitBook);
        addBookRequestData.setCollectionOfIsbns(isbnList);
        addBookRequestData.setUserId(authResponse.getUserId());
        booksApi.addBook(authResponse, addBookRequestData);

        profilePage.openProfilePage()
                .checkUser(authResponse.getUsername())
                .checkBooksListContainBook(SpeakJSBookName)
                .deleteAllBooks()
                .checkBooksListDoesNotContainBook(gitBookName);
    }

    @Test
    @DisplayName("Delete one book from profile")
    @WithLogin
    @Tag("collectionBooks")
    void deleteBookFromCollection() {

        booksApi.deleteAllBooks(authResponse);

        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnGitBook);
        addBookRequestData.setCollectionOfIsbns(isbnList);
        addBookRequestData.setUserId(authResponse.getUserId());
        booksApi.addBook(authResponse, addBookRequestData);

        profilePage.openProfilePage()
                .checkUser(authResponse.getUsername())
                .checkBooksListContainBook(gitBookName)
                .deleteSpecificBook(gitBookName,isbnGit)
                .checkBooksListDoesNotContainBook(gitBookName);
    }

}