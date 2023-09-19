package com.dev.scaffdone;

import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.CalculateModule;
import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import com.dev.scaffdone.scaffold.entity.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Route("")
public class HomeView extends VerticalLayout {



    public HomeView(ScaffoldService service) {

        Grid<ScaffoldData> grid = createGrid();
        ScaffoldData owi = ScaffoldData.builder().id(1L).modules(List.of(new CalculateModule(Size.SIZE_073.getSize(),5)))
                .settled(true)
                .username("Owi").height(10.0f).build();


        grid.setItems(
                service.add(owi)
        );

        add(
                new H1("Scaff-Done"),
                grid,
                new HorizontalLayout(userHandler(),
                        sizesBox(),
                        framesBoxes(),
                        height()),
                calculation()

        );

    }

    private static Grid<ScaffoldData> createGrid() {
        Grid<ScaffoldData> grid = new Grid<>(ScaffoldData.class, false);
        grid.addColumn(ScaffoldData::getId).setHeader("Id");
        grid.addColumn(ScaffoldData::getUsername).setHeader("User");
        grid.addColumn(ScaffoldData::getModules).setHeader("Modules");
        grid.addColumn(ScaffoldData::getFrameSize).setHeader("Frames");
        grid.addColumn(ScaffoldData::getHeight).setHeader("Height");
        grid.addColumn(ScaffoldData::getTotalLength).setHeader("Total Length");

        grid.addComponentColumn(item -> {
            Button button = new Button();
            if (item.isSettled()) {
                button.setText("YES");
                button.getStyle().set("color", "white");
                button.getStyle().set("background-color", "green");
            } else {
                button.setText("NO");
                button.getStyle().set("background-color", "red");
            }
            button.addClickListener(click -> {
                // Handle button click here
            });
            return button;

        }).setHeader("Settled");
        grid.addColumn(ScaffoldData::getResultSquareMeters).setHeader("Square Meters");
        grid.addComponentColumn(item -> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            delete.getStyle().set("background-color", "red");
            delete.getStyle().set("color", "white");
            delete.addClickListener(e -> {
                //Dodaj potwierdzenie TAK/NIE USUN Z BAZY
                Notification.show("Deleted!");
            });
            return delete;
        });


        return grid;

    }

    private static VerticalLayout userHandler() {
        TextField user = new TextField("Custom User");
        ComboBox<String> comboBox = new ComboBox<>("Last Users");
        List<String> items = new ArrayList<>();
        //Dopisac uzytkownikow ktorzy istnieja w bazie danych
        items.add("");
        items.add("My Data");
        comboBox.setAllowCustomValue(true);
        comboBox.setItems(items);

        return new VerticalLayout(comboBox, user);
    }

    private static VerticalLayout sizesBox() {
        ComboBox<String> comboBox = new ComboBox<>("Sizes");

        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Quantity");
        radioButtonGroup.setItems(
                IntStream.rangeClosed(1, 6)
                        .boxed()
                        .collect(Collectors.toList())
        );

        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("280px");
        add.getStyle().set("background-color", "green");
        add.getStyle().set("color", "white");
        add.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("Added!");
        });

        List<Size> items = Size.getSizes();
        comboBox.setAllowCustomValue(true);
        comboBox.setItems(items
                .stream()
                .filter(e -> e.getSize() > 0.5f)
                .map(e -> String.valueOf(e.getSize()))
                .toList());

        return new VerticalLayout(comboBox,radioButtonGroup,add);
    }

    private static VerticalLayout framesBoxes() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Frame Size");
        checkboxGroup.setItems("One frame length", "Two frame length");
        return new VerticalLayout(checkboxGroup);
    }

    private static VerticalLayout height() {
        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Height");
        radioButtonGroup.setItems(
                IntStream.rangeClosed(2, 20)
                        .filter(n -> n % 2 == 0)
                        .boxed()
                        .collect(Collectors.toList())
        );
        radioButtonGroup.addValueChangeListener(event->
        {
          //  currentCalculation = radioButtonGroup.getValue();

        });

        TextField customHeight = new TextField("Custom Height");
        return new VerticalLayout(radioButtonGroup, customHeight);
    }

    private static HorizontalLayout calculation() {
        TextField sizeTextField = new TextField("Enter size");
        TextField sizeLabel = new TextField("Current size is: ");

        // Set ValueChangeMode to EAGER to get real-time updates
        sizeTextField.setValueChangeMode(ValueChangeMode.EAGER);

        // Add a value change listener
        sizeTextField.addValueChangeListener(event -> {
           // sizeLabel.setValue(String.valueOf(currentCalculation));  // Update the Label
        });
        return new HorizontalLayout(sizeTextField, sizeLabel);
    }

}
