package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkuscoffeeshop.customerloyalty.TestUtil;
import io.quarkuscoffeeshop.customerloyalty.domain.Adjective;
import io.quarkuscoffeeshop.customerloyalty.domain.Animal;
import io.quarkuscoffeeshop.customerloyalty.domain.LoyaltyMember;
import io.quarkuscoffeeshop.customerloyalty.domain.LoyaltyMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class ApiResourceTest {

    Logger logger = LoggerFactory.getLogger(ApiResource.class);

    @InjectMock
    LoyaltyMemberRepository loyaltyMemberRepository;

    @BeforeEach
    public void setUpMocks() {

        PanacheMock.mock(Adjective.class);
        Mockito.when(Adjective.getRandomAdjectiveThatStartsWith(Mockito.anyString())).thenReturn("Flashy");

        PanacheMock.mock(Animal.class);
        Mockito.when(Animal.getRandomAnimalThatStartsWith(Mockito.anyString())).thenReturn("Fieldmouse");

        Mockito.doAnswer(new TestUtil.AssignIdToEntityAnswer(new Random().nextLong())).when(loyaltyMemberRepository).persist(any(LoyaltyMember.class));
    }


    @Test
    public void testRegisterUser() {

        try {
            Writer payloadWriter = new StringWriter();

            JsonObjectBuilder payloadBuilder = Json.createObjectBuilder()
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
                        .body("codeName", notNullValue());

        } catch (Exception e) {
            assertNull(e);
        }
    }

}