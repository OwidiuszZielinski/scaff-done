package com.dev.scaffdone;

import com.dev.scaffdone.scaffold.DialogView;
import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.CalculateModule;
import com.dev.scaffdone.scaffold.entity.Scaffold;
import com.dev.scaffdone.scaffold.entity.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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

        Grid<Scaffold> grid = createGrid();
        final Scaffold owi = Scaffold.builder().id(1L).modules(List.of(new CalculateModule(Size.SIZE_073.getSize(),5)))
                .settled(true)
                .additionalInfo("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Owi").height(10.0f).build();



        grid.setItems(
                service.add(owi)
        );
        H1 header = new H1("Scaffold Done");
        //<theme-editor-local-classname>
        header.addClassName("home-view-h1-1");

        VerticalLayout title = new VerticalLayout(header);
        //<theme-editor-local-classname>
        title.addClassName("home-view-vertical-layout-1");
        title.setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        add(
                title,
                grid,
                new HorizontalLayout(userHandler(),
                        sizesBox(),
                        framesBoxes(),
                        height()),
                        calculation()

        );

    }

    private static Grid<Scaffold> createGrid() {
        Grid<Scaffold> grid = new Grid<>(Scaffold.class, false);
        grid.addColumn(Scaffold::getId).setHeader("Id");
        grid.addColumn(Scaffold::getUsername).setHeader("User");
        grid.addColumn(Scaffold::getModules).setHeader("Modules");
        grid.addColumn(Scaffold::getFrameSize).setHeader("Frames");
        grid.addColumn(Scaffold::getHeight).setHeader("Height");
        grid.addColumn(Scaffold::getTotalLength).setHeader("Total Length");

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
        grid.addColumn(Scaffold::getResultSquareMeters).setHeader("Square Meters");

        grid.addComponentColumn(item -> {
            Button button = new Button("MORE");

            button.getStyle().set("background-color", "green");
            button.getStyle().set("color", "white");

            button.addClickListener(click -> {
                Dialog dialog = new Dialog();
                TextArea dialogTextArea = new TextArea("Data:");
                dialogTextArea.setReadOnly(true);
                VerticalLayout dialogLayout = new VerticalLayout();

                dialogTextArea.setValue(item.getAdditionalInfo());
                dialog.setHeaderTitle("Additional Information");
                dialog.setWidth("600px");
                dialog.setHeight("400px");

                dialogLayout.add(dialogTextArea);
                dialogLayout.setAlignItems(Alignment.STRETCH);
                dialog.add(dialogLayout);

                Button close = new Button("Close", e -> {
                    dialog.close();
                });
                dialog.getFooter().add(close);
                dialog.open();
            });
            return button;

        }).setHeader("Additional Info");


        grid.addComponentColumn(item -> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            //<theme-editor-local-classname>
            delete.addClassName("home-view-button-1");
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
        Button setUser = new Button("SET USER");
        setUser.setWidth("190px");
        setUser.getStyle().set("background-color", "green");
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

        return new VerticalLayout(comboBox, user,setUser);
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
        add.setWidth("190px");
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
        checkboxGroup.setItems("One frame length - 5 [cm]", "Two frame length - 10 [cm]");

        Button setFrames = new Button("SET FRAMES");
        setFrames.setWidth("190px");
        setFrames.getStyle().set("background-color", "green");
        setFrames.getStyle().set("color", "white");
        setFrames.addClickListener(e -> {
            //Dodaj zapisywanie do listy obiektu ilosc x modul
            Notification.show("User added!");
        });
        return new VerticalLayout(checkboxGroup,setFrames);
    }

    private static VerticalLayout height() {
        RadioButtonGroup<Integer> radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Height");
        radioButtonGroup.setItems(
                IntStream.rangeClosed(2, 22)
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
