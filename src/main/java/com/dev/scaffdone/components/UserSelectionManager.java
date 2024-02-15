package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSelectionManager extends VerticalLayout {

    private String userName;
    private final ScaffoldingService scaffoldingService;

    public UserSelectionManager(ScaffoldingService service) {
        this.scaffoldingService = service;
        TextField customUser = createTextField();
        ComboBox<String> lastUsers = createComboBoxUsers();
        Button setUser = createUserButton("SET USER");
        setUser.addClickListener(e -> {
            if (customUser.getValue().isBlank() && lastUsers.getValue() == null) {
                Notification.show("User is required");
                return;
            }
            if (customUser.getValue().isBlank()) {
                this.userName = lastUsers.getValue();
            } else {
                this.userName = customUser.getValue();
            }
            // TODO: 15.02.2024 Applikacja ma problem kiedy w bazie jest kalkulacja bez uzytkownika NPE
            Notification.show("User " + userName + " added!");
        });
        setUsers(lastUsers);
        add(lastUsers, customUser, setUser);
    }

    private static ComboBox<String> createComboBoxUsers() {
        ComboBox<String> lastUsers = new ComboBox<>("Last Users");
        lastUsers.addClassName("home-view-combo-box-1");
        lastUsers.setWidth("270px");
        return lastUsers;
    }

    private static TextField createTextField() {
        TextField customUser = new TextField("Custom User");
        customUser.addClassName("home-view-text-field-1");
        customUser.setWidth("270px");
        return customUser;
    }

    private void setUsers(ComboBox<String> comboBox) {
        List<String> items = scaffoldingService.getUsedUsers();
        comboBox.setAllowCustomValue(false);
        comboBox.setItems(items);
    }

    private static Button createUserButton(String name) {
        Button setUser = new Button(name);
        setUser.setWidth("270px");
        setUser.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        setUser.getStyle().set("color", "white");
        return setUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
