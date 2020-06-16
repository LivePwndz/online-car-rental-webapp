package miu.edu.cs.cs425.carRentalWebApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "reservations")
public class CarReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true)
    private String code;

    @Transient
    private BigDecimal cost;

    private ReservationStatus status = ReservationStatus.DRAFT;

    @NotNull(message = "Start date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "End date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull(message="Car cant't be null")
    @ManyToOne
    @JoinColumn(name = "reservation_car_id_fk")
    private Car car;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "reservation_customer_id_fk")
    private Customer customer;

    public CarReservation() {
    }

    public CarReservation(@NotNull(message = "Start date can't be null") LocalDate startDate, @NotNull(message = "End date can't be null") LocalDate endDate, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
    }

    public CarReservation(Long id, @NotNull(message = "Start date can't be null") LocalDate startDate, @NotNull(message = "End date can't be null") LocalDate endDate, Car car) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BigDecimal getCost() {
        Period period = Period.between(startDate, endDate);
        int noOfDays = period.getDays();
        // TODO Add cost of delivery
        return car.getPricePerDay().multiply(BigDecimal.valueOf(noOfDays));
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarReservation{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", car=" + car +
                '}';
    }
}
