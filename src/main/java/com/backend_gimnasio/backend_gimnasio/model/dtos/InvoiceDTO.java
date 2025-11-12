package com.backend_gimnasio.backend_gimnasio.model.dtos;

import com.backend_gimnasio.backend_gimnasio.enums.InvoiceStatusEnum;
import com.backend_gimnasio.backend_gimnasio.enums.InvoiceTypeEnum;
import com.backend_gimnasio.backend_gimnasio.enums.PaymentMethodEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {

    @Null(groups = InvoiceDTO.Create.class)
    @NotNull(groups = InvoiceDTO.Update.class)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String number;

    @NotNull
    private InvoiceTypeEnum type;

    private LocalDateTime date;

    private String clientEmail;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal total;  // Calculado en servicio desde ítems

    @NotNull
    private InvoiceStatusEnum status = InvoiceStatusEnum.PENDING;

    private PaymentMethodEnum paymentMethod;

    @NotBlank
    private String registeredByEmail;

    private List<InvoiceProductItemDTO> productItems;  // Opcional, lista vacía si no hay

    private List<InvoiceMembershipItemDTO> membershipItems;  // Opcional, lista vacía si no hay

    // Grupos para validación condicional
    public interface Create {}
    public interface Update {}
}