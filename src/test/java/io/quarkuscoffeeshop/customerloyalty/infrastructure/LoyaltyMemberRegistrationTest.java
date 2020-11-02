package io.quarkuscoffeeshop.customerloyalty.infrastructure;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.customerloyalty.BaseRegistrationTest;

@QuarkusTest @QuarkusTestResource(DatabaseTestResource.class)
public class LoyaltyMemberRegistrationTest extends BaseRegistrationTest {

}
