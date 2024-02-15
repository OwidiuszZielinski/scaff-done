package com.dev.scaffdone;

import com.dev.scaffdone.config.SecurityService;
import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.dev.scaffdone.core.user.UserDetailsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.RequiredArgsConstructor;

@Route("register")
@PageTitle("Sign up")
@AnonymousAllowed
@RequiredArgsConstructor
public class RegisterView extends Composite {

    private final SecurityService securityService;
    private final UserDetailsService userService;

    @Override
    protected Component initContent() {
        PasswordField confirmPassword = new PasswordField("Confirm Password");
        confirmPassword.setWidth("300px");
        PasswordField password = new PasswordField("Password");
        password.setWidth("300px");
        EmailField email = new EmailField("Email");
        email.setWidth("300px");
        TextField username = new TextField("Username");
        username.setWidth("300px");
        VerticalLayout layout = crateRegisterLayout(username, email, password, confirmPassword);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return layout;
    }

    private VerticalLayout crateRegisterLayout(TextField username, EmailField email, PasswordField password, PasswordField confirmPassword) {
        Button button = new Button("Send", event -> register(
                username.getValue(),
                email.getValue(),
                password.getValue(),
                confirmPassword.getValue())
        );
        button.setWidth("300px");
        button.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        button.getStyle().set("color", "white");
        VerticalLayout layout = new VerticalLayout(
                new H2("Sign up"),
                username,
                email,
                password,
                confirmPassword,
                button,
                new RouterLink("Sign In", LoginView.class)
        );
        layout.setSizeFull();
        return layout;
    }

    private void register(String username, String email, String password, String confirmPassword) {
        if (username.trim().isEmpty()) {
            Notification.show("Enter a username");
        } else if (password.isEmpty()) {
            Notification.show("Enter a password");
        } else if (!password.equals(confirmPassword)) {
            Notification.show("Passwords don't match");
        } else if (userService.loadUserByUsername(username) != null) {
            Notification.show("Username is already taken. Please choose a different one.");
        } else if (userService.loadUserByEmail(email) != null) {
            Notification.show("Email is already taken. Please use a different one.");
        } else {
            userService.register(username, email, passwordEncode(password));
            Notification.show("Registration succeeded");
        }
    }

    private String passwordEncode(String password) {
        String encode = securityService.passwordEncoder().encode(password);
        System.out.println(encode);
        return encode;
    }
}
