package miu.edu.cs.cs425.carRentalWebApp.service.dto;

import java.time.LocalDateTime;

public class ClerkDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    public ClerkDto() {
    }

    public ClerkDto(String firstName, String middleName, String lastName, String email, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public ClerkDto(Long id, String firstName, String middleName, String lastName, String email, LocalDateTime createDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }
}
