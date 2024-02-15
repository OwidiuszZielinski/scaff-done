package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeightManager extends VerticalLayout {

    public HeightManager(CalculationManager calculationManager) {
        RadioButtonGroup<Integer> heights = createHeightsRadioButton();
        TextField customHeight = new TextField("Custom Height");
        Button setHeightButton = createSetHeightButton();
        setHeightButton.addClickListener(event ->
        {
            if (heights.getValue() == null && customHeight.getValue().isBlank()) {
                Notification.show("Height is required");
            } else if (heights.getValue() != null && !customHeight.getValue().isBlank()) {
                calculationManager.setScaffoldingHeight(Float.parseFloat(customHeight.getValue()));
                calculationManager.calculateResult();
                Notification.show("Height has been set!");
            } else {
                Notification.show("Height has been set!");
                calculationManager.setScaffoldingHeight(heights.getValue());
                calculationManager.calculateResult();
            }
        });
        HorizontalLayout layout = createHorizontal(customHeight, setHeightButton);
        add(heights, layout);
    }

    private static HorizontalLayout createHorizontal(TextField customHeight, Button setHeightButton) {
        HorizontalLayout layout = new HorizontalLayout(customHeight, setHeightButton);
        customHeight.addClassName("home-view-text-field-1");
        return layout;
    }

    private static Button createSetHeightButton() {
        Button setHeightButton = new Button("SET HEIGHT");
        setHeightButton.setWidth("190px");
        setGreenColorButton(setHeightButton);
        return setHeightButton;
    }

    private static void setGreenColorButton(Button setHeightButton) {
        setHeightButton.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        setHeightButton.getStyle().set("margin-top", "37px");
        setHeightButton.getStyle().set("color", "white");
    }

    private RadioButtonGroup<Integer> createHeightsRadioButton() {
        RadioButtonGroup<Integer> heights = new RadioButtonGroup<>();
        heights.setLabel("Height");
        heights.setItems(
                initHeights()
        );
        heights.setValue(1);
        return heights;
    }

    private static List<Integer> initHeights() {
        return IntStream.rangeClosed(1, 36)
                .filter(n -> n % 2 == 0 || n == 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
