package com.backend_gimnasio.backend_gimnasio.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for transferring membership payment records.
 * Represents the main data of a membership payment.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPaymentDTO {

    private Long idPayment;
    private Long clientId;
    private Long membershipId;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private BigDecimal amount;
    private Long registeredBy;
    private String paymentMethod;
}