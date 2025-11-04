package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.UserCreateDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User toEntity(UserCreateDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        user.setStatus("ACTIVE");

        return user;
    }
}
