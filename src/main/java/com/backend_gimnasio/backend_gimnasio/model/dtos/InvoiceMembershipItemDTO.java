package com.backend_gimnasio.backend_gimnasio.model.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceMembershipItemDTO {

    private Long id;

    @NotNull
    private Long membershipId;

    @NotNull
    private String clientEmail;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Min(1)
    private Integer quantity;
}