package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for transferring client information.
 * Represents the main personal and registration data of a client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nationalId;   // previously "dni"
    private String phone;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String registeredBy;
}
