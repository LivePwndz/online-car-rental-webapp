package miu.edu.cs.cs425.carRentalWebApp.service.dto;

public class CheckoutNotificationDto {
    private String carModel;
    private String carLicensePlate;
    private String customerFirstName;
    private String customerLastName;
    private String checkoutClerkName;
    private String dateOfReservationStr;
    private String returnDateStr;

    public CheckoutNotificationDto() {
    }

    public CheckoutNotificationDto(String carModel, String carLicensePlate, String customerFirstName, String customerLastName, String checkoutClerkName, String dateOfReservationStr, String returnDateStr) {
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.checkoutClerkName = checkoutClerkName;
        this.dateOfReservationStr = dateOfReservationStr;
        this.returnDateStr = returnDateStr;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
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

    public String getCheckoutClerkName() {
        return checkoutClerkName;
    }

    public void setCheckoutClerkName(String checkoutClerkName) {
        this.checkoutClerkName = checkoutClerkName;
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

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }
}
