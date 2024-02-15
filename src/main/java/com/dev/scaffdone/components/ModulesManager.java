package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.dev.scaffdone.core.scaffolding.model.Dimensions;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class ModulesManager extends VerticalLayout {

    private final List<ScaffoldingModule> scaffoldingModules = new ArrayList<>();
    private final CalculationManager calculationManager;

    public ModulesManager(CalculationManager calculationManager) {
        this.calculationManager = calculationManager;
        RadioButtonGroup<Integer> values = createQuantitiesRadioButton();
        Button addToList = createDimQtyButton();
        ComboBox<String> dimensions = createDimensionsBox();
        TextField customDimension = createCustomDimension();
        TextField customQuantity = createCustomQuantity();
        VerticalLayout addCustomLayout = new VerticalLayout(customQuantity, addToList);
        HorizontalLayout dimensionLayout = new HorizontalLayout(dimensions, customDimension);
        HorizontalLayout quantityLayout = new HorizontalLayout(values, addCustomLayout);
        addToList.addClickListener(e -> {
            if (dimensions.getValue() == null && customDimension.isEmpty()) {
                Notification.show("Dimension is required");
                return;
            }
            if (values.getValue() == 0 && customQuantity.isEmpty()) {
                Notification.show("Quantities is required");
                return;
            }
            if (dimensions.getValue() == null) {
                if (values.getValue() == 1 && customQuantity.isEmpty()) {
                    this.scaffoldingModules.add(new ScaffoldingModule(
                            Float.parseFloat(customDimension.getValue()),
                            values.getValue()));
                    calculationManager.setScaffoldingLength(calculateTotalLengthFromModule());
                    Notification.show("Added!");
                    return;
                } else {
                    this.scaffoldingModules.add(new ScaffoldingModule(
                            Float.parseFloat(customDimension.getValue()),
                            Integer.parseInt(customQuantity.getValue())));
                }
                calculationManager.setScaffoldingLength(calculateTotalLengthFromModule());
                Notification.show("Added!");
                return;
            }
            if (values.getValue() == 1 && customQuantity.isEmpty()) {
                this.scaffoldingModules.add(new ScaffoldingModule(
                        Float.parseFloat(dimensions.getValue()),
                        values.getValue()));
                calculationManager.setScaffoldingLength(calculateTotalLengthFromModule());
                Notification.show("Added!");
                return;
            }
            this.scaffoldingModules.add(new ScaffoldingModule(
                    Float.parseFloat(dimensions.getValue()),
                    values.getValue()));

            calculationManager.setScaffoldingLength(calculateTotalLengthFromModule());
            Notification.show("Added!");
        });
        add(dimensionLayout, quantityLayout);
    }

    private TextField createCustomDimension() {
        TextField customDim = new TextField("Custom Dim");
        customDim.addClassName("home-view-text-field-1");
        customDim.setWidth("130px");
        return customDim;
    }

    private TextField createCustomQuantity() {
        TextField customQty = new TextField("CustomQty");
        customQty.addClassName("home-view-text-field-1");
        customQty.setWidth("130px");
        return customQty;
    }

    private static RadioButtonGroup<Integer> createQuantitiesRadioButton() {
        RadioButtonGroup<Integer> quantities = new RadioButtonGroup<>();
        quantities.setLabel("Quantity");
        quantities.setWidth("187px");
        quantities.addClassName("home-view-combo-box-1");
        quantities.setItems(
                initQuantities()
        );
        quantities.setValue(1);
        return quantities;
    }

    private static ComboBox<String> createDimensionsBox() {
        ComboBox<String> dimensions = createComboBox();
        dimensions.setAllowCustomValue(true);
        dimensions.setItems(initDimensions(Dimensions.getSizes()));
        return dimensions;
    }

    private static List<String> initDimensions(List<Dimensions> items) {
        return items
                .stream()
                .filter(e -> e.getSize() > 0.5f)
                .map(e -> String.format("%.2f", e.getSize()))
                .map(e -> e.replace(",", "."))
                .toList();
    }

    private static ComboBox<String> createComboBox() {
        ComboBox<String> dimensions = new ComboBox<>("Dimensions");
        dimensions.addClassName("home-view-combo-box-1");
        dimensions.setWidth("130px");
        return dimensions;
    }

    private static Button createDimQtyButton() {
        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("130px");
        add.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        add.getStyle().set("color", "white");
        return add;
    }

    private static List<Integer> initQuantities() {
        return IntStream.rangeClosed(1, 8)
                .boxed()
                .collect(Collectors.toList());
    }

    public float calculateTotalLengthFromModule() {
        float sum = 0f;
        for (ScaffoldingModule scaffolding : scaffoldingModules) {
            sum += scaffolding.getDimension() * scaffolding.getQuantity();
        }
        return (float) (Math.round(sum * 100.0) / 100.0);
    }

}

