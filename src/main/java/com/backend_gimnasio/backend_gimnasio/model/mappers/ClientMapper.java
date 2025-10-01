package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Client;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client entity) {
        if (entity == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setNationalId(entity.getNationalId());
        dto.setPhone(entity.getPhone());
        dto.setBirthDate(entity.getBirthDate());
        dto.setRegistrationDate(entity.getRegistrationDate());

        if (entity.getRegisteredBy() != null) {
            dto.setRegisteredById(entity.getRegisteredBy().getId());
            dto.setRegisteredByName(entity.getRegisteredBy().getUserName());
        }

        return dto;
    }

    public Client toEntity(ClientDTO dto, User registeredBy) {
        if (dto == null) return null;

        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setNationalId(dto.getNationalId());
        entity.setPhone(dto.getPhone());
        entity.setBirthDate(dto.getBirthDate());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setRegisteredBy(registeredBy);

        return entity;
    }

    public void updateEntityFromDto(ClientDTO dto, Client entity, User registeredBy) {
        if (dto == null || entity == null) return;

        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getNationalId() != null) entity.setNationalId(dto.getNationalId());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getBirthDate() != null) entity.setBirthDate(dto.getBirthDate());
        if (dto.getRegistrationDate() != null) entity.setRegistrationDate(dto.getRegistrationDate());
        if (registeredBy != null && registeredBy.getId() != null) {
            entity.setRegisteredBy(registeredBy);
        }
    }
}
