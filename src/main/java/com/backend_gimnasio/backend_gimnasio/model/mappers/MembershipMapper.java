package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.MembershipDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.MembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {

    public MembershipDTO toDTO(MembershipEntity membership) {
        if (membership == null) return null;

        return MembershipDTO.builder()
                .id(membership.getId())
                .name(membership.getName())
                .price(membership.getPrice())
                .membershipType(membership.getMembershipType())
                .durationDays(membership.getDurationDays())
                .description(membership.getDescription())
                .build();
    }

    public MembershipEntity toEntity(MembershipDTO dto) {
        if (dto == null) return null;

        return MembershipEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .membershipType(dto.getMembershipType())
                .durationDays(dto.getDurationDays())
                .description(dto.getDescription())
                .build();
    }
}
