package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.UserCreateDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAll();
    Optional<UserDTO> getBy(String email);
    void create(UserCreateDTO userCreateDTO);
    void update(UserDTO userDTO);
    void delete(String email);
}
