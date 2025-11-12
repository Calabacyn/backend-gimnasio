package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.ProviderDTO;

import java.util.List;
import java.util.Optional;

public interface IProviderService {

    List<ProviderDTO> getAll();

    Optional<ProviderDTO> getById(Long id);

    void create(ProviderDTO provider);

    void update(ProviderDTO provider);

    void delete(Long id);
}
