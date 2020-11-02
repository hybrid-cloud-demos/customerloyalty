package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Animal extends PanacheEntity {

    @Transient
    static final Logger logger = LoggerFactory.getLogger(Animal.class);

    public String startsWith;

    @Column(name = "text")
    public String value;

    public Animal() {
    }

    public Animal(String startsWith, String value) {
        this.startsWith = startsWith;
        this.value = value;
    }

    public static String getRandomAnimalThatStartsWith(final String letter) {
        logger.debug("retrieving animals starting with {}", letter);
        List<Animal> allAnimals = Animal.find("from Animal where startsWith = ?1", letter.toUpperCase()).list();
        if(logger.isDebugEnabled()){
            logger.debug("returned {} adjectives", allAnimals.size());
            allAnimals.forEach(animal -> { logger.debug("{}", animal.toString());});
        }
        return allAnimals.get(new Random().nextInt(allAnimals.size())).value;
    }
}
