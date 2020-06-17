package miu.edu.cs.cs425.carRentalWebApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class CheckInRecord {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "checkIn_reservation_id_fk")
    @NotNull(message="reservation cant't be null")
    private CarReservation reservation;
    @ManyToOne
    @JoinColumn(name = "checkIn_clerk_id_fk")
    @NotNull(message="clerk cant't be null")
    private User clerk;
    @NotNull(message = "Create date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime createDate;
    @NotNull(message = "LastUpdate date can't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss.zzz")
    private LocalDateTime lastUpdate;

    public CheckInRecord() {
           }

    public CheckInRecord(Long id, CarReservation reservationId, User clerk, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.id = id;
        reservation = reservationId;
        this.clerk = clerk;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public CheckInRecord(CarReservation reservationId, User clerk, LocalDateTime createDate, LocalDateTime lastUpdate) {
        reservation = reservationId;
        this.clerk = clerk;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarReservation getReservation() {
        return reservation;
    }

    public void setReservation(CarReservation reservation) {
        this.reservation = reservation;
    }

    public User getClerk() {
        return clerk;
    }

    public void setClerk(User clerk) {
        this.clerk = clerk;
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
        return "CheckInRecord{" +
                "id=" + id +
                ", ReservationId=" + reservation +
                ", clerkId=" + clerk +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
