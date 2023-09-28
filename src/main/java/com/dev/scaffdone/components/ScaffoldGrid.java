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

public class ScaffoldGrid extends Grid<Scaffold> {
    public ScaffoldGrid() {

        initColumns();
        addComponentColumn(item -> {
            Button doneButton = new Button("Done");
            doneButtonHandler(item, doneButton);
            doneButton.addClickListener(click -> {
                isDoneHandler(item, doneButton);
            });
            return doneButton;
        }).setHeader("Done");

        addComponentColumn(item -> {
            Button moreButton = new Button("MORE");
            buttonGreenStyle(moreButton);
            moreButtonHandler(item, moreButton);
            return moreButton;
        }).setHeader("Additional Info");

        addComponentColumn(item -> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            buttonRedStyle(delete);
            delete.setWidth("80px");
            delete.addClickListener(e -> {
                //Dodaj potwierdzenie TAK/NIE USUN Z BAZY
                Notification.show("Deleted!");
            });
            return delete;
        });
    }

    private static void buttonRedStyle(Button delete) {
        delete.addClassName("home-view-button-1");
        delete.getStyle().set("background-color", "#ff6464");
        delete.getStyle().set("color", "white");
    }

    private static void moreButtonHandler(Scaffold item, Button moreButton) {
        moreButton.addClickListener(click -> {
            TextArea dialogTextArea = new TextArea(item.getAdditionalInfo());
            dialogTextArea.setReadOnly(true);
            VerticalLayout dialogLayout = new VerticalLayout(dialogTextArea);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            Dialog moreInfoDialog = new Dialog(dialogLayout);
            dialogStyle(moreInfoDialog);
            Button closeDialog = new Button("Close", e -> {
                moreInfoDialog.close();
            });
            moreInfoDialog.getFooter().add(closeDialog);
            moreInfoDialog.open();
        });
    }

    private static void dialogStyle(Dialog moreInfoDialog) {
        moreInfoDialog.setHeaderTitle("Additional Information");
        moreInfoDialog.setWidth("600px");
        moreInfoDialog.setHeight("400px");
    }

    private static void buttonGreenStyle(Button moreButton) {
        moreButton.getStyle().set("background-color", "#4e8752");
        moreButton.getStyle().set("color", "white");
    }

    private static void doneButtonHandler(Scaffold item, Button button) {
        if (item.isDone()) {
            button.setText("YES");
            buttonGreenStyle(button);
        } else {
            button.setText("NO");
            buttonRedStyle(button);
        }
    }

    private static void isDoneHandler(Scaffold item, Button button) {
        if (item.isDone()) {
            item.setDone(false);
            button.setText("NO");
            buttonRedStyle(button);
        } else {
            button.setText("YES");
            item.setDone(true);
            buttonGreenStyle(button);
        }
    }

    private void initColumns() {
        addColumn(Scaffold::getId).setHeader("Id");
        addColumn(Scaffold::getUsername).setHeader("User");
        addColumn(Scaffold::getModules).setHeader("Modules");
        addColumn(Scaffold::getFrameDim).setHeader("Frames");
        addColumn(Scaffold::getHeight).setHeader("Height");
        addColumn(Scaffold::getTotalLength).setHeader("Total Length");
        addColumn(Scaffold::getResultSquareMeters).setHeader("Square Meters");
    }
}
