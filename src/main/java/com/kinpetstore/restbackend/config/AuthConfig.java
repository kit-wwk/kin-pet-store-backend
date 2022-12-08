package com.kinpetstore.restbackend.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    private static final Logger logger = LogManager.getLogger(AuthConfig.class);
    @Value(value = "${spring.security.oauth2.client.registration.auth0.client-id}")
    public String clientId;
    @Value(value = "${spring.security.oauth2.client.provider.auth0.issuer-uri}")
    public String issuerUri;
    @Value("${auth0.audience}")
    private String audience;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .headers()
                .contentTypeOptions().and()
                .xssProtection().and()
                .cacheControl().and()
                .httpStrictTransportSecurity().and()
                .frameOptions().and()
                .and()

                .authorizeHttpRequests(requests ->
                        {
                            try {
                                requests.requestMatchers(
                                                new AntPathRequestMatcher("/", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/swagger-ui.html", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/v3/api-docs/**", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/login/oauth2/authorize", HttpMethod.GET.toString())
                                        )
                                        .permitAll()
                                        .requestMatchers(
                                                new AntPathRequestMatcher("/api/v1/pet/**", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/api/v1/store/**", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/api/v1/district/**", HttpMethod.GET.toString()),
                                                new AntPathRequestMatcher("/api/v1/pet/search", HttpMethod.POST.toString()),
                                                new AntPathRequestMatcher("/api/v1/store/search", HttpMethod.POST.toString()),
                                                new AntPathRequestMatcher("/api/v1/district/search", HttpMethod.POST.toString())
                                        )
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                                ;
                            } catch (Exception e) {
                                logger.info("e: {}", e);
                                throw new RuntimeException(e);
                            }
                        }
                )
                .logout()
                .logoutUrl("/logout")
                .and()
                .oauth2ResourceServer()
                .jwt()
                .and()
                .and().build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuerUri);
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder;
    }

}