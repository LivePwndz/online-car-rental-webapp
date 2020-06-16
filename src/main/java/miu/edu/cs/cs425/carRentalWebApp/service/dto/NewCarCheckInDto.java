package miu.edu.cs.cs425.carRentalWebApp.service.dto;

public class NewCarCheckInDto {
    private Long reservationId;
    private String carModel;
    private String carLicensePlate;
    private String customerFirstName;
    private String customerLastName;
    private String reservationCode;
    private String checkoutClerkName;
    private String checkoutDateStr;

    public NewCarCheckInDto() {
    }

    public NewCarCheckInDto(Long reservationId) {
        this.reservationId = reservationId;
    }

    public NewCarCheckInDto(Long reservationId, String carModel, String carLicensePlate, String customerFirstName, String customerLastName, String reservationCode, String checkoutClerkName, String checkoutDateStr) {
        this.reservationId = reservationId;
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.reservationCode = reservationCode;
        this.checkoutClerkName = checkoutClerkName;
        this.checkoutDateStr = checkoutDateStr;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getCheckoutClerkName() {
        return checkoutClerkName;
    }

    public void setCheckoutClerkName(String checkoutClerkName) {
        this.checkoutClerkName = checkoutClerkName;
    }

    public String getCheckoutDateStr() {
        return checkoutDateStr;
    }

    public void setCheckoutDateStr(String checkoutDateStr) {
        this.checkoutDateStr = checkoutDateStr;
    }
}
