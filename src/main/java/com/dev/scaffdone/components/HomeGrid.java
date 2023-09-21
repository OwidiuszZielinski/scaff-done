package com.dev.scaffdone.components;

import com.dev.scaffdone.scaffold.entity.Scaffold;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class HomeGrid extends Grid<Scaffold> {
    public HomeGrid() {

       addColumn(Scaffold::getId).setHeader("Id");
       addColumn(Scaffold::getUsername).setHeader("User");
       addColumn(Scaffold::getModules).setHeader("Modules");
       addColumn(Scaffold::getFrameSize).setHeader("Frames");
       addColumn(Scaffold::getHeight).setHeader("Height");
       addColumn(Scaffold::getTotalLength).setHeader("Total Length");

       addComponentColumn(item -> {
            Button button = new Button();
            if (item.isSettled()) {
                button.setText("YES");
                button.getStyle().set("color", "white");
                button.getStyle().set("background-color", "#4e8752");
            } else {
                button.setText("NO");
                button.getStyle().set("background-color", "red");
            }
            button.addClickListener(click -> {
                // Handle button click here
            });
            return button;

        }).setHeader("Settled");
        addColumn(Scaffold::getResultSquareMeters).setHeader("Square Meters");

        addComponentColumn(item -> {
            Button button = new Button("MORE");
            button.getStyle().set("background-color", "#4e8752");
            button.getStyle().set("color", "white");

            button.addClickListener(click -> {
                Dialog dialog = new Dialog();
                TextArea dialogTextArea = new TextArea();
                VerticalLayout dialogLayout = new VerticalLayout();

                dialogTextArea.setReadOnly(true);
                dialogTextArea.setValue(item.getAdditionalInfo());

                dialog.setHeaderTitle("Additional Information");
                dialog.setWidth("600px");
                dialog.setHeight("400px");

                dialogLayout.add(dialogTextArea);
                dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
                dialog.add(dialogLayout);

                Button close = new Button("Close", e -> {
                    dialog.close();
                });
                dialog.getFooter().add(close);
                dialog.open();
            });
            return button;
        }).setHeader("Additional Info");


        addComponentColumn(item -> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            //<theme-editor-local-classname>
            delete.addClassName("home-view-button-1");
            delete.getStyle().set("background-color", "#ff6464");
            delete.getStyle().set("color", "white");
            delete.setWidth("80px");
            delete.addClickListener(e -> {
                //Dodaj potwierdzenie TAK/NIE USUN Z BAZY
                Notification.show("Deleted!");
            });
            return delete;
        });
    }
}
