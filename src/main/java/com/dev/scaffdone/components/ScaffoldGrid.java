package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class ScaffoldGrid extends Grid<Scaffolding> {
    public ScaffoldGrid() {

        initColumns();
        addComponentColumn(ScaffoldGrid::createDoneButton).setHeader("Done");
        addComponentColumn(ScaffoldGrid::createMoreButton).setHeader("Other Info");
        addComponentColumn(item -> createDeleteButton());
    }

    private static Button createDeleteButton() {
        Button delete = new Button(VaadinIcon.TRASH.create());
        buttonRedStyle(delete);
        delete.setWidth("80px");
        delete.addClickListener(e -> {
            //Dodaj potwierdzenie TAK/NIE USUN Z BAZY
            Notification.show("Deleted!");
        });
        return delete;
    }

    private static Button createDoneButton(Scaffolding item) {
        Button doneButton = new Button("Done");
        doneButtonHandler(item, doneButton);
        doneButton.addClickListener(click -> {
            isDoneHandler(item, doneButton);
        });
        return doneButton;
    }

    private static Button createMoreButton(Scaffolding item) {
        Button moreButton = new Button("MORE");
        buttonGreenStyle(moreButton);
        moreButtonHandler(item, moreButton);
        return moreButton;
    }

    private static void buttonRedStyle(Button delete) {
        delete.addClassName("home-view-button-1");
        delete.getStyle().set("background-color", "#ff6464");
        delete.getStyle().set("color", "white");
    }

    private static void moreButtonHandler(Scaffolding item, Button moreButton) {
        moreButton.addClickListener(click -> {
            VerticalLayout dialogLayout = createLayoutWithTextArea(item);
            Dialog moreInfoDialog = createDialog(dialogLayout);
            Button closeDialog = new Button("Close", e -> {
                moreInfoDialog.close();
            });
            moreInfoDialog.getFooter().add(closeDialog);
            moreInfoDialog.open();
        });
    }

    private static Dialog createDialog(VerticalLayout dialogLayout) {
        Dialog moreInfoDialog = new Dialog(dialogLayout);
        dialogStyle(moreInfoDialog);
        return moreInfoDialog;
    }

    private static VerticalLayout createLayoutWithTextArea(Scaffolding item) {
        TextArea dialogTextArea = new TextArea("Other Information");
        dialogTextArea.setReadOnly(true);
        dialogTextArea.setValue(item.getOtherInformation());
        VerticalLayout layout = new VerticalLayout(dialogTextArea);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        return layout;
    }

    private static void dialogStyle(Dialog moreInfoDialog) {
        moreInfoDialog.setHeaderTitle("Other Information");
        moreInfoDialog.setWidth("600px");
        moreInfoDialog.setHeight("400px");
    }

    private static void buttonGreenStyle(Button moreButton) {
        moreButton.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        moreButton.getStyle().set("color", "white");
    }

    private static void doneButtonHandler(Scaffolding item, Button button) {
        if (item.isDone()) {
            button.setText("YES");
            buttonGreenStyle(button);
        } else {
            button.setText("NO");
            buttonRedStyle(button);
        }
    }

    private static void isDoneHandler(Scaffolding item, Button button) {
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
        addColumn(Scaffolding::getId).setHeader("Id");
        addColumn(Scaffolding::getUsername).setHeader("User");
        addColumn(Scaffolding::getModules).setHeader("Modules");
        addColumn(Scaffolding::getHeight).setHeader("Height");
        addColumn(Scaffolding::getTotalLength).setHeader("Total Length");
        addColumn(Scaffolding::getResultSquareMeters).setHeader("Square Meters");
    }
}
