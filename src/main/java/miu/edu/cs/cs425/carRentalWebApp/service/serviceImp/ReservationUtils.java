package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class ReservationUtils {
    private  static DateTimeFormatter standardDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate convertFromStringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, standardDateFormat);
    }

    public static BigDecimal calculateTotalCostOfReservation(BigDecimal pricePerDay, String startDateStr, String endDateStr) {
        BigDecimal costOfDelivery = BigDecimal.valueOf(0);

        // Total number of days
        LocalDate startDate = ReservationUtils.convertFromStringToLocalDate(startDateStr);
        LocalDate endDate = ReservationUtils.convertFromStringToLocalDate(endDateStr);
        Period period = Period.between(startDate, endDate);
        int totalNoOfDays = period.getDays();

        // Total cost of rent
        BigDecimal totalCostOfRent = pricePerDay.multiply(BigDecimal.valueOf(totalNoOfDays));

        // Total cost of reservation
        return totalCostOfRent.add(costOfDelivery);
    }

    public static String formatLocalDateToUIString(LocalDate localDate) {
        return localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US)+" "
        +localDate.getDayOfMonth()+", "
        +localDate.getYear();
    }

    public static String formatLocalDateToStandardString(LocalDate localDate) {
        return localDate.format(standardDateFormat);
    }
}
