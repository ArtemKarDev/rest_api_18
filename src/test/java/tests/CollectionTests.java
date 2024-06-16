package tests;

import api.BooksApi;
import models.AddBookRequestModel;
import models.AuthResponseModel;
import models.DeleteOneBookModel;
import models.IsbnModel;
import extensions.WithLogin;
import io.qameta.allure.Feature;
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
    AddBookRequestModel addBook = new AddBookRequestModel();
    DeleteOneBookModel deleteOneBookData = new DeleteOneBookModel();

    @Test
    @DisplayName("Add one book in profile")
    @WithLogin
    @Tag("collectionBooks")
    void addBookInCollection() {

        booksApi.deleteAllBooks(authResponse);

        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnSpeakJSBook);
        addBook.setCollectionOfIsbns(isbnList);
        addBook.setUserId(authResponse.getUserId());
        booksApi.addBook(authResponse, addBook);

        profilePage.openPage()
                .checkUser(authResponse.getUsername())
                .checkBooksListContainBook(SpeakJSBookName);
    }

    @Test
    @DisplayName("Delete one book from profile")
    @WithLogin
    @Tag("collectionBooks")
    void deleteBookFromCollection() {

        booksApi.deleteAllBooks(authResponse);

        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnGitBook);
        addBook.setCollectionOfIsbns(isbnList);
        addBook.setUserId(authResponse.getUserId());
        booksApi.addBook(authResponse, addBook);

//        deleteOneBookData.setUserId(authResponse.getUserId());
//        deleteOneBookData.setIsbn(isbnGit);
//        booksApi.deleteOneBook(authResponse, deleteOneBookData);

        profilePage.openPage()
                .checkUser(authResponse.getUsername())
                .checkBooksListContainBook(gitBookName)
                .deleteSpecificBook(gitBookName,isbnGit)
                .checkBooksListDoesNotContainBook(gitBookName);
    }

}