package miu.edu.cs.cs425.carRentalWebApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Resrvation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Start date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime startDate;
    @NotNull(message = "End date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "id")
    private Car car;

    public Resrvation() {
    }

    public Resrvation(@NotNull(message = "Start date can't be null") LocalDateTime startDate, @NotNull(message = "End date can't be null") LocalDateTime endDate, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
    }

    public Resrvation(Long id, @NotNull(message = "Start date can't be null") LocalDateTime startDate, @NotNull(message = "End date can't be null") LocalDateTime endDate, Car car) {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Resrvation{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", car=" + car +
                '}';
    }
}
