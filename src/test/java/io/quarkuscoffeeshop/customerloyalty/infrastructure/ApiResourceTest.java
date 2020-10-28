package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.StringWriter;
import java.io.Writer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ApiResourceTest {

    Logger logger = LoggerFactory.getLogger(ApiResource.class);

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testRegisterUser() {

        try {
            Writer payloadWriter = new StringWriter();

            JsonObjectBuilder payloadBuilder = Json.createObjectBuilder()
                    .add("name", "Mickey Mouse")
                    .add("email", "mickey@disney.com");

            JsonObject jsonObject = payloadBuilder.build();

            Json.createWriter(payloadWriter).write(jsonObject);
            String payload = payloadWriter.toString();
            logger.info(payload);
            given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .post("/api/register")
                        .then()
                        .statusCode(201)
                        .assertThat()
                        .body("codename", notNullValue());

        } catch (Exception e) {
            assertNull(e);
        }
    }

}