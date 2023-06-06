package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;

public class ApiRoomStepDefs {
    @Given("send get request")
    public void send_get_request() {
        //Set the url
        spec.pathParams("first","api","second","rooms").
        queryParams("sort","createdDate,desc");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

    }
    @Then("validate body")
    public void validate_body() {
        //Do Assertion

    }
}
