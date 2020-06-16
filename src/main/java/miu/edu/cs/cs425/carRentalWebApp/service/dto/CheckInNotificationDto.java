package miu.edu.cs.cs425.carRentalWebApp.service.dto;

public class CheckInNotificationDto {
    private String carModel;
    private String carLicensePlate;
    private String customerFirstName;
    private String customerLastName;
    private String checkInClerkName;


    public CheckInNotificationDto() {
    }

    public CheckInNotificationDto(String carModel, String carLicensePlate, String customerFirstName, String customerLastName, String checkInClerkName) {
        this.carModel = carModel;
        this.carLicensePlate = carLicensePlate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.checkInClerkName = checkInClerkName;
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

    public String getCheckInClerkName() {
        return checkInClerkName;
    }

    public void setCheckInClerkName(String checkInClerkName) {
        this.checkInClerkName = checkInClerkName;
    }
}
