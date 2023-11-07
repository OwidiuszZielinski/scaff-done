package com.dev.scaffdone;

import com.dev.scaffdone.HomeView;
import com.dev.scaffdone.LoginView;
import com.dev.scaffdone.config.SecurityService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AnonymousAllowed
@RequiredArgsConstructor
@Route("")
public class DefaultRedirectView extends VerticalLayout implements BeforeEnterObserver {

    private final SecurityService securityService;
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("     " + securityService.getAuthenticatedUser().getUsername());
            System.out.println("     " +securityService.getAuthenticatedUser().getAuthorities());
            System.out.println("     " +securityService.getAuthenticatedUser().getPassword());
            event.forwardTo(HomeView.class);
        } else {
            System.out.println(authentication.isAuthenticated());
            event.forwardTo(LoginView.class);
        }

    }
}