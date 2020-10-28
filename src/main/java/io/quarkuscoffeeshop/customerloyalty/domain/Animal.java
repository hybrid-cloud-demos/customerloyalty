package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Animal extends PanacheEntity {

    char startsWith;

    String value;
}
