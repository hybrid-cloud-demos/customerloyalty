package io.quarkuscoffeeshop.customerloyalty.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbProperty;
import java.util.Objects;
import java.util.StringJoiner;

@RegisterForReflection
public class RewardsMember {

    String name;
    
    String email;

    @JsonbProperty("codename")
    String codeName;

    public RewardsMember() {
    }

    public RewardsMember(String name, String email) {
        this.name = name;
        this.email = email;
        this.codeName = CodenameGenerator.generateCodename();
    }

    public static RewardsMember processApplication(MembershipApplication membershipApplication) {

        return new RewardsMember(membershipApplication.getName(), membershipApplication.getEmail());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RewardsMember.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("codeName='" + codeName + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardsMember rewardsMember = (RewardsMember) o;
        return Objects.equals(name, rewardsMember.name) &&
                Objects.equals(email, rewardsMember.email) &&
                Objects.equals(codeName, rewardsMember.codeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, codeName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeName() {
        return codeName;
    }

}

