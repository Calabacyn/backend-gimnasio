package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for transferring supplier information.
 * Represents the basic data of a supplier.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {

    private Long id;
    private String name;
    private String description;
}
