package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameLengthManager extends VerticalLayout {

    private float frameLength;

    public FrameLengthManager() {
        CheckboxGroup<String> framesCheckBox = createFramesCheckBox();
        Button addFramesLength = new Button("SET FRAMES LENGTH");
        setGreenTheme(addFramesLength);

        addFramesLength.addClickListener(e -> {
            this.frameLength = framesCheckBox.getValue()
                    .stream()
                    .map(Float::valueOf)
                    .reduce(0.00f, Float::sum);

            Notification.show("The frame size has been set!");
        });
        add(framesCheckBox,addFramesLength);
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
        // Create a custom ItemLabelGenerator

        checkboxGroup.setItemLabelGenerator(item -> {
            String description = getDescriptionForItem(item);
            return item + " [cm] - " + description;
        });
        return checkboxGroup;
    }

    private static List<String> initSizes() {
        return Arrays.asList("5", "10", "15", "20");
    }
    private static String getDescriptionForItem(String item) {
        Map<String, String> itemDescriptions = new HashMap<>();
        itemDescriptions.put("5", "One frame length");
        itemDescriptions.put("10", "Two frames length");
        itemDescriptions.put("15", "Three frames length");
        itemDescriptions.put("20", "Four frames length");
        return itemDescriptions.getOrDefault(item, "No description available");
    }

}
