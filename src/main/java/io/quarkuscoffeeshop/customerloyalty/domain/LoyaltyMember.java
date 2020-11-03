package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.StringJoiner;

@Entity
@NamedQuery(name = "LoyaltyMember.loyaltyMemberExists", query = "from LoyaltyMember where codeName = ?1 and email = ?2")
public class LoyaltyMember extends PanacheEntity {

    private String codeName;

    private String email;

    public LoyaltyMember() {
    }

    public LoyaltyMember(String codeName, String email) {
        this.codeName = codeName;
        this.email = email;
    }

    public static LoyaltyMember processMembershipApplication(final MembershipApplication membershipApplication) {
        LoyaltyMember loyaltyMember = createLoyaltyMember(membershipApplication.email);

        boolean validApplication = false;
        while (!validApplication) {
            if(validateApplication(loyaltyMember.codeName, loyaltyMember.email)){
                validApplication = true;
                return loyaltyMember;
            }else {
                loyaltyMember = createLoyaltyMember(membershipApplication.email);
            }
        }
        return loyaltyMember;
    }

    private static boolean validateApplication(final String codeName, final String email){
        LoyaltyMember loyaltyMember = find("#LoyaltyMember.loyaltyMemberExists", codeName, email).firstResult();
        return loyaltyMember == null ? true : false;
    }

    private static LoyaltyMember createLoyaltyMember(final String email) {
        return new LoyaltyMember(generateCodeName(), email);
    }

    private static String generateCodeName() {
        String letter = RandomStringUtils.randomAlphabetic(1);
        String adjective = Adjective.getRandomAdjectiveThatStartsWith(letter);
        String animal = Animal.getRandomAnimalThatStartsWith(letter);
        return new StringBuilder()
                .append(adjective)
                .append(animal).toString();
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
