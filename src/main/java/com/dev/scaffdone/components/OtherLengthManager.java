package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherLengthManager extends VerticalLayout {

    private float otherLength;

    public OtherLengthManager(ResultManager resultManager) {
        CheckboxGroup<String> framesCheckBox = createFramesCheckBox();
        Button setLength = createButton();
        TextField customLength = new TextField("Custom Length");
        HorizontalLayout hLayout = new HorizontalLayout();
        customLength.addClassName("home-view-text-field-1");


        setLength.addClickListener(e -> {
            if (!customLength.isEmpty()) {
                this.otherLength = Float.parseFloat(customLength.getValue());
                resultManager.addOtherLength(otherLength);
                System.out.println(otherLength);
                Notification.show("The frame size has been set!");
            } else {
                this.otherLength = getValueFromCheckBox(framesCheckBox);
                System.out.println(otherLength);
                resultManager.addOtherLength(otherLength);
                Notification.show("The frame size has been set!");
            }
        });
        hLayout.add(customLength, setLength);
        add(framesCheckBox, hLayout);
    }

    private static Button createButton() {
        Button addFramesLength = new Button("SET OTHER LENGTH");
        addFramesLength.getStyle().set("top", "33px");
        setGreenTheme(addFramesLength);
        return addFramesLength;
    }

    private static Float getValueFromCheckBox(CheckboxGroup<String> framesCheckBox) {
        return framesCheckBox.getValue()
                .stream()
                .map(Float::valueOf)
                .map(m -> m / 100)
                .reduce(0.00f, Float::sum);
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
            return item + " [ cm ] - " + description;
        });
        return checkboxGroup;
    }

    private static List<String> initSizes() {
        return Arrays.asList("5", "10", "15");
    }

    private static String getDescriptionForItem(String item) {
        Map<String, String> itemDescriptions = new HashMap<>();
        itemDescriptions.put("5", "One frame length");
        itemDescriptions.put("10", "Two frames length");
        itemDescriptions.put("15", "Three frames length");
        return itemDescriptions.getOrDefault(item, "No description available");
    }

}
