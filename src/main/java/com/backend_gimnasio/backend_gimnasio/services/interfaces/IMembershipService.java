package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.MembershipDTO;

import java.util.List;
import java.util.Optional;

public interface IMembershipService {

    List<MembershipDTO> getAll();

    Optional<MembershipDTO> getBy(Long id);

    void create(MembershipDTO membershipDTO);

    void update(MembershipDTO membershipDTO);

    void delete(Long id);
}
