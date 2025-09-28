package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for transferring workout plan information.
 * Represents the main data of a client’s workout plan in the gym.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanDTO {

    private Long id;
    private Long clientId;
    private String title;
    private String link;
    private String description;
    private LocalDate creationDate;
    private Long membershipId;
    private String registeredBy;
    private LocalDate expirationDate;
}
