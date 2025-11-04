package com.backend_gimnasio.backend_gimnasio.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO for transferring client information.
 * Represents the main personal and registration data of a client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String nationalId;
    private String phone;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String registeredByEmail;


}
