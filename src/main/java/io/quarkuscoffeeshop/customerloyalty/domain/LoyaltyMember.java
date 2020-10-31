package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import java.util.StringJoiner;

@Entity
public class LoyaltyMember extends PanacheEntity {

    private String codeName;

    private String email;

    public LoyaltyMember() {
    }

    public LoyaltyMember(String codeName, String email) {
        this.codeName = codeName;
        this.email = email;
    }

    public static LoyaltyMember processMembershipApplication(MembershipApplication membershipApplication) {
        return new LoyaltyMember(generateCodeName(), membershipApplication.email);
    }

    private static String generateCodeName() {
        String letter = RandomStringUtils.randomAlphabetic(1);
        return new StringJoiner(Adjective.getRandomAdjectiveThatStartsWith(letter))
                .add(Animal.getRandomAnimalThatStartsWith(letter)).toString();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LoyaltyMember.class.getSimpleName() + "[", "]")
                .add("codeName='" + codeName + "'")
                .add("email='" + email + "'")
                .add("id=" + id)
                .toString();
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
