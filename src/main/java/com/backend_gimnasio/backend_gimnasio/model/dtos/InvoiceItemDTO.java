package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO for transferring invoice item data.
 * Represents a single product sold in an invoice.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDTO {

    private Long idItem;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
