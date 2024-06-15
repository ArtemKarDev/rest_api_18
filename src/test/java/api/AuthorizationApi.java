package api;

import models.AuthRequestModel;
import models.AuthResponseModel;
import io.qameta.allure.Step;

import static specs.TestSpecs.requestSpecification;
import static specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Authorize with API and save auth response")
    public static final AuthResponseModel getAuthResponse(AuthRequestModel credentials) {

        return given(requestSpecification)
                .body(credentials)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(statusCodeResponseSpec(200))
                .extract().as(AuthResponseModel.class);
    }

}