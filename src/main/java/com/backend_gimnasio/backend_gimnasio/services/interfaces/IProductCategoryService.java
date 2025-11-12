package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductCategoryDTO;
import java.util.List;
import java.util.Optional;

public interface IProductCategoryService {

    List<ProductCategoryDTO> getAll();

    Optional<ProductCategoryDTO> getBy(Long id);

    void create(ProductCategoryDTO productCategoryDTO);

    void update(ProductCategoryDTO productCategoryDTO);

    void delete(Long id);
}
