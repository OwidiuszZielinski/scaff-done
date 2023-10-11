package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Dimension;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DimensionQuantityManager extends VerticalLayout {

    public DimensionQuantityManager() {

        RadioButtonGroup<Integer> quantities = new RadioButtonGroup<>();
        quantities.setLabel("Quantity");
        quantities.addClassName("home-view-combo-box-1");
        quantities.setItems(
                initQuantities()
        );

        Button plus = createDimQtyButton();
        plus.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("Added!");
        });
        ComboBox<String> dimensions = initDimensionsBox();
        add(dimensions, quantities, plus);
    }

    private static ComboBox<String> initDimensionsBox() {
        ComboBox<String> dimensions = new ComboBox<>("Dimensions");
        dimensions.addClassName("home-view-combo-box-1");
        List<Dimension> items = Dimension.getSizes();
        dimensions.setAllowCustomValue(true);
        dimensions.setItems(items
                .stream()
                .filter(e -> e.getSize() > 0.5f)
                .map(e -> String.valueOf(e.getSize()))
                .toList());
        return dimensions;
    }

    private static Button createDimQtyButton() {
        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("190px");
        add.getStyle().set("background-color", "#4e8752");
        add.getStyle().set("color", "white");
        return add;
    }

    private static List<Integer> initQuantities() {
        return IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
    }

}

