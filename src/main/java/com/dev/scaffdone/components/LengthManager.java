package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class LengthManager extends VerticalLayout {

    private float currentLength;

    public LengthManager(CalculationManager calculationManager) {
        CheckboxGroup<String> framesCheckBox = createFramesCheckBox();
        Button setLength = createButton();
        TextField customLength = createCustomLength();
        HorizontalLayout layout = new HorizontalLayout(customLength, setLength);
        setLength.addClickListener(e -> {
            if (!customLength.isEmpty()) {
                currentLength = Float.parseFloat(customLength.getValue());
                calculationManager.addOtherLength(roundLength());
                System.out.println(currentLength);
                Notification.show("The frame size has been set!");
            } else {
                currentLength = getValueFromCheckBox(framesCheckBox);
                System.out.println(currentLength);
                calculationManager.addOtherLength(roundLength());
                Notification.show("The size has been set!");
            }
        });
        add(framesCheckBox, layout);
    }

    private static TextField createCustomLength() {
        TextField customLength = new TextField("Custom Length");
        customLength.addClassName("home-view-text-field-1");
        return customLength;
    }

    private static Button createButton() {
        Button addFramesLength = new Button("ADD LENGTH");
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
        setFrames.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        setFrames.getStyle().set("color", "white");
    }

    private static CheckboxGroup<String> createFramesCheckBox() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Frame Size");
        checkboxGroup.setItems(initSizes());
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

    private float roundLength() {
        return (float) (Math.round(currentLength * 100.0) / 100.0);
    }

}
