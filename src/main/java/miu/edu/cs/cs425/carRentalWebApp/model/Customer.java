package miu.edu.cs.cs425.carRentalWebApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Drving license can not be empty")
    private String drivingLicense;

    @NotBlank(message = "First Name can't be empty")
    @NotBlank(message = "Phone number is required")
    private String phoneNo;

    @NotNull(message = "Create date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime createDate;

    @NotNull(message = "Last Update date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime lastUpdate;

    @NotNull
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn( name = "customer_user_id_fk")
    private User user;

    @NotNull(message = "Address Id can't be empty")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<CarReservation> carReservations;

    public Customer() {
    }

    public Customer(@NotBlank(message = "Drving license can not be empty") String drivingLicense, @NotBlank(message = "First Name can't be empty") @NotBlank(message = "Phone number is required") String phoneNo, @NotNull(message = "Create date can't be null") LocalDateTime createDate, @NotNull(message = "Last Update date can't be null") LocalDateTime lastUpdate, @NotNull(message = "Address Id can't be empty") Address address, List<CarReservation> carReservations) {
        this.drivingLicense = drivingLicense;
        this.phoneNo = phoneNo;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.address = address;
        this.carReservations = carReservations;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public List<CarReservation> getCarReservations() {
        return carReservations;
    }

    public void setCarReservations(List<CarReservation> carReservations) {
        this.carReservations = carReservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
