package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProductPurchaseNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductPurchaseDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductPurchaseEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProviderEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.UserEntity;
import com.backend_gimnasio.backend_gimnasio.model.mappers.ProductPurchaseMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.ProductPurchaseRepository;
import com.backend_gimnasio.backend_gimnasio.repositories.ProductRepository;
import com.backend_gimnasio.backend_gimnasio.repositories.ProviderRepository;
import com.backend_gimnasio.backend_gimnasio.repositories.UserRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProductPurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPurchaseServiceImpl implements IProductPurchaseService {

    private final ProductPurchaseRepository productPurchaseRepository;
    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final ProductPurchaseMapper productPurchaseMapper;

    public ProductPurchaseServiceImpl(ProductPurchaseRepository productPurchaseRepository,
                                      ProductRepository productRepository,
                                      ProviderRepository providerRepository,
                                      UserRepository userRepository,
                                      ProductPurchaseMapper productPurchaseMapper) {
        this.productPurchaseRepository = productPurchaseRepository;
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.productPurchaseMapper = productPurchaseMapper;
    }

    @Override
    public List<ProductPurchaseDTO> getAll() {
        return productPurchaseRepository.findAll()
                .stream()
                .map(productPurchaseMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ProductPurchaseDTO> getById(Long id) {
        return productPurchaseRepository.findById(id)
                .map(productPurchaseMapper::toDTO);
    }

    @Override
    @Transactional
    public void create(ProductPurchaseDTO productPurchaseDTO) {
        ProductEntity product = productRepository.findById(productPurchaseDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProviderEntity provider = providerRepository.findById(productPurchaseDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        UserEntity user = userRepository.findById(productPurchaseDTO.getRegisteredBy())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (productPurchaseDTO.getTotalCost() == null) {
            productPurchaseDTO.setTotalCost(
                    productPurchaseDTO.getUnitCost().multiply(BigDecimal.valueOf(productPurchaseDTO.getQuantity()))
            );
        }


        ProductPurchaseEntity purchase = productPurchaseMapper.toEntity(productPurchaseDTO, product, provider, user);

        productPurchaseRepository.save(purchase);

        product.setStock(product.getStock() + productPurchaseDTO.getQuantity());
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(ProductPurchaseDTO productPurchaseDTO) {
        Long id = Optional.ofNullable(productPurchaseDTO.getId())
                .orElseThrow(ProductPurchaseNotFoundException::new);

        ProductPurchaseEntity existingPurchase = productPurchaseRepository.findById(id)
                .orElseThrow(() -> new ProductPurchaseNotFoundException(id));

        ProductEntity product = productRepository.findById(productPurchaseDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProviderEntity provider = providerRepository.findById(productPurchaseDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        UserEntity user = userRepository.findById(productPurchaseDTO.getRegisteredBy())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Ajuste de stock: restamos la cantidad anterior y sumamos la nueva
        int oldQuantity = existingPurchase.getQuantity();
        int newQuantity = productPurchaseDTO.getQuantity();
        product.setStock(product.getStock() - oldQuantity + newQuantity);

        productPurchaseMapper.updateEntityFromDTO(productPurchaseDTO, existingPurchase, product, provider, user);

        productPurchaseRepository.save(existingPurchase);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductPurchaseEntity existingPurchase = productPurchaseRepository.findById(id)
                .orElseThrow(() -> new ProductPurchaseNotFoundException(id));

        ProductEntity product = existingPurchase.getProduct();
        product.setStock(product.getStock() - existingPurchase.getQuantity());
        productRepository.save(product);

        productPurchaseRepository.delete(existingPurchase);
    }
}
