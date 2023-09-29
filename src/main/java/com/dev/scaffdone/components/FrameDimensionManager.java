package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;
import java.util.List;

public class FrameDimensionManager extends VerticalLayout {

    public FrameDimensionManager() {
        CheckboxGroup<String> framesCheckBox = createFramesCheckBox();
        Button addFramesDimensions = new Button("SET FRAMES");
        setGreenTheme(addFramesDimensions);

        addFramesDimensions.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("User added!");
        });
        add(framesCheckBox, addFramesDimensions);
    }

    private static void setGreenTheme(Button setFrames) {
        setFrames.setWidth("190px");
        setFrames.getStyle().set("background-color", "#4e8752");
        setFrames.getStyle().set("color", "white");
    }

    private static CheckboxGroup<String> createFramesCheckBox() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Frame Size");
        checkboxGroup.setItems(initSizes());
        return checkboxGroup;
    }

    private static List<String> initSizes() {
        return Arrays.asList("One frame length - 5 [cm]",
                "Two frames length - 10 [cm]",
                "Three frames length - 15 [cm]",
                "Four frames length - 20 [cm] ");
    }
}
