package miu.edu.cs.cs425.carRentalWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotBlank(message = "add house number")
    private String houseNumber;
    @NotBlank(message = "add street number")
    private String street;
    @NotBlank(message = "add city")
    private String city;
    @NotNull(message = "add house zip code")
    private Integer zipCode;
    @NotBlank(message = "add state")
    private String state;

    public Address() {
    }

    public Address(@NotBlank(message = "add house number") String houseNumber, @NotBlank(message = "add street number") String street, @NotBlank(message = "add city") String city, @NotNull(message = "add house zip code") Integer zipCode, @NotBlank(message = "add state") String state) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Address(Long addressId, @NotBlank(message = "add house number") String houseNumber, @NotBlank(message = "add street number") String street, @NotBlank(message = "add city") String city, @NotNull(message = "add house zip code") Integer zipCode, @NotBlank(message = "add state") String state) {
        this.addressId = addressId;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return new StringJoiner("")
                .add(houseNumber + " " + street + "\n")
                .add(city + ", " + state + " " + zipCode)
                .toString();
    }
}

