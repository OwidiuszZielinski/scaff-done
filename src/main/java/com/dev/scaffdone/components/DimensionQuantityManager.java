package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Dimension;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class DimensionQuantityManager extends VerticalLayout {


    private List<ScaffoldingModule> scaffoldingModule;

    public DimensionQuantityManager() {

        RadioButtonGroup<Integer> quantities = new RadioButtonGroup<>();
        quantities.setLabel("Quantity");
        quantities.addClassName("home-view-combo-box-1");
        quantities.setItems(
                initQuantities()
        );
        quantities.setValue(1);

        Button plus = createDimQtyButton();
        ComboBox<String> dimensions = initDimensionsBox();
        plus.addClickListener(e -> {
            if (dimensions.getValue() == null) {
                Notification.show("Dimension is required");
                return;
            }
            if (quantities.getValue() == 0) {
                Notification.show("Quantities is required");
                return;
            } else {
                this.scaffoldingModule.add( new ScaffoldingModule(
                        Float.parseFloat(dimensions.getValue()),
                        quantities.getValue()));
            }
            System.out.println(scaffoldingModule);
            Notification.show("Added!");
        });

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
                .map(e -> String.format("%.2f", e.getSize()))
                .map(e->e.replace(",","."))
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

    public void setScaffoldingModule(List<ScaffoldingModule> scaffoldingModule) {
        this.scaffoldingModule = scaffoldingModule;
    }


}

