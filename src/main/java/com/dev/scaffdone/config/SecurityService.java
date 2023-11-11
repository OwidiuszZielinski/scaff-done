package com.dev.scaffdone.config;

import com.dev.scaffdone.core.user.User;
import com.vaadin.flow.spring.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationContext authenticationContext;


    public User getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(User.class)
                .orElseThrow(() -> new RuntimeException("NO AUTH"));
    }

    public void logout() {
        authenticationContext.logout();
    }


}
