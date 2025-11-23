package com.site.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_PATHS = {"/api/**", "/actuator/health", "/actuator/info"};

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csrf is disabled for now, since now only public stateless APIs
        // enable it if  using server based web forms later
        // TODO: set cors not set yet, if there is a front end

        http.securityMatcher(PUBLIC_PATHS)
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().permitAll()
                ).csrf(AbstractHttpConfigurer::disable); // only stateless REST APIs for now

        return http.build();
    }

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
