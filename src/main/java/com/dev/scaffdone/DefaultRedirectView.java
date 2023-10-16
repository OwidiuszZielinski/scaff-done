package com.dev.scaffdone;

import com.dev.scaffdone.HomeView;
import com.dev.scaffdone.LoginView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AnonymousAllowed
@Route("")
public class DefaultRedirectView extends VerticalLayout implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.isAuthenticated());
            event.forwardTo(HomeView.class);
        } else {
            event.forwardTo(LoginView.class);
        }

    }
}