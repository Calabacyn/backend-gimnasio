package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ClientEntity;
import com.backend_gimnasio.backend_gimnasio.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDto(ClientEntity entity) {
        if (entity == null) return null;

        return ClientDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .nationalId(entity.getNationalId())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .registrationDate(entity.getRegistrationDate())
                .registeredByEmail(entity.getRegisteredByEmail() != null ? entity.getRegisteredByEmail().getEmail() : null)

                .build();
    }


    public ClientEntity toEntity(ClientDTO dto, UserEntity registeredBy) {
        if (dto == null) return null;

        ClientEntity entity = new ClientEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setNationalId(dto.getNationalId());
        entity.setPhone(dto.getPhone());
        entity.setBirthDate(dto.getBirthDate());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setRegisteredByEmail(registeredBy);

        return entity;
    }

    public List<ClientDTO> toListDto(List<ClientEntity> clients) {
        if (clients == null) return List.of();
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



}
