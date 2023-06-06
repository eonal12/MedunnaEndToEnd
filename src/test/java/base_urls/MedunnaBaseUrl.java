package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static utilities.AuthenticationMedunna.generateToken;


public class MedunnaBaseUrl {

    public static RequestSpecification spec;


    public static void setUp() {

        spec = new RequestSpecBuilder()
                .addHeader("Authorization","Bearer "+generateToken())
                .setBaseUri("https://medunna.com").build();

    }


}
