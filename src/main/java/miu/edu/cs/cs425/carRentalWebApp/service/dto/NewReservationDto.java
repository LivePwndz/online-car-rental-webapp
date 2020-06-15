package miu.edu.cs.cs425.carRentalWebApp.service.dto;

public class NewReservationDto {
    private Long carId;
    private String startDateStr;
    private String endDateStr;

    public NewReservationDto() {
    }

    public NewReservationDto(Long carId, String startDateStr, String endDateStr) {
        this.carId = carId;
        this.startDateStr = startDateStr;
        this.endDateStr = endDateStr;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
