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
    public Optional<ClientDTO> getBy(String email) {
        return clientRepository.findByEmail(email).map(clientMapper::toDto);
    }

    @Override
    public void create(ClientDTO client) {
        User registeredBy = userRepository.findByEmail(client.getRegisteredByEmail())
                .orElseThrow(() -> new UserNotFoundException(client.getRegisteredByEmail()));

        Client entity = clientMapper.toEntity(client, registeredBy);
        entity.setRegistrationDate(LocalDate.now());

        clientRepository.save(entity);
    }

    @Override
    public void update(ClientDTO client) {
        String email = Optional.ofNullable(client.getEmail())
                .orElseThrow(ClientNotFoundException::new);

        Client existingClient = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(email));

        User registeredBy = Optional.ofNullable(client.getRegisteredByEmail())
                .map(userEmail -> userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new UserNotFoundException(userEmail)))
                .orElse(null);

        existingClient.updateFromDto(client, registeredBy);
        clientRepository.save(existingClient);
    }

    @Override
    public void delete(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(email));

        clientRepository.delete(client);
    }
}
