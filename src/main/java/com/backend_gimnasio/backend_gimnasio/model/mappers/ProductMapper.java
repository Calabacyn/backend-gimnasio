package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(ProductEntity product) {
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .productCategoryId(product.getProductCategory() != null ? product.getProductCategory().getId() : null)
                .description(product.getDescription())
                .salePrice(product.getSalePrice())
                .stock(product.getStock())
                .build();
    }


    public ProductEntity toEntity(ProductDTO dto, ProductEntity existingProduct, ProductCategoryEntity category) {
        if (dto == null) return null;

        ProductEntity product = existingProduct != null ? existingProduct : new ProductEntity();

        product.setName(dto.getName());
        product.setProductCategory(category);
        product.setDescription(dto.getDescription());
        product.setSalePrice(dto.getSalePrice());
        product.setStock(dto.getStock());

        return product;
    }
}
