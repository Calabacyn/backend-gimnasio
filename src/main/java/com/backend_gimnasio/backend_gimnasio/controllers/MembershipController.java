package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.MembershipNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.MembershipDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IMembershipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    private final IMembershipService membershipService;

    public MembershipController(IMembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping
    public List<MembershipDTO> getAll() {
        return membershipService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipDTO> getBy(@PathVariable Long id) {
        MembershipDTO membership = membershipService.getBy(id)
                .orElseThrow(() -> new MembershipNotFoundException( id ));
        return ResponseEntity.ok(membership);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid MembershipDTO membership) {
        membershipService.create(membership);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid MembershipDTO membership) {
        membershipService.update(membership);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        membershipService.delete(id);
    }
}
