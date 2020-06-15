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
    private CarReservation reservationId;
    @NotNull(message="Clerk Id can't be empty")
    @ManyToOne
    private Clerk clerckId;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    public CheckoutRecord() {
    }

    public CheckoutRecord(CarReservation reservationId, Clerk clerckId, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.reservationId = reservationId;
        this.clerckId = clerckId;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public CheckoutRecord(Long checkoutId, CarReservation reservationId, Clerk clerckId, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.checkoutId = checkoutId;
        this.reservationId = reservationId;
        this.clerckId = clerckId;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public Long getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Long checkoutId) {
        this.checkoutId = checkoutId;
    }

    public CarReservation getReservationId() {
        return reservationId;
    }

    public void setReservation(CarReservation reservationId) {
        this.reservationId = reservationId;
    }

    public Clerk getClerckId() {
        return clerckId;
    }

    public void setClerk(Clerk clerckId) {
        this.clerckId = clerckId;
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
                ", reservationId=" + reservationId +
                ", clerckId=" + clerckId +
                ", createDate=" + createDate +
                ", updateDate=" + lastUpdate +
                '}';
    }
}
