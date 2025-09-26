package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for transferring client access information.
 * Represents the entry and exit times of a client in the gym.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientAccessDTO {

    private Long id;
    private Long clientId;
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private Long registeredBy;  // user who registered the access
    private String status;      // 'active' or 'inactive'
}
