package com.dev.scaffdone.components;

import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;

public class GreenButton extends Button {

    public GreenButton(String text) {
        super(text);
        setGreenStyleButton(this);
    }

    public static void setGreenStyleButton(Button button) {
        button.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        button.getStyle().set("color", "white");
    }
}
