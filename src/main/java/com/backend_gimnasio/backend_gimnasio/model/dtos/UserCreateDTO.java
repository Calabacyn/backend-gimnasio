package com.backend_gimnasio.backend_gimnasio.model.dtos;

import com.backend_gimnasio.backend_gimnasio.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserCreateDTO {

    @NotBlank
    private String userName;

    @Email
    @NotBlank
    private String email;

    private String password;

    private Set<RoleEnum> roles;

}
