package ru.gb.lesson_4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.lessThan;

public abstract class AbstractClass {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    protected static String username;
    private static String hash;
    protected static ResponseSpecification responseSpec;
    protected static RequestSpecification requestSpec;

    @BeforeAll
    static void beforeAll() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/test/resources/application.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        username = prop.getProperty("username");
        hash = prop.getProperty("hash");

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(2000L))
                .expectContentType("application/json")
                .expectHeader("Connection", "keep-alive")
                .build();

        requestSpec = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("includeNutrition", "false")
                .addQueryParam("hash", hash)
                .addPathParam("username", username)

                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;
        RestAssured.requestSpecification = requestSpec;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public RequestSpecification getRequestSpec(){
        return requestSpecification;
    }
}
