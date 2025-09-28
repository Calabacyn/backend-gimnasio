package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for transferring expense records.
 * Represents the main data of an expense entry.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private Long id;
    private LocalDate date;
    private BigDecimal amount;
    private String paymentMethod;
    private Long registeredBy;
    private String description;
    private Long categoryId;
    private String invoiceNumber;
}
