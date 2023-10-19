package com.dev.scaffdone.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import lombok.Getter;
import lombok.Setter;

public class ResultManager extends HorizontalLayout {

    private float length;

    private float height;

    @Getter
    @Setter
    private TextArea resultOfLength = new TextArea("Current length");

    @Getter
    @Setter
    private TextArea resultOfHeight = new TextArea("Current height");

    @Getter
    @Setter
    private TextArea resultOfSquareMeters = new TextArea("Square meters");

    public ResultManager() {
        resultOfLength.setReadOnly(true);
        resultOfHeight.setReadOnly(true);
        resultOfSquareMeters.setReadOnly(true);
        add(resultOfLength, resultOfHeight, resultOfSquareMeters);
    }

    public void setLength(float length) {
        this.length = length;
        this.resultOfLength.setValue(this.length + " [ m ]");
        System.out.println(this.length);
    }

    public void addOtherLength(float length) {
        this.length += length;
        this.resultOfLength.setValue(this.length + " [ m ]");
        System.out.println(this.length);
    }

    public void setScaffoldingHeight(float height) {
        this.height = height;
        this.resultOfHeight.setValue(height + " [ m ]");
        System.out.println(height);
    }

    public float calculateResult() {
        float result = (float) (Math.round(this.length * this.height * 100.0) / 100.0);
        this.resultOfSquareMeters.setValue(result + " [ m2 ]");
        return result;
    }
}
