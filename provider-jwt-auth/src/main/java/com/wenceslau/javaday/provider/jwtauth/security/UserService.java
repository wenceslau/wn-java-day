package com.wenceslau.javaday.provider.jwtauth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!"user".equals(username)) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return new BCryptPasswordEncoder().encode("123");
            }

            @Override
            public String getUsername() {
                return "user";
            }
        };
    }
}
