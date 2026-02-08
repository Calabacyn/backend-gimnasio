package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductCategoryDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {

    public ProductCategoryDTO toDTO(ProductCategoryEntity category) {
        if (category == null) return null;

        return ProductCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public ProductCategoryEntity toEntity(ProductCategoryDTO dto) {
        if (dto == null) return null;

        ProductCategoryEntity category = new ProductCategoryEntity();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;
    }
}
