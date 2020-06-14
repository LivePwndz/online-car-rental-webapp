package miu.edu.cs.cs425.carRentalWebApp.model;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String houseNumber;
    private String street;
    private String city;
    private Integer zipCode;
    private String state;

    public Address(Long addressId, String houseNumber, String street, String city, Integer zipCode, String state) {
        this.addressId = addressId;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Address(String houseNumber, String street, String city, Integer zipCode, String state) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Address() {
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

