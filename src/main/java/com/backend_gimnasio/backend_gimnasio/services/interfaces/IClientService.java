package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<ClientDTO> getAll();
    Optional<ClientDTO> getBy(String email);
    void create(ClientDTO clientDTO);
    void update(ClientDTO clientDTO);
    void delete(String email);
}
