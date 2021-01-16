package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.customerloyalty.BaseRegistrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class LoyaltyMemberTest {

    @BeforeEach
    public void setUpMocks() {

        PanacheMock.mock(Adjective.class);
        Mockito.when(Adjective.getRandomAdjectiveThatStartsWith(Mockito.anyString())).thenReturn("Flashy");

        PanacheMock.mock(Animal.class);
        Mockito.when(Animal.getRandomAnimalThatStartsWith(Mockito.anyString())).thenReturn("Fieldmouse");

    }

}
