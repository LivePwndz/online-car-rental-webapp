package miu.edu.cs.cs425.carRentalWebApp.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "plateNo is required")
    private String plateNo;
    @NotBlank(message = "model is required")
    private String model;
    @Range(min = 1, max = 30)
    private int maxNoDays;
    @Min(10)
    private BigDecimal pricePerDay;
    @NotNull
    private TransmissionType transmissionType;
    @NotNull
    private int nuOfSeats;
    @Min(1)
    private int nuOfDoors;

    private String photoUrl;
    @NotNull
    private LocalDateTime createDate;
    @NotNull
    private LocalDateTime lastUpdate;

    public Car() {
    }

    public Car(String plateNo, String model, int maxNoDays, BigDecimal pricePerDay, TransmissionType transmissionType, int nuOfSeats, int nuOfDoors, String photoUrl, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.plateNo = plateNo;
        this.model = model;
        this.maxNoDays = maxNoDays;
        this.pricePerDay = pricePerDay;
        this.transmissionType = transmissionType;
        this.nuOfSeats = nuOfSeats;
        this.nuOfDoors = nuOfDoors;
        this.photoUrl = photoUrl;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Car(Long id, String plateNo, String model, int maxNoDays, BigDecimal pricePerDay, TransmissionType transmissionType, int nuOfSeats, int nuOfDoors, String photoUrl, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.plateNo = plateNo;
        this.model = model;
        this.maxNoDays = maxNoDays;
        this.pricePerDay = pricePerDay;
        this.transmissionType = transmissionType;
        this.nuOfSeats = nuOfSeats;
        this.nuOfDoors = nuOfDoors;
        this.photoUrl = photoUrl;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxNoDays() {
        return maxNoDays;
    }

    public void setMaxNoDays(int maxNoDays) {
        this.maxNoDays = maxNoDays;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getNuOfSeats() {
        return nuOfSeats;
    }

    public void setNuOfSeats(int nuOfSeats) {
        this.nuOfSeats = nuOfSeats;
    }

    public int getNuOfDoors() {
        return nuOfDoors;
    }

    public void setNuOfDoors(int nuOfDoors) {
        this.nuOfDoors = nuOfDoors;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", plateNo='" + plateNo + '\'' +
                ", model='" + model + '\'' +
                ", maxNoDays=" + maxNoDays +
                ", pricePerDay=" + pricePerDay +
                ", transmissionType=" + transmissionType +
                ", nuOfSeats=" + nuOfSeats +
                ", nuOfDoors=" + nuOfDoors +
                ", photoUrl='" + photoUrl + '\'' +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
