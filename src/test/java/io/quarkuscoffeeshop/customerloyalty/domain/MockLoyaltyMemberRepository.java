package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.test.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
public class MockLoyaltyMemberRepository extends LoyaltyMemberRepository{

    Logger logger = LoggerFactory.getLogger(MockLoyaltyMemberRepository.class);

    @Override
    public void persist(LoyaltyMember loyaltyMember) {
        logger.info("persisting {}", loyaltyMember);
    }

}
