package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Role;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        String roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));

        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                null, // Nunca exponer password
                roleNames,
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setRoles(new HashSet<>()); // por ahora vacío o manejar desde otro mapper
        return user;
    }
}
