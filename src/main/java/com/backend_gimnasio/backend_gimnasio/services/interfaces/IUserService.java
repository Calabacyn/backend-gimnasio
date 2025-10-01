package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(Long id);

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(Long id, UserDTO user);
    void deleteUser(Long id);
}
