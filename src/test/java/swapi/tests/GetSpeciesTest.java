package swapi.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSpeciesTest extends BaseTest {

    @Test
    public void readAllSpecies() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + SPECIES)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("results.name");

        assertEquals(37, json.getInt("count"));
        assertEquals(10, names.size());
    }

    @Test
    public void readSpecies() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + SPECIES + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Human", json.getString("name"));
        assertEquals(7, json.getList("films").size());
    }

}
