package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Adjective extends PanacheEntity {

    public char startsWith;

    @Column(name = "text")
    public String value;

    public Adjective() {
    }

    public Adjective(char startsWith, String value) {
        this.startsWith = startsWith;
        this.value = value;
    }
}
