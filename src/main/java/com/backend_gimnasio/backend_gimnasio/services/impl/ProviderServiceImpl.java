package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.ProviderNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.ProviderDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.ProviderEntity;
import com.backend_gimnasio.backend_gimnasio.model.mappers.ProviderMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.ProviderRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements IProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public ProviderServiceImpl(ProviderRepository providerRepository,
                               ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    @Override
    public List<ProviderDTO> getAll() {
        return providerRepository.findAll()
                .stream()
                .map(providerMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ProviderDTO> getById(Long id) {
        return providerRepository.findById(id)
                .map(providerMapper::toDTO);
    }

    @Override
    public void create(ProviderDTO providerDTO) {
        if (providerRepository.existsByName(providerDTO.getName())) {
            throw new RuntimeException("El proveedor con este nombre ya existe.");
        }
        if (providerRepository.existsByEmail(providerDTO.getEmail())) {
            throw new RuntimeException("El proveedor con este email ya existe.");
        }


        ProviderEntity provider = providerMapper.toEntity(providerDTO);
        providerRepository.save(provider);
    }

    @Override
    public void update(ProviderDTO providerDTO) {
        Long id = Optional.ofNullable(providerDTO.getId())
                .orElseThrow(ProviderNotFoundException::new);

        ProviderEntity existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));


        if (!existingProvider.getName().equals(providerDTO.getName()) &&
                providerRepository.existsByName(providerDTO.getName())) {
            throw new RuntimeException("El proveedor con este nombre ya existe.");
        }
        if (!existingProvider.getEmail().equals(providerDTO.getEmail()) &&
                providerRepository.existsByEmail(providerDTO.getEmail())) {
            throw new RuntimeException("El proveedor con este email ya existe.");
        }

        // Usamos el mapper para actualizar los campos
        providerMapper.updateEntityFromDTO(providerDTO, existingProvider);
        providerRepository.save(existingProvider);
    }


    @Override
    public void delete(Long id) {
        ProviderEntity existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException(id));

        providerRepository.delete(existingProvider);
    }
}
