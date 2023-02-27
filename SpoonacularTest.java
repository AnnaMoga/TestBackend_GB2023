package ru.gb.lesson_3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpoonacularTest {
    static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        headers.put("apiKey", "b4e7d1545555478da98bb5a3dbe487da");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void FirstLaunchTest() {
        given()
                .headers(headers)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .then()
                .statusCode(200);
    }

    @Test
    void FirstLaunchWithLoggingTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FindDietTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?diet=Gluten%20Free&apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FindCuisineTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?cuisine=italian&apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FindExcludeIngredientsTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?excludeIngredients=eggs&apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FindMinCaloriesTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?minCalories=490&apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void CuisineTest() {
        given()
                .headers(headers)
                .log()
                .all()
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine?apiKey=b4e7d1545555478da98bb5a3dbe487da")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}

