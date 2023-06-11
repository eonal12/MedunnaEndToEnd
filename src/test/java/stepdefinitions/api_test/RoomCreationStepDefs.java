package stepdefinitions.api_test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.RoomPojo;

import java.util.HashMap;
import java.util.Map;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RoomCreationStepDefs {

    int roomNumber;
    Response response;
    Map<String,Object> expextedDataMap;
    RoomPojo expectedDataPojo;


    @Given("send post request for creating room")
    public void send_post_request_for_creating_room() {
        //Set the url
        spec.pathParams("first","api","second","rooms");
        roomNumber = new Faker().number().numberBetween(100000,1000000);

        //Set the expected data Burada şimdiye kadar öğrendiğimiz tüm yöntemleri kullanıcaz

        //1.Yol: String ile -->Tavsiye edilmez çünkü String içerisinden spesific veri alamayız, Ama bazen kolay olması açısından kullanırız
                                //Örnek olarak generateToken methodunda spesific veri almamıza gerek yok o yüzden String kullandık

        String expextedData ="{\"description\": \"Emre\",\"price\": 469,\"roomNumber\": "+roomNumber+",\"roomType\": \"TWIN\",\"status\": true}";

        //2.Yol Map ile

        expextedDataMap = new HashMap<>();
        expextedDataMap.put("description","Emre");
        expextedDataMap.put("price",469);
        expextedDataMap.put("roomNumber",roomNumber);
        expextedDataMap.put("roomType","TWIN");
        expextedDataMap.put("status",true);

        //3.Yol: Pojo Class

        expectedDataPojo = new RoomPojo(roomNumber,"TWIN",true,469.0,"Emre");
        System.out.println("expectedDataPojo = " + expectedDataPojo);


        //Send the request and get the response
        response = given(spec).body(expectedDataPojo).post("{first}/{second}");
        response.prettyPrint();


    }
    @Then("get the response and validate")
    public void get_the_response_and_validate() throws JsonProcessingException {
        //Do Assertion

        //1.Yol: then() methodu + HamcrestMatcher

        response
                .then()
                .statusCode(201)
                .body("roomNumber",equalTo(roomNumber),
                        "roomType",equalTo("TWIN"),
                        "status",equalTo(true),
                        "price",equalTo(469.0F),
                        "description",equalTo("Emre"));

        //2.Yol: Json Path ile

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(roomNumber,jsonPath.getInt("roomNumber"));
        Assert.assertEquals("TWIN",jsonPath.getString("roomType"));
        Assert.assertTrue(jsonPath.getBoolean("status"));
        Assert.assertEquals("469.0",jsonPath.getDouble("price")+"");
        Assert.assertEquals("Emre",jsonPath.getString("description"));

        //3.Yol : Map ile
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expextedDataMap.get("roomNumber")+".0",actualData.get("roomNumber")+"");
        Assert.assertEquals(expextedDataMap.get("roomType"),actualData.get("roomType"));
        Assert.assertEquals(expextedDataMap.get("status"),actualData.get("status"));
        Assert.assertEquals(expextedDataMap.get("price")+".0",actualData.get("price")+"");
        Assert.assertEquals(expextedDataMap.get("description"),actualData.get("description"));

        //4.Yol Pojo Class ile

        RoomPojo actualDataPojo = response.as(RoomPojo.class);

        Assert.assertEquals(expectedDataPojo.getRoomNumber(),actualDataPojo.getRoomNumber());
        Assert.assertEquals(expectedDataPojo.getRoomType(),actualDataPojo.getRoomType());
        Assert.assertEquals(expectedDataPojo.getStatus(),actualDataPojo.getStatus());
        Assert.assertEquals(expectedDataPojo.getPrice(),actualDataPojo.getPrice());
        Assert.assertEquals(expectedDataPojo.getDescription(),actualDataPojo.getDescription());

        //5.Yol Pojo Class + ObjectMapper ile ----> En çok tavsiye edilen

        RoomPojo actualDataPojoMapper = new ObjectMapper().readValue(response.asString(),RoomPojo.class);

        Assert.assertEquals(expectedDataPojo.getRoomNumber(),actualDataPojoMapper.getRoomNumber());
        Assert.assertEquals(expectedDataPojo.getRoomType(),actualDataPojoMapper.getRoomType());
        Assert.assertEquals(expectedDataPojo.getStatus(),actualDataPojoMapper.getStatus());
        Assert.assertEquals(expectedDataPojo.getPrice(),actualDataPojoMapper.getPrice());
        Assert.assertEquals(expectedDataPojo.getDescription(),actualDataPojoMapper.getDescription());

        //6.Yol: Pojo Class + Gson

        RoomPojo actualDataPojoGson =new Gson().fromJson(response.asString(), RoomPojo.class);

        Assert.assertEquals(expectedDataPojo.getRoomNumber(),actualDataPojoGson.getRoomNumber());
        Assert.assertEquals(expectedDataPojo.getRoomType(),actualDataPojoGson.getRoomType());
        Assert.assertEquals(expectedDataPojo.getStatus(),actualDataPojoGson.getStatus());
        Assert.assertEquals(expectedDataPojo.getPrice(),actualDataPojoGson.getPrice());
        Assert.assertEquals(expectedDataPojo.getDescription(),actualDataPojoGson.getDescription());
    }
}
