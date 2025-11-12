package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductDTO;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<ProductDTO> getAll();

    Optional<ProductDTO> getBy(Long id);

    void create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);
}
