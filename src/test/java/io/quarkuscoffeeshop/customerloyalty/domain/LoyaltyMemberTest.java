package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class LoyaltyMemberTest {

    Logger logger = LoggerFactory.getLogger(LoyaltyMemberTest.class);

    static final String regex = "^([A-Z]){1}[a-z]*\\1[a-z]*$";


    @BeforeEach
    public void setUpMocks() {

        PanacheMock.mock(Adjective.class);
        Mockito.when(Adjective.getRandomAdjectiveThatStartsWith(Mockito.anyString())).thenReturn("Flashy");

        PanacheMock.mock(Animal.class);
        Mockito.when(Animal.getRandomAnimalThatStartsWith(Mockito.anyString())).thenReturn("Fieldmouse");
    }

    @Test
    public void testMemberRegistration(){

        String email = "elwood@bluesbrothers.com";
        MembershipApplication membershipApplication = new MembershipApplication(email);
        LoyaltyMember loyaltyMember = LoyaltyMember.processMembershipApplication(membershipApplication);
        logger.info(loyaltyMember.toString());
        assertNotNull(loyaltyMember);
        assertNotNull(loyaltyMember.getCodeName());
        assertNotNull(loyaltyMember.getEmail());
        assertEquals(email, loyaltyMember.getEmail());
        assertTrue(loyaltyMember.getCodeName().matches(regex));

    }

    @Test
    public void testRegex() {

        assertTrue("FerretFerret".matches(regex));
        assertFalse("Ferret".matches(regex));
    }
}
