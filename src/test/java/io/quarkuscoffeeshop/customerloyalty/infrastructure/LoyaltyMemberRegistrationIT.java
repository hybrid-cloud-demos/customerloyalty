package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.customerloyalty.TestHelper;
import io.quarkuscoffeeshop.customerloyalty.domain.LoyaltyMember;
import io.quarkuscoffeeshop.customerloyalty.domain.MembershipApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest @QuarkusTestResource(DockerComposeTestResource.class)
public class LoyaltyMemberRegistrationIT {

    Logger logger = LoggerFactory.getLogger(LoyaltyMemberRegistrationIT.class);

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
}
