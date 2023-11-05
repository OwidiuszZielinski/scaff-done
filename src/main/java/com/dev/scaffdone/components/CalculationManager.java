package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculationManager extends HorizontalLayout {

    private float scaffoldingLength;
    private float scaffoldingHeight;
    private TextArea currentLength = new TextArea("Current length");
    private TextArea currentHeight = new TextArea("Current height");
    private TextArea squareMeters = new TextArea("Square meters");

    public CalculationManager() {
        setFieldsReadOnly();
        setFieldsSize();
        setSpacing(false);
        justifyElements();
        add(currentLength, currentHeight, squareMeters);
    }

    private void justifyElements() {
        currentLength.getStyle().set("margin-left","620px");
        currentLength.getStyle().set("margin-right","20px");
        squareMeters.getStyle().set("margin-left","40px");
        currentHeight.getStyle().set("margin-right","20px");

    }

    private static Button createButton(String text) {
        Button save = new Button(text);
        setGreenButton(save);
        return save;
    }

    public void setScaffoldingLength(float len) {
        this.scaffoldingLength = len;
        this.currentLength.setValue(this.scaffoldingLength + " [ m ]");
    }

    public void addOtherLength(float len) {
        this.scaffoldingLength += len;
        this.currentLength.setValue(this.scaffoldingLength + " [ m ]");
    }

    public void setScaffoldingHeight(float hei) {
        this.scaffoldingHeight = hei;
        this.currentHeight.setValue(hei + " [ m ]");
    }

    public float getSquareMeters(){
        return (float) (Math.round(this.scaffoldingLength * this.scaffoldingHeight * 100.0) / 100.0);

    }
    public void calculateResult() {
        float result = (float) (Math.round(this.scaffoldingLength * this.scaffoldingHeight * 100.0) / 100.0);
        this.squareMeters.setValue(result + " [ m2 ]");
    }

    private void setFieldsReadOnly(){
        currentLength.setReadOnly(true);
        currentHeight.setReadOnly(true);
        squareMeters.setReadOnly(true);

    }

    private void setFieldsSize() {
        currentLength.setWidth("190px");
        currentHeight.setWidth("190px");
        squareMeters.setWidth("190px");
    }

    private static void setGreenButton(Button button) {
        button.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        button.getStyle().set("color", "white");
        button.setWidth("190px");
        button.getStyle().set("top", "40px");
    }

}
