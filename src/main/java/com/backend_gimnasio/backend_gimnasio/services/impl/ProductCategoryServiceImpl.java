package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProductCategoryNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductCategoryDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductCategory;
import com.backend_gimnasio.backend_gimnasio.model.mappers.ProductCategoryMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.ProductCategoryRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
                                      ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public List<ProductCategoryDTO> getAll() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ProductCategoryDTO> getBy(Long id) {
        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toDTO);
    }

    @Override
    public void create(ProductCategoryDTO dto) {
        if (productCategoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("The category name already exists.");
        }

        ProductCategory entity = productCategoryMapper.toEntity(dto);
        productCategoryRepository.save(entity);
    }

    @Override
    public void update(ProductCategoryDTO dto) {
        Long id = Optional.ofNullable(dto.getId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        ProductCategory existing = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));

        // Verificar si el nuevo nombre ya está en uso por otra categoría
        productCategoryRepository.findByName(dto.getName())
                .filter(cat -> !cat.getId().equals(id))
                .ifPresent(cat -> {
                    throw new RuntimeException("The category name already exists.");
                });

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        productCategoryRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));

        productCategoryRepository.delete(category);
    }
}
