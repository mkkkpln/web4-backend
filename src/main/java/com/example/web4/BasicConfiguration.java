package com.example.web4;

import com.example.web4.jwt.JwtRequestFilter;
import com.example.web4.jwt.JwtTokenUtils;
import com.example.web4.user.UserEntity;
import com.example.web4.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserEntity> username1 = userRepository.existsByUsername(username);
                if (username1.isEmpty()) {
                    throw new UsernameNotFoundException("Not found");
                } else {
                    return User.withUsername(username).password(username1.get().getPassword()).build();
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        ar -> {
                            ar.requestMatchers("/login").permitAll();
                            ar.requestMatchers("/register").permitAll();
                            ar.anyRequest().authenticated();
                        }
                )
                .addFilterAfter(new JwtRequestFilter(jwtTokenUtils), UsernamePasswordAuthenticationFilter.class).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                httpBasic(Customizer.withDefaults());;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }
}
