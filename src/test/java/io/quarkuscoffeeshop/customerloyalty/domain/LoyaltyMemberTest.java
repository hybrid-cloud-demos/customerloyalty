package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.customerloyalty.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class LoyaltyMemberTest extends TestHelper {

    @BeforeEach
    public void setUpMocks() {

        PanacheMock.mock(Adjective.class);
        Mockito.when(Adjective.getRandomAdjectiveThatStartsWith(Mockito.anyString())).thenReturn("Flashy");

        PanacheMock.mock(Animal.class);
        Mockito.when(Animal.getRandomAnimalThatStartsWith(Mockito.anyString())).thenReturn("Fieldmouse");

    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        assertTrue(loyaltyMember.getCodeName().matches(TestHelper.CODENAME_REGEX));
    }

    @Test
    public void testRegex() {

        assertTrue("FerretFerret".matches(TestHelper.CODENAME_REGEX));
        assertFalse("Ferret".matches(TestHelper.CODENAME_REGEX));
    }



}
