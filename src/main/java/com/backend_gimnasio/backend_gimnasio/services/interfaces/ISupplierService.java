package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.SupplierDTO;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<SupplierDTO> getAllSuppliers();

    Optional<SupplierDTO> getSupplierById(Long id);

    SupplierDTO createSupplier(SupplierDTO supplierDTO);

    SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO);

    void deleteSupplier(Long id);
}
