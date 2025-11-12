package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.MembershipNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.MembershipDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.Membership;
import com.backend_gimnasio.backend_gimnasio.model.mappers.MembershipMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.MembershipRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IMembershipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements IMembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    public MembershipServiceImpl(MembershipRepository membershipRepository,
                                 MembershipMapper membershipMapper) {
        this.membershipRepository = membershipRepository;
        this.membershipMapper = membershipMapper;
    }

    @Override
    public List<MembershipDTO> getAll() {
        return membershipRepository.findAll()
                .stream()
                .map(membershipMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<MembershipDTO> getBy(Long id) {
        return membershipRepository.findById(id).map(membershipMapper::toDTO);
    }

    @Override
    public void create(MembershipDTO membershipDTO) {

        if (membershipRepository.existsByNameIgnoreCase(membershipDTO.getName())) {
            throw new RuntimeException("Membership name already exists.");
        }

        Membership entity = membershipMapper.toEntity(membershipDTO);
        membershipRepository.save(entity);
    }

    @Override
    public void update(MembershipDTO membershipDTO) {
        Long id = Optional.ofNullable(membershipDTO.getId())
                .orElseThrow(MembershipNotFoundException::new);

        Membership existingMembership = membershipRepository.findById(id)
                .orElseThrow(() -> new MembershipNotFoundException(id));


        if (membershipRepository.existsByNameIgnoreCaseAndIdNot(membershipDTO.getName(), id)) {
            throw new RuntimeException("Membership name already exists.");
        }

        existingMembership.setName(membershipDTO.getName());
        existingMembership.setPrice(membershipDTO.getPrice());
        existingMembership.setMembershipType(membershipDTO.getMembershipType());
        existingMembership.setDurationDays(membershipDTO.getDurationDays());
        existingMembership.setDescription(membershipDTO.getDescription());

        membershipRepository.save(existingMembership);
    }

    @Override
    public void delete(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new MembershipNotFoundException(id));

        membershipRepository.delete(membership);
    }
}
