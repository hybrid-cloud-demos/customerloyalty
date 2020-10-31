package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static String getRandomAdjectiveThatStartsWith(String letter) {
        Set<String> allValues = Adjective.stream("startsWith = :letter", letter)
                .map(a -> {return ((Adjective) a).value;})
                .collect(Collectors.toSet());
        return allValues.stream().collect(Collectors.toList()).get(new Random().nextInt(allValues.size()));
    }

}
