package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IAuthService {

    List<GrantedAuthority> getAuthoritiesFromToken(Jwt jwt);
    boolean check(Authentication authentication);

}
