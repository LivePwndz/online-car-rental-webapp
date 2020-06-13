package miu.edu.cs.cs425.carRentalWebApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Drving license can not be empty")
    private String drivingLicense;
    @NotBlank(message="First Name can't be empty")
    private String firstName;
    private String middleName;
    @NotBlank(message="Last Name can't be empty")
    private String lastName;
    @NotBlank(message="email can't be empty")
    private String email;
    @NotNull(message = "Create date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime createDate;
    @NotNull(message = "Last Update date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime lastUpdate;
    @NotNull(message = "Address Id can't be empty")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Customer() {
    }

    public Customer(@NotNull String drivingLicense, @NotBlank(message = "First Name can't be empty") String firstName, String middleName, @NotBlank(message = "Last Name can't be empty") String lastName, @NotBlank(message = "email can't be empty") String email, @NotNull(message = "Create date can't be null") LocalDateTime createDate, @NotNull(message = "Last Update date can't be null") LocalDateTime lastUpdate, Address address) {
        this.drivingLicense = drivingLicense;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.address = address;
    }

    public Customer(Long id, @NotNull String drivingLicense, @NotBlank(message = "First Name can't be empty") String firstName, String middleName, @NotBlank(message = "Last Name can't be empty") String lastName, @NotBlank(message = "email can't be empty") String email, @NotNull(message = "Create date can't be null") LocalDateTime createDate, @NotNull(message = "Last Update date can't be null") LocalDateTime lastUpdate, Address address) {
        this.id = id;
        this.drivingLicense = drivingLicense;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", address=" + address +
                '}';
    }
}
