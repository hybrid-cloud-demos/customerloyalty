package io.quarkuscoffeeshop.customerloyalty.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class MembershipApplication {

    String name;

    String email;

    public MembershipApplication() {
    }

    public MembershipApplication(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MembershipApplication.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipApplication that = (MembershipApplication) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
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
}
