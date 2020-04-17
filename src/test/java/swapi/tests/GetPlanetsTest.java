package swapi.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPlanetsTest extends BaseTest {

    @Test
    public void readAllPlanets() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("results.name");

        assertEquals(61, json.getInt("count"));
        assertEquals(10, names.size());
    }

    @Test
    public void readPlanet() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Tatooine", json.getString("name"));
        assertEquals(5, json.getList("films").size());
    }

}
