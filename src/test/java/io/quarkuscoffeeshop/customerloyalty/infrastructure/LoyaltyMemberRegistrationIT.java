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

@QuarkusTest @QuarkusTestResource(DatabaseTestResource.class)
public class LoyaltyMemberRegistrationIT extends TestHelper{

}
