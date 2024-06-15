
package api;

import models.AddBookRequestModel;
import models.AuthResponseModel;
import models.DeleteOneBookModel;
import io.qameta.allure.Step;

import static specs.TestSpecs.requestSpecification;
import static specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class BooksApi {

    @Step("Add book to profile")
    public void addBook(AuthResponseModel authResponse, AddBookRequestModel addBooks) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(addBooks)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(201));
    }

    @Step("Delete one specific book from profile")
    public void deleteOneBook(AuthResponseModel authResponse, DeleteOneBookModel deleteOneBookData) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(deleteOneBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(statusCodeResponseSpec(204));
    }

    @Step("Delete all books from profile")
    public void deleteAllBooks(AuthResponseModel authResponse) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParam("UserId", authResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(204));
    }

}
