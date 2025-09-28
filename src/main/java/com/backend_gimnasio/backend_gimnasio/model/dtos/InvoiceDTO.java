package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for transferring invoice data.
 * Represents a sale invoice (type A or C) and contains its general info.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private Long idInvoice;
    private String type;           // "A" or "C"
    private LocalDate date;
    private String cuit;           // tax ID
    private Long client;         // optional if client exists
    private BigDecimal totalAmount;
    private String paymentMethod;
    private Long registeredBy;

    private List<InvoiceItemDTO> items; // list of items sold in this invoice
}