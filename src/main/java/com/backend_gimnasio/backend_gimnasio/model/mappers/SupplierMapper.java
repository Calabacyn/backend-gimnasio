package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.SupplierDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public static SupplierDTO toDto(Supplier supplier) {
        if (supplier == null) return null;
        return new SupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getDescription()
        );
    }

    public static Supplier toEntity(SupplierDTO dto) {
        if (dto == null) return null;
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setDescription(dto.getDescription());
        return supplier;
    }

    public static void updateEntityFromDto(SupplierDTO dto, Supplier entity) {
        if (dto == null || entity == null) return;
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
    }
}
