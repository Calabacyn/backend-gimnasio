package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProviderDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProviderEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public ProviderDTO toDTO(ProviderEntity provider) {
        if (provider == null) return null;

        return ProviderDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .email(provider.getEmail())
                .phone(provider.getPhone())
                .address(provider.getAddress())
                .active(provider.getActive())
                .build();
    }

    public ProviderEntity toEntity(ProviderDTO dto) {
        if (dto == null) return null;

        return ProviderEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    public void updateEntityFromDTO(ProviderDTO dto, ProviderEntity entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setActive(dto.getActive() != null ? dto.getActive() : entity.getActive());
    }
}
