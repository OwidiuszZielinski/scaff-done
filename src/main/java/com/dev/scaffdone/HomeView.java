package com.dev.scaffdone;


import com.dev.scaffdone.components.ScaffoldGrid;
import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.CalculateModule;
import com.dev.scaffdone.scaffold.entity.Scaffold;
import com.dev.scaffdone.scaffold.entity.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Route("")
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {


    public HomeView(ScaffoldService service) {

        //addClassName("home-view-h1-1");
        ScaffoldGrid grid = new ScaffoldGrid();

        final Scaffold owi = Scaffold.builder().id(1L).modules(List.of(new CalculateModule(Size.SIZE_073.getSize(), 5)))
                .done(true)
                .additionalInfo("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Owi").height(10.0f).build();

        final Scaffold owi2 = Scaffold.builder().id(1L).modules(List.of(new CalculateModule(Size.SIZE_073.getSize(), 5)))
                .done(true)
                .additionalInfo("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Owi").height(10.0f).build();

        grid.setItems(
                service.add(owi),
                service.add(owi2)
        );
        H1 header = new H1("Scaffold Done");
        //<theme-editor-local-classname>
        header.addClassName("home-view-h1-1");

        header.addClassName("home-view-h1-1");

        VerticalLayout title = new VerticalLayout(header);

        title.addClassName("home-view-vertical-layout-1");
       


        add(
                title,
                grid,
                new HorizontalLayout(userHandler(),
                        sizesBox(),
                        framesBoxes(),
                        height()),
                    additionalInfo(),
                    calculation()

        );
    }


    private static VerticalLayout additionalInfo(){
        VerticalLayout horizontalLayout = new VerticalLayout();
        TextArea info = new TextArea("Additional Info");
        info.setWidth("400px");
        info.setHeight("200px");
        info.addClassName("home-view-text-field-1");
        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("200px");
        add.getStyle().set("background-color", "#4e8752");
        add.getStyle().set("color", "white");
        add.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("Added!");
        });
        horizontalLayout.setWidth("100%");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.START);  // Space out components, pushing the button to the far right
        horizontalLayout.add(info,add);
        return horizontalLayout;



    }

    private static VerticalLayout userHandler() {
        TextField user = new TextField("Custom User");
        user.addClassName("home-view-text-field-1");
        ComboBox<String> comboBox = new ComboBox<>("Last Users");
        comboBox.addClassName("home-view-combo-box-1");
        Button setUser = new Button("SET USER");
        setUser.setWidth("190px");
        setUser.getStyle().set("background-color", "#4e8752");
        setUser.getStyle().set("color", "white");
        setUser.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("User added!");
        });
        List<String> items = new ArrayList<>();
        //Dopisac uzytkownikow ktorzy istnieja w bazie danych
        items.add("");
        items.add("My Data");
        comboBox.setAllowCustomValue(true);
        comboBox.setItems(items);

        return new VerticalLayout(comboBox, user, setUser);
    }

    private static VerticalLayout sizesBox() {
        ComboBox<String> comboBox = new ComboBox<>("Sizes");
        //<theme-editor-local-classname>
        comboBox.setOverlayClassName("home-view-combo-box-1");
        //<theme-editor-local-classname>
        comboBox.addClassName("home-view-combo-box-1");

        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Quantity");
        radioButtonGroup.addClassName("home-view-combo-box-1");
        radioButtonGroup.setItems(
                IntStream.rangeClosed(1, 6)
                        .boxed()
                        .collect(Collectors.toList())
        );

        Button add = new Button(VaadinIcon.PLUS.create());
        add.setWidth("190px");
        add.getStyle().set("background-color", "#4e8752");
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

        return new VerticalLayout(comboBox, radioButtonGroup, add);
    }

    private static VerticalLayout framesBoxes() {

        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Frame Size");
        checkboxGroup.setItems("One frame length - 5 [cm]", "Two frames length - 10 [cm]","Three frames length - 15 [cm]","Four frames length - 20 [cm] ");

        Button setFrames = new Button("SET FRAMES");
        setFrames.setWidth("190px");
        setFrames.getStyle().set("background-color", "#4e8752");
        setFrames.getStyle().set("color", "white");
        setFrames.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("User added!");
        });
        return new VerticalLayout(checkboxGroup, setFrames);
    }

    private static VerticalLayout height() {
        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Height");
        radioButtonGroup.setItems(
                IntStream.rangeClosed(2, 36)
                        .filter(n -> n % 2 == 0)
                        .boxed()
                        .collect(Collectors.toList())
        );
        radioButtonGroup.addValueChangeListener(event ->
        {
            //  currentCalculation = radioButtonGroup.getValue();

        });
        TextField customHeight = new TextField("Custom Height");
        Button setHeightButton = new Button("SET HEIGHT");


        setHeightButton.setWidth("190px");
        setHeightButton.getStyle().set("background-color", "#4e8752");
        setHeightButton.getStyle().set("margin-top", "37px");
        setHeightButton.getStyle().set("color", "white");
        setHeightButton.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("Height Set!");
        });
        HorizontalLayout horizontalLayout = new HorizontalLayout(customHeight,setHeightButton);

        //<theme-editor-local-classname>
        customHeight.addClassName("home-view-text-field-1");
        return new VerticalLayout(radioButtonGroup, horizontalLayout);
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
