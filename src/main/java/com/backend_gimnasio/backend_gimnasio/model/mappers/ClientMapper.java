package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Client;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client entity) {
        if (entity == null) return null;

        return ClientDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .nationalId(entity.getNationalId())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .registrationDate(entity.getRegistrationDate())
                .registeredById(entity.getRegisteredBy() != null ? entity.getRegisteredBy().getId() : null)
                .registeredByName(entity.getRegisteredBy() != null ? entity.getRegisteredBy().getUserName() : null)
                .build();
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

    public List<ClientDTO> toListDto(List<Client> clients) {
        if (clients == null) return List.of();
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



}
