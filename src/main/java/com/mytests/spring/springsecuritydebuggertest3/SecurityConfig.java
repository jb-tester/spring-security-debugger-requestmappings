package com.mytests.spring.springsecuritydebuggertest3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("SUPER")
                        .requestMatchers(HttpMethod.POST,"/all/**").hasAnyRole("USER","ADMIN","SUPER")
                        .requestMatchers(HttpMethod.GET,"/all/**").authenticated()
                        .anyRequest().permitAll()
                );
        // .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}user")
                .roles("USER").build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN").build();
        UserDetails superuser = User.withUsername("super")
                .password("{noop}super")
                .roles("ADMIN","SUPER").build();

        return new InMemoryUserDetailsManager(user, admin, superuser);
    }
}