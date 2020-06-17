package miu.edu.cs.cs425.carRentalWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutId;
    @NotNull(message="Reservation Id can't be empty")
    @OneToOne
    private CarReservation reservation;
    @NotNull(message="Clerk Id can't be empty")
    @ManyToOne
    private User clerk;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    public CheckoutRecord() {
    }

    public CheckoutRecord(CarReservation reservation,User clerk, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.reservation = reservation;
        this.clerk = clerk;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public CheckoutRecord(Long checkoutId, CarReservation reservation, User clerk, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.checkoutId = checkoutId;
        this.reservation = reservation;
        this.clerk = clerk;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Long getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Long checkoutId) {
        this.checkoutId = checkoutId;
    }

    public CarReservation getReservation() {
        return reservation;
    }

    public void setReservation(CarReservation reservationId) {
        this.reservation = reservationId;
    }

    public User getClerk() {
        return clerk;
    }

    public void setClerk(User clerck) {
        this.clerk = clerck;
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

    public void setLastUpdate(LocalDateTime updateDate) {
        this.lastUpdate = updateDate;
    }

    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "checkoutId=" + checkoutId +
                ", reservationId=" + reservation +
                ", clerckId=" + clerk +
                ", createDate=" + createDate +
                ", updateDate=" + lastUpdate +
                '}';
    }
}
