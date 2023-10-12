package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class UserSelectionManager extends VerticalLayout {

    private String userName;
    private final ScaffoldingService scaffoldingService;

    public UserSelectionManager(ScaffoldingService service) {
        this.scaffoldingService = service;
        TextField customUser = new TextField("Custom User");
        customUser.addClassName("home-view-text-field-1");
        ComboBox<String> lastUsers = new ComboBox<>("Last Users");
        lastUsers.addClassName("home-view-combo-box-1");
        Button setUser = userButton("SET USER");

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
            System.out.println("My first field of calculation " + userName);
            Notification.show("User " + userName + " added!");
        });
        setUsers(lastUsers);
        add(lastUsers, customUser, setUser);
    }

    private void setUsers(ComboBox<String> comboBox) {
        List<String> items = scaffoldingService.getUsedUsers();
        comboBox.setAllowCustomValue(false);
        comboBox.setItems(items);
    }

    private static Button userButton(String name) {
        Button setUser = new Button(name);
        setUser.setWidth("190px");
        setUser.getStyle().set("background-color", "#4e8752");
        setUser.getStyle().set("color", "white");
        return setUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
