package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserCreateDTO;
import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import com.backend_gimnasio.backend_gimnasio.model.mappers.UserMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.UserRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<UserDTO> getBy(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO);
    }

    @Override
    public void create(UserCreateDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        User entity = userMapper.toEntity(userDTO);


        userRepository.save(entity);
    }

    @Override
    public void update(UserDTO userDTO) {
        String email = Optional.ofNullable(userDTO.getEmail())
                .orElseThrow(UserNotFoundException::new);

        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));


        existingUser.setUserName(userDTO.getUserName());
        existingUser.setRoles(userDTO.getRoles());
        existingUser.setStatus(userDTO.getStatus());
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);
    }

    @Override
    public void delete(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        userRepository.delete(user);
    }
}
