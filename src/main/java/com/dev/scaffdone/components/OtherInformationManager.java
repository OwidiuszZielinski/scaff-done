package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class OtherInformationManager extends VerticalLayout {
    public OtherInformationManager() {
        TextArea info = new TextArea("Other Info");
        setGreenStyle(info);
        Button add = createAddButton();
        setWidth("100%");
        setJustifyContentMode(JustifyContentMode.START);  // Space out components, pushing the button to the far right
        add(info,add);
    }

    private static Button createAddButton() {
        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("200px");
        add.getStyle().set("background-color", "#4e8752");
        add.getStyle().set("color", "white");
        add.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("Added!");
        });
        return add;
    }

    private static void setGreenStyle(TextArea info) {
        info.setWidth("400px");
        info.setHeight("200px");
        info.addClassName("home-view-text-field-1");
    }
}
