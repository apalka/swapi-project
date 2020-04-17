package swapi.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVehiclesTest extends BaseTest {

    @Test
    public void readAllVehicles() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("results.name");

        assertEquals(39, json.getInt("count"));
        assertEquals(10, names.size());
    }

    @Test
    public void readVehicle() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES + "/4")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("Sand Crawler", json.getString("name"));
        assertEquals(2, json.getList("films").size());
    }

}
