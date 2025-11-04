package com.backend_gimnasio.backend_gimnasio.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import com.backend_gimnasio.backend_gimnasio.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

}
