package com.dev.scaffdone.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void register(String login, String email, String password) {
        repository.save(
                User.builder()
                        .login(login)
                        .roles(Set.of(Role.USER))
                        .email(email)
                        .password(password)
                        .build()
        );
    }
}
