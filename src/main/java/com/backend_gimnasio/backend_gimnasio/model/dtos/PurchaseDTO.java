package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for transferring product renewal/purchase records.
 * Represents the main data of a product purchase.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long idPurchase;
    private Long product;
    private Long supplier;
    private LocalDate purchaseDate;
    private Integer quantityPurchased;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String paymentMethod;   // e.g., cash, credit card, transfer
    private Long registeredBy;
}
