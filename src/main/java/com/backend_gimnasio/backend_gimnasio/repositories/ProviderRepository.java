package com.backend_gimnasio.backend_gimnasio.repositories;

import com.backend_gimnasio.backend_gimnasio.model.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
