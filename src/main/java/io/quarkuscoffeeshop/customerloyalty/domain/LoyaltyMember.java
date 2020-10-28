package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class LoyaltyMember extends PanacheEntity {

    public String codeName;

    public LoyaltyMember() {
    }

    public LoyaltyMember(String codeName) {
        this.codeName = codeName;
    }
}
