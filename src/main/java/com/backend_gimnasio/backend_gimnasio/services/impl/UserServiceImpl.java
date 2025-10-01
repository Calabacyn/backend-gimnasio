package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.model.dtos.UserDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import com.backend_gimnasio.backend_gimnasio.model.mappers.UserMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.UserRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(userDTO.getUserName());
                    user.setEmail(userDTO.getEmail());
                    user.setPassword(userDTO.getPassword());
                    user.setStatus(userDTO.getStatus());
                    user.setUpdatedAt(LocalDateTime.now());
                    return UserMapper.toDTO(userRepository.save(user));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: "+id));
        userRepository.delete(user);
    }

}
