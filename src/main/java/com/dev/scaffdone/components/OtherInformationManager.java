package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherInformationManager extends VerticalLayout {

    private String otherInfo;



    public OtherInformationManager() {

        Button add = createAddButton();

        setWidth("100%");
        add.addClickListener(e -> {
            TextArea info = new TextArea("Other Information");
            VerticalLayout dialogLayout = new VerticalLayout(info);
            dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
            Dialog moreInfoDialog = new Dialog(dialogLayout);
            dialogStyle(moreInfoDialog);
            Button closeDialog = new Button("Close", d -> {
                moreInfoDialog.close();
            });
            Button save = new Button("Save", s->{
                //save data
                moreInfoDialog.close();
            });
            setGreenStyleButton(save);
            moreInfoDialog.getFooter().add(closeDialog,save);
            moreInfoDialog.open();

            if(!info.getValue().isEmpty()){
                this.otherInfo = info.getValue();
                System.out.println("Info set ! " + info.getValue());
                Notification.show("Added!");
            }else {
                Notification.show("Other Info is empty");
            }
        });
        add(add);
    }

    private static Button createAddButton() {
        Button add = new Button("ADD MORE INFO");
        add.setWidth("190px");
        setGreenStyleButton(add);
        return add;
    }
    private static void setGreenStyleButton(Button button){
        button.getStyle().set("background-color", "#4e8752");
        button.getStyle().set("color", "white");

    }

    private static void setGreenStyle(TextArea info) {
        info.setWidth("400px");
        info.setHeight("200px");
        info.addClassName("home-view-text-field-1");
    }

    private static void dialogStyle(Dialog moreInfoDialog) {
        moreInfoDialog.setHeaderTitle("Other Information");
        moreInfoDialog.setWidth("600px");
        moreInfoDialog.setHeight("400px");
    }
}
