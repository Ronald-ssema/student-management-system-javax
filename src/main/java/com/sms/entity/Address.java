package com.sms.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Address {

    @NotBlank
    @Size(max = 100)
    private String line1;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 10)
    private String postcode;

    @NotBlank
    @Size(max = 50)
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address that = (Address) o;
        return line1.equals(that.line1)
                && city.equals(that.city)
                && postcode.equals(that.postcode)
                && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(line1, city, postcode, country);
    }
}

