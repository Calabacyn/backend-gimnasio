package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.SupplierNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.SupplierDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Supplier;
import com.backend_gimnasio.backend_gimnasio.model.mappers.SupplierMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.SupplierRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements ISupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(SupplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierDTO> getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(SupplierMapper::toDto);
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = SupplierMapper.toEntity(supplierDTO);
        return SupplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    SupplierMapper.updateEntityFromDto(supplierDTO, supplier);
                    return SupplierMapper.toDto(supplierRepository.save(supplier));
                })
                .orElseThrow(() -> new SupplierNotFoundException(id));
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));
        supplierRepository.delete(supplier);
    }
}
