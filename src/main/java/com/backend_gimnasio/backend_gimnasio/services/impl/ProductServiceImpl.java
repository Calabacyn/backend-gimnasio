package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProductNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProductDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Product;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProductCategory;
import com.backend_gimnasio.backend_gimnasio.model.mappers.ProductMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.ProductRepository;
import com.backend_gimnasio.backend_gimnasio.repositories.ProductCategoryRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductCategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ProductDTO> getBy(Long id) {
        return productRepository.findById(id).map(productMapper::toDTO);
    }

    @Override
    public void create(ProductDTO productDTO) {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new RuntimeException("El nombre del producto ya está registrado.");
        }

        ProductCategory category = categoryRepository.findById(productDTO.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría indicada no existe."));

        Product product = productMapper.toEntity(productDTO,null, category);

        productRepository.save(product);
    }


    @Override
    public void update(ProductDTO productDTO) {
        Long id = Optional.ofNullable(productDTO.getId())
                .orElseThrow(ProductNotFoundException::new);

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (!existingProduct.getName().equalsIgnoreCase(productDTO.getName()) &&
                productRepository.existsByName(productDTO.getName())) {
            throw new RuntimeException("El nombre del producto ya está registrado.");
        }

        ProductCategory category = categoryRepository.findById(productDTO.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría indicada no existe."));

        Product updatedProduct = productMapper.toEntity(productDTO, existingProduct, category);
        productRepository.save(updatedProduct);
    }


    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }
}
