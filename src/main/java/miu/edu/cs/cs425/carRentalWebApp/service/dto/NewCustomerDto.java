package miu.edu.cs.cs425.carRentalWebApp.service.dto;

import miu.edu.cs.cs425.carRentalWebApp.model.Address;

import java.time.LocalDateTime;

public class NewCustomerDto {
    private Long id;
    private String drivingLicense;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String password;
    private String confirmPassword;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private Address address;

    public NewCustomerDto() {
    }

    public NewCustomerDto(String drivingLicense, String firstName, String middleName, String lastName, String phoneNo, String email, String password, String confirmPassword, LocalDateTime createDate, LocalDateTime lastUpdate, Address address) {
        this.drivingLicense = drivingLicense;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.address = address;
    }

    public NewCustomerDto(Long id, String drivingLicense, String firstName, String middleName, String lastName, String phoneNo, String email, String password, String confirmPassword, LocalDateTime createDate, LocalDateTime lastUpdate, Address address) {
        this.id = id;
        this.drivingLicense = drivingLicense;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
