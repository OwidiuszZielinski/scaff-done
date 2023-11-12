package com.dev.scaffdone.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalInfoManager extends VerticalLayout {

    private String additionalInfo;
    public AdditionalInfoManager() {
        Button addButton = createAddButton();
        setWidth("100%");
        addButton.addClickListener(e -> {
            createDialog();
        });
        add(addButton);

    }

    private void createDialog() {
        TextArea textInfo = new TextArea("Additional information");
        VerticalLayout dialogLayout = new VerticalLayout(textInfo);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        Dialog moreInfoDialog = new Dialog(dialogLayout);
        setStyleDialog(moreInfoDialog);
        Button close = closeHandler(moreInfoDialog);
        Button save = saveHandler(textInfo, moreInfoDialog);
        GreenButton.setGreenStyleButton(save);
        moreInfoDialog.getFooter().add(close, save);
        moreInfoDialog.open();

    }

    private Button saveHandler(TextArea textInfo, Dialog moreInfoDialog) {
        return new Button("Save", saveEvent -> {
            dialogTextHandler(textInfo, moreInfoDialog);
        });
    }

    private static Button closeHandler(Dialog moreInfoDialog) {
        return new Button("Close", clickEvent -> {
            moreInfoDialog.close();
        });
    }

    private void dialogTextHandler(TextArea textInfo, Dialog moreInfoDialog) {
        if (!textInfo.getValue().isEmpty()) {
            this.additionalInfo = textInfo.getValue();
            Notification.show("Added!");
        } else {
            Notification.show("Other Info is empty");
        }
        moreInfoDialog.close();
    }

    private static GreenButton createAddButton() {
        GreenButton add = new GreenButton("ADD MORE INFO");
        add.setWidth("270px");
        add.getStyle().set("top", "25px");
        return add;
    }

    private static void setStyleDialog(Dialog moreInfoDialog) {
        moreInfoDialog.setHeaderTitle("Other Information");
        moreInfoDialog.setWidth("600px");
        moreInfoDialog.setHeight("400px");
    }
}
