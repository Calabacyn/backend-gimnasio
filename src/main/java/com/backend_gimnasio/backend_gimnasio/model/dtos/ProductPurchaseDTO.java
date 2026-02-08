package com.backend_gimnasio.backend_gimnasio.model.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPurchaseDTO {

    private Long id;

    @NotNull
    private LocalDateTime purchaseDate;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal unitCost;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalCost;

    @NotBlank
    @Size(max = 50)
    private String invoiceNumber;

    @Size(max = 255)
    private String notes;


    @NotNull
    private Long productId;

    @NotNull
    private Long providerId;

    @NotBlank
    @Size(max = 150)
    private String registeredBy;
}
