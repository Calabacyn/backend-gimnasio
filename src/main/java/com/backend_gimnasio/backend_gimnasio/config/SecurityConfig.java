package com.backend_gimnasio.backend_gimnasio.config;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IAuthService;
import com.backend_gimnasio.backend_gimnasio.enums.RoleEnum;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;


import com.backend_gimnasio.backend_gimnasio.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Environment environment;

    @Bean
    public JwtDecoder jwtDecoder() {

        // Solo se valida el audience del token, y se verifica con las firmas publicas de Google que el token
        // recibido de Google tenga una firma valida

        String jwkSetUri = this.environment.getProperty("jwt.jwk-set-uri");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

        String expectedAudience = this.environment.getProperty("jwt.audience");

        OAuth2TokenValidator<Jwt> audienceValidator = jwt -> {

            if (jwt.getAudience().contains(expectedAudience)) {
                return OAuth2TokenValidatorResult.success();
            } else {
                OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);
                return OAuth2TokenValidatorResult.failure(error);
            }

        };

        jwtDecoder.setJwtValidator(audienceValidator);

        return jwtDecoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(IAuthService authService) {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            try {

                return authService.getAuthoritiesFromToken(jwt);

            } catch (UserNotFoundException ex) {

                log.warn("jwtAuthenticationConverter - Warning - User not found: {}", ex.getMessage());
                return Collections.emptyList();
            }
        });

        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {

        http

                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth

                                .requestMatchers("/test/public").permitAll()

                                // Test endpoints
                                .requestMatchers("/test/admin").hasRole(RoleEnum.ADMIN.name())
                                .requestMatchers("/test/client").hasAnyRole(RoleEnum.CLIENT.name(), RoleEnum.ADMIN.name())
                                .requestMatchers("/test/staff").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())



                                //.requestMatchers("/clients/**").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())


                                //.requestMatchers("/memberships/**").hasAnyRole(RoleEnum.CLIENT.name(), RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())


                                .requestMatchers("/invoices/**").hasAnyRole(RoleEnum.CLIENT.name(), RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())


                        //.requestMatchers("/products/**").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())
                        //.requestMatchers("/product-categories/**").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())
                        //.requestMatchers("/product-purchases/**").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())
                        //.requestMatchers("/providers/**").hasAnyRole(RoleEnum.STAFF.name(), RoleEnum.ADMIN.name())


                        //.requestMatchers("/users/**").hasRole(RoleEnum.ADMIN.name())
                )
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir todos los orígenes, métodos y headers
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
