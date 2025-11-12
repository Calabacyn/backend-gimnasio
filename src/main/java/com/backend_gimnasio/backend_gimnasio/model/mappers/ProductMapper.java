package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Product;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
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


    public Product toEntity(ProductDTO dto, Product existingProduct, ProductCategory category) {
        if (dto == null) return null;

        Product product = existingProduct != null ? existingProduct : new Product();

        product.setName(dto.getName());
        product.setProductCategory(category);
        product.setDescription(dto.getDescription());
        product.setSalePrice(dto.getSalePrice());
        product.setStock(dto.getStock());

        return product;
    }
}
