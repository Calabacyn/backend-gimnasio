package com.backend_gimnasio.backend_gimnasio.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private Long id; // Opcional: null en creación, valor en actualización

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "Debe indicar la categoría")
    private Long productCategoryId;

    private String description;

    @NotNull(message = "Debe indicar el precio de venta")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double salePrice;

    @NotNull(message = "Debe indicar el stock")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
}
