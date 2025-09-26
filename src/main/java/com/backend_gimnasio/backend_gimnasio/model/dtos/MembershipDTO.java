package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO for transferring membership information.
 * Represents the main data of a gym membership plan.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {

    private Long id;
    private String name;
    private String type;
    private BigDecimal price;
    private String description;
    private Integer durationDays;
}
