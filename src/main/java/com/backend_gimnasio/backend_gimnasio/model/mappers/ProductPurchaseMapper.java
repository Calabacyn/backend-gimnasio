package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductPurchaseDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductPurchaseEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProviderEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ProductPurchaseMapper {

    public  ProductPurchaseDTO toDTO(ProductPurchaseEntity entity) {
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

    public ProductPurchaseEntity toEntity(ProductPurchaseDTO dto, ProductEntity product, ProviderEntity provider, UserEntity user) {
        if (dto == null) return null;
        return ProductPurchaseEntity.builder()
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


    public void updateEntityFromDTO(ProductPurchaseDTO dto, ProductPurchaseEntity entity,
                                    ProductEntity product, ProviderEntity provider, UserEntity user) {
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

