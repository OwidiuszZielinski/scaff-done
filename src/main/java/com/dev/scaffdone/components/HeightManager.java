package com.dev.scaffdone.components;

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


    public HeightManager(ResultManager resultManager) {
        RadioButtonGroup<Integer> heightsRadioButtonGroup = createHeightsRadioButtonGroup();
        TextField customHeight = new TextField("Custom Height");
        Button setHeightButton = createSetHeightButton();

        setHeightButton.addClickListener(event ->
        {
            if (heightsRadioButtonGroup.getValue() == null && customHeight.getValue().isBlank()) {
                Notification.show("Height is required");
            }
            else if (heightsRadioButtonGroup.getValue() != null && !customHeight.getValue().isBlank()) {
                resultManager.setScaffoldingHeight(Float.parseFloat(customHeight.getValue()));
                resultManager.calculateResult();
                Notification.show("Height has been set!");

            } else {
                Notification.show("Height has been set!");
                resultManager.setScaffoldingHeight(heightsRadioButtonGroup.getValue());
                resultManager.calculateResult();

            }
        });


        HorizontalLayout layout = new HorizontalLayout(customHeight,setHeightButton);
        customHeight.addClassName("home-view-text-field-1");
        add(heightsRadioButtonGroup,layout);
    }

    private static Button createSetHeightButton() {
        Button setHeightButton = new Button("SET HEIGHT");
        setHeightButton.setWidth("190px");
        setGreenColorButton(setHeightButton);
        return setHeightButton;
    }

    private static void setGreenColorButton(Button setHeightButton) {
        setHeightButton.getStyle().set("background-color", "#4e8752");
        setHeightButton.getStyle().set("margin-top", "37px");
        setHeightButton.getStyle().set("color", "white");
    }

    private RadioButtonGroup <Integer> createHeightsRadioButtonGroup() {
        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Height");
        radioButtonGroup.setItems(
                initHeights()
        );
        radioButtonGroup.setValue(1);
        return radioButtonGroup;
    }

    private static List<Integer> initHeights() {
        return IntStream.rangeClosed(1, 36)
                .filter(n -> n % 2 == 0 || n == 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
