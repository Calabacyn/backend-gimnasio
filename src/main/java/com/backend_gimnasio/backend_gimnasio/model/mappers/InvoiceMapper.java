package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceMembershipItemDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceProductItemDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    public InvoiceDTO toDTO(InvoiceEntity invoice) {
        if (invoice == null) return null;

        return InvoiceDTO.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .type(invoice.getType())
                .date(invoice.getDate())
                .clientEmail(invoice.getClient() != null ? invoice.getClient().getEmail() : null)
                .total(invoice.getTotal())
                .status(invoice.getStatus())
                .paymentMethod(invoice.getPaymentMethod())
                .registeredByEmail(invoice.getRegisteredBy() != null ? invoice.getRegisteredBy().getEmail() : null)
                .productItems(invoice.getProductItems() != null ?
                        invoice.getProductItems().stream().map(this::toProductItemDTO).collect(Collectors.toList()) : null)
                .membershipItems(invoice.getMembershipItems() != null ?
                        invoice.getMembershipItems().stream().map(this::toMembershipItemDTO).collect(Collectors.toList()) : null)
                .build();
    }

    public InvoiceEntity toEntity(InvoiceDTO dto, ClientEntity client, UserEntity registeredBy,
                                  List<InvoiceProductItemEntity> productItems, List<InvoiceMembershipItemEntity> membershipItems) {
        if (dto == null) return null;

        InvoiceEntity invoice = InvoiceEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .type(dto.getType())
                .date(dto.getDate())
                .client(client)
                .total(dto.getTotal())
                .status(dto.getStatus())
                .paymentMethod(dto.getPaymentMethod())
                .registeredBy(registeredBy)
                .productItems(productItems)
                .membershipItems(membershipItems)
                .build();

        // Asignar invoice a ítems
        if (productItems != null) {
            productItems.forEach(item -> item.setInvoice(invoice));
        }
        if (membershipItems != null) {
            membershipItems.forEach(item -> item.setInvoice(invoice));
        }

        return invoice;
    }

    // Para update: mapea sobre entidad existente
    public InvoiceEntity toEntity(InvoiceDTO dto, InvoiceEntity existing, ClientEntity client, UserEntity registeredBy,
                                  List<InvoiceProductItemEntity> productItems, List<InvoiceMembershipItemEntity> membershipItems) {
        if (dto == null || existing == null) return null;

        existing.setNumber(dto.getNumber());
        existing.setType(dto.getType());
        existing.setDate(dto.getDate());
        existing.setClient(client);
        existing.setTotal(dto.getTotal());
        existing.setStatus(dto.getStatus());
        existing.setPaymentMethod(dto.getPaymentMethod());
        existing.setRegisteredBy(registeredBy);
        existing.setProductItems(productItems);
        existing.setMembershipItems(membershipItems);


        if (productItems != null) {
            productItems.forEach(item -> item.setInvoice(existing));
        }
        if (membershipItems != null) {
            membershipItems.forEach(item -> item.setInvoice(existing));
        }

        return existing;
    }

    private InvoiceProductItemDTO toProductItemDTO(InvoiceProductItemEntity item) {
        if (item == null) return null;

        return InvoiceProductItemDTO.builder()
                .id(item.getId())
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }

    private InvoiceMembershipItemDTO toMembershipItemDTO(InvoiceMembershipItemEntity item) {
        if (item == null) return null;

        return InvoiceMembershipItemDTO.builder()
                .id(item.getId())
                .membershipId(item.getMembership() != null ? item.getMembership().getId() : null)
                .clientEmail(item.getClient() != null ? item.getClient().getEmail() : null)
                .price(item.getPrice())
                .expirationDate(item.getExpirationDate())
                .quantity(item.getQuantity())
                .build();
    }
}