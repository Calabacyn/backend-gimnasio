package com.backend_gimnasio.backend_gimnasio.model.dtos;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String nationalId;

    private String phone;

    private LocalDate birthDate;

    private LocalDate registrationDate;

    @NotBlank
    private Long registeredById;

    private String registeredByName;
}
