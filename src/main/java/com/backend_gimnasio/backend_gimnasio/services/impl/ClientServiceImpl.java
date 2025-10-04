package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.ClientNotFoundException;
import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Client;
import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import com.backend_gimnasio.backend_gimnasio.model.mappers.ClientMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.ClientRepository;
import com.backend_gimnasio.backend_gimnasio.repositories.UserRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IClientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             UserRepository userRepository,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAll() {
        return clientMapper.toListDto(clientRepository.findAll());
    }

    @Override
    public Optional<ClientDTO> getBy(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public void create(ClientDTO client) {
        User registeredBy = userRepository.findById(client.getRegisteredById())
                .orElseThrow(() -> new UserNotFoundException(client.getRegisteredById()));

        Client entity = clientMapper.toEntity(client, registeredBy);
        entity.setRegistrationDate(LocalDate.now());

        clientRepository.save(entity);
    }

    @Override
    public void update(ClientDTO client) {
        Long clientId = Optional.ofNullable(client.getId())
                .orElseThrow(ClientNotFoundException::new);

        User registeredBy = Optional.ofNullable(client.getRegisteredById())
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId)))
                .orElse(null);

        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        existingClient.updateFromDto(client, registeredBy);

        clientRepository.save(existingClient);
    }


    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.delete(client);
    }
}
