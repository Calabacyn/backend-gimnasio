package com.backend_gimnasio.backend_gimnasio.repositories;

import com.backend_gimnasio.backend_gimnasio.model.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
