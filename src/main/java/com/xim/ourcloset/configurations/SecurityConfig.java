package com.xim.ourcloset.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xim.ourcloset.filter.JwtAuthenticationFilter;
import com.xim.ourcloset.services.UserDetailsImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsImp userDetailsImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsImp userDetailsImp, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsImp = userDetailsImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/rest/home/login").permitAll()
                         .requestMatchers("/rest/home/reg").permitAll()
                        .requestMatchers("getuser").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("admin").hasAuthority("ADMIN")
                        .requestMatchers("/rest/home/getcolle").hasAuthority("ADMIN")
                        .requestMatchers("/rest/home/getmodel").hasAuthority("ADMIN")
                        .requestMatchers("beartigiano").hasAuthority("USER")
                        .requestMatchers("getartigiano").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/collection/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/model/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/carta/**").hasAnyAuthority("USER", "ADMIN")

                        .anyRequest().authenticated()
                ).userDetailsService(userDetailsImp)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
