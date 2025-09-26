package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for transferring expense category information.
 * Represents the main data of an expense category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryDTO {

    private Long id;
    private String name;
    private String description;
    private Long registeredBy;   // user who registered it
    private LocalDate createdAt;
}
