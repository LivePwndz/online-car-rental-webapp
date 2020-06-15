package miu.edu.cs.cs425.carRentalWebApp.service.dto;

import java.math.BigDecimal;

public class PlaceRerservationInfoDto {
    private String model;
    private String reservationCode;
    private String pickupDateStr;
    private BigDecimal totalCostOfReservation;

    public PlaceRerservationInfoDto() {
    }

    public PlaceRerservationInfoDto(String model, String reservationCode, String pickupDateStr, BigDecimal totalCostOfReservation) {
        this.model = model;
        this.reservationCode = reservationCode;
        this.pickupDateStr = pickupDateStr;
        this.totalCostOfReservation = totalCostOfReservation;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getPickupDateStr() {
        return pickupDateStr;
    }

    public void setPickupDateStr(String pickupDateStr) {
        this.pickupDateStr = pickupDateStr;
    }

    public BigDecimal getTotalCostOfReservation() {
        return totalCostOfReservation;
    }

    public void setTotalCostOfReservation(BigDecimal totalCostOfReservation) {
        this.totalCostOfReservation = totalCostOfReservation;
    }
}
