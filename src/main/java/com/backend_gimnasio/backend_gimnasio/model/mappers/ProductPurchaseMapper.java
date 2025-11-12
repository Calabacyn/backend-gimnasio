package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductPurchaseDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Product;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductPurchase;
import com.backend_gimnasio.backend_gimnasio.model.entities.Provider;
import com.backend_gimnasio.backend_gimnasio.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ProductPurchaseMapper {

    public  ProductPurchaseDTO toDTO(ProductPurchase entity) {
        if (entity == null) return null;
        return ProductPurchaseDTO.builder()
                .id(entity.getId())
                .purchaseDate(entity.getPurchaseDate())
                .quantity(entity.getQuantity())
                .unitCost(entity.getUnitCost())
                .totalCost(entity.getTotalCost())
                .invoiceNumber(entity.getInvoiceNumber())
                .notes(entity.getNotes())
                .productId(entity.getProduct().getId())
                .providerId(entity.getProvider().getId())
                .registeredBy(entity.getRegisteredBy().getEmail())
                .build();
    }

    public  ProductPurchase toEntity(ProductPurchaseDTO dto, Product product, Provider provider, UserEntity user) {
        if (dto == null) return null;
        return ProductPurchase.builder()
                .purchaseDate(dto.getPurchaseDate())
                .quantity(dto.getQuantity())
                .unitCost(dto.getUnitCost())
                .totalCost(dto.getTotalCost())
                .invoiceNumber(dto.getInvoiceNumber())
                .notes(dto.getNotes())
                .product(product)
                .provider(provider)
                .registeredBy(user)
                .build();
    }


    public void updateEntityFromDTO(ProductPurchaseDTO dto, ProductPurchase entity,
                                           Product product, Provider provider, UserEntity user) {
        entity.setPurchaseDate(dto.getPurchaseDate());
        entity.setQuantity(dto.getQuantity());
        entity.setUnitCost(dto.getUnitCost());
        entity.setTotalCost(dto.getUnitCost().multiply(BigDecimal.valueOf(dto.getQuantity())));
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setNotes(dto.getNotes());
        entity.setProduct(product);
        entity.setProvider(provider);
        entity.setRegisteredBy(user);
    }
}

