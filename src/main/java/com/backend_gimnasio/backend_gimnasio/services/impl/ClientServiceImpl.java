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
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        User registeredBy = userRepository.findById(clientDTO.getRegisteredById())
                .orElseThrow(() -> new ClientNotFoundException(clientDTO.getRegisteredById()));

        Client client = clientMapper.toEntity(clientDTO, registeredBy);
        client.setRegistrationDate(LocalDate.now());

        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {

        User registeredBy = Optional.ofNullable(clientDTO.getRegisteredById())
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId)))
                .orElse(null);


        return clientRepository.findById(id)
                .map(client -> {
                    clientMapper.updateEntityFromDto(clientDTO, client, registeredBy);
                    return clientMapper.toDto(clientRepository.save(client));
                })
                .orElseThrow(() -> new ClientNotFoundException(id));
    }



    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.delete(client);
    }
}
