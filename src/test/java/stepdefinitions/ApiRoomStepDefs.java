package stepdefinitions;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.RoomPojo;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static stepdefinitions.MedunnaRoomStepDefs.roomId;
import static stepdefinitions.MedunnaRoomStepDefs.roomNumberFaker;

public class ApiRoomStepDefs {
    Response response;
    RoomPojo expectedData;
    @Given("send get request")
    public void send_get_request() {
        //Set the url
        spec.pathParams("first","api","second","rooms").
        queryParams("sort","createdDate,desc");

        //Set the expected data

        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        //response.prettyPrint();

    }
    @Then("validate body")
    public void validate_body() {
        //Do Assertion
       Object roomType = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.roomType").get(0);
       Object status = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.status").get(0);
       Object price = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.price").get(0);
       Object description = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.description").get(0);
       Object roomNumber = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.roomNumber").get(0);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("SUITE",roomType);
        Assert.assertEquals(true,status);
        Assert.assertEquals("123.0",price+"");
        Assert.assertEquals("Yaniyosun Fuat Abi",description);
        Assert.assertEquals(roomNumberFaker,roomNumber);

    }

    @Given("send get request by id")
    public void sendGetRequestById() {
        //Set the url
        spec.pathParams("first","api","second","rooms","third",roomId);

        //Set the expected data
        expectedData = new RoomPojo(roomNumberFaker,"SUITE",true,123.00,"Yaniyosun Fuat Abi");

        //Send the request and get the response
        response = given(spec).get("{first}/{second}/{third}");
        //response.prettyPrint();


    }

    @Then("validate response body")
    public void validateResponseBody() throws JsonProcessingException {
        //Do Assertion
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(),RoomPojo.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        Assert.assertEquals(expectedData.getRoomType(),actualData.getRoomType());
        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getDescription(),actualData.getDescription());
        Assert.assertEquals(expectedData.getPrice(),actualData.getPrice());


    }
}
