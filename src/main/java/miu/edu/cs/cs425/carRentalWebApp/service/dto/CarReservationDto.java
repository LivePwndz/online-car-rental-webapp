package miu.edu.cs.cs425.carRentalWebApp.service.dto;

import miu.edu.cs.cs425.carRentalWebApp.model.ReservationStatus;
import miu.edu.cs.cs425.carRentalWebApp.model.TransmissionType;

import java.math.BigDecimal;

public class CarReservationDto {
        private Long carId;
        private String plateNo;
        private String model;
        private int maxNoDays;
        private BigDecimal pricePerDay;
        private TransmissionType transmissionType;
        private int nuOfSeats;
        private int nuOfDoors;
        private ReservationStatus status = ReservationStatus.DRAFT;
        private String photoUrl;
        private String durationDescription;
        private BigDecimal totalCostOfReservation;
        private BigDecimal costOfDelivery;
        private BigDecimal totalCostOfRent;
        private int totalNoOfDays;

        public CarReservationDto(Long carId, String plateNo, String model, int maxNoDays, BigDecimal pricePerDay, TransmissionType transmissionType, int nuOfSeats, int nuOfDoors, ReservationStatus status, String photoUrl) {
                this.carId = carId;
                this.plateNo = plateNo;
                this.model = model;
                this.maxNoDays = maxNoDays;
                this.pricePerDay = pricePerDay;
                this.transmissionType = transmissionType;
                this.nuOfSeats = nuOfSeats;
                this.nuOfDoors = nuOfDoors;
                this.status = status;
                this.photoUrl = photoUrl;
        }

        public Long getCarId() {
                return carId;
        }

        public void setCarId(Long carId) {
                this.carId = carId;
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

        public String getDurationDescription() {
                return durationDescription;
        }

        public void setDurationDescription(String durationDescription) {
                this.durationDescription = durationDescription;
        }

        public BigDecimal getTotalCostOfReservation() {
                return totalCostOfReservation;
        }

        public void setTotalCostOfReservation(BigDecimal totalCostOfReservation) {
                this.totalCostOfReservation = totalCostOfReservation;
        }

        public BigDecimal getCostOfDelivery() {
                return costOfDelivery;
        }

        public void setCostOfDelivery(BigDecimal costOfDelivery) {
                this.costOfDelivery = costOfDelivery;
        }

        public BigDecimal getTotalCostOfRent() {
                return totalCostOfRent;
        }

        public void setTotalCostOfRent(BigDecimal rentTotal) {
                this.totalCostOfRent = rentTotal;
        }

        public int getTotalNoOfDays() {
                return totalNoOfDays;
        }

        public void setTotalNoOfDays(int totalNoOfDays) {
                this.totalNoOfDays = totalNoOfDays;
        }

        public ReservationStatus getStatus() {
                return status;
        }

        public void setStatus(ReservationStatus status) {
                this.status = status;
        }
}
