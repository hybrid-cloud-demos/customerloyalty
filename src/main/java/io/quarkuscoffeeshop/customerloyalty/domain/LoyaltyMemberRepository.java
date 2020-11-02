package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoyaltyMemberRepository implements PanacheRepository<LoyaltyMember> {
}
