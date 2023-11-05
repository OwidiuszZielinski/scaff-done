package com.dev.scaffdone.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class VaadinSecurityConfig extends VaadinWebSecurity {

    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
