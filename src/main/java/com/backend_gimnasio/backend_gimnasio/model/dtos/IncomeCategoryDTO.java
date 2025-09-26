package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for transferring income category information.
 * Represents the main data of an income category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCategoryDTO {

    private Long id;
    private String name;
    private String description;
    private Long registeredBy;
    private LocalDate createdAt;
}
