package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Animal extends PanacheEntity {

    public char startsWith;

    @Column(name = "text")
    public String value;

    public Animal() {
    }

    public Animal(char startsWith, String value) {
        this.startsWith = startsWith;
        this.value = value;
    }

    public static String getRandomAnimalThatStartsWith(String letter) {
        Set<String> allValues = Animal.stream("startsWith = :letter", letter)
                .map(a -> {return ((Adjective) a).value;})
                .collect(Collectors.toSet());
        return allValues.stream().collect(Collectors.toList()).get(new Random().nextInt(allValues.size()));
    }

}
