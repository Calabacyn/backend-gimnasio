package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductPurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface IProductPurchaseService {

    List<ProductPurchaseDTO> getAll();

    Optional<ProductPurchaseDTO> getById(Long id);

    void create(ProductPurchaseDTO productPurchase);

    void update(ProductPurchaseDTO productPurchase);

    void delete(Long id);
}
