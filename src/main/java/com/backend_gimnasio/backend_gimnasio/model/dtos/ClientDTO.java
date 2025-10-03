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

    @NotNull
    private Long registeredById;

    private String registeredByName;


}
