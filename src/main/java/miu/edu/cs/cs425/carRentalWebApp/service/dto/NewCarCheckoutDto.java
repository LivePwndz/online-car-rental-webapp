package miu.edu.cs.cs425.carRentalWebApp.service.dto;

public class NewCarCheckoutDto {
    private Long reservationId;
    private String carModel;
    private String carLicensePlate;
    private String customerFirstName;
    private String customerLastName;
    private String drivingLicenseNo;
    private String reservationCode;
    private String dateOfReservationStr;
    private String returnDateStr;

    public NewCarCheckoutDto() {
    }

    public NewCarCheckoutDto(Long reservationId) {
        this.reservationId = reservationId;
    }

    public NewCarCheckoutDto(Long reservationId, String carModel, String carLicensePlate, String customerFirstName, String customerLastName, String drivingLicenseNo, String reservationCode, String dateOfReservationStr, String returnDateStr) {
        this.reservationId = reservationId;
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.drivingLicenseNo = drivingLicenseNo;
        this.reservationCode = reservationCode;
        this.dateOfReservationStr = dateOfReservationStr;
        this.returnDateStr = returnDateStr;
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

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getDateOfReservationStr() {
        return dateOfReservationStr;
    }

    public void setDateOfReservationStr(String dateOfReservationStr) {
        this.dateOfReservationStr = dateOfReservationStr;
    }

    public String getReturnDateStr() {
        return returnDateStr;
    }

    public void setReturnDateStr(String returnDateStr) {
        this.returnDateStr = returnDateStr;
    }
}
