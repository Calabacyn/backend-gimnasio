package com.backend_gimnasio.backend_gimnasio.services.impl;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IAuthService;

import com.backend_gimnasio.backend_gimnasio.enums.RoleEnum;
import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.entities.UserEntity;
import com.backend_gimnasio.backend_gimnasio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    @Override
    public List<GrantedAuthority> getAuthoritiesFromToken(Jwt jwt) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        String userEmail = jwt.getClaimAsString("email");

        UserEntity user = this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        log.info("getAuthoritiesFromToken - user: {} - roles: {}", userEmail, user.getRoles());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PUBLIC"));

        for (RoleEnum role : user.getRoles()) {

            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }

        return grantedAuthorities;
    }

    @Override
    public boolean check(Authentication authentication) {

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String userEmail = jwt.getClaimAsString("email");

            log.info("check - userEmail: {}", userEmail);

            return this.userRepository.findById(userEmail)
                    .isPresent();
        }

        return false;
    }

}