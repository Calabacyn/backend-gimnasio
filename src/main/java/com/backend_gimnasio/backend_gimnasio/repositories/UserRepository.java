package com.backend_gimnasio.backend_gimnasio.repositories;

import com.backend_gimnasio.backend_gimnasio.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
