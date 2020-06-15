package miu.edu.cs.cs425.carRentalWebApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class ChekoutRecord {
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
    private LocalDateTime updateDate;

    public ChekoutRecord() {
    }

    public ChekoutRecord(CarReservation reservationId, Clerk clerckId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.reservationId = reservationId;
        this.clerckId = clerckId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public ChekoutRecord(Long checkoutId, CarReservation reservationId, Clerk clerckId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.checkoutId = checkoutId;
        this.reservationId = reservationId;
        this.clerckId = clerckId;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public void setReservationId(CarReservation reservationId) {
        this.reservationId = reservationId;
    }

    public Clerk getClerckId() {
        return clerckId;
    }

    public void setClerckId(Clerk clerckId) {
        this.clerckId = clerckId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "ChekoutRecord{" +
                "checkoutId=" + checkoutId +
                ", reservationId=" + reservationId +
                ", clerckId=" + clerckId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
