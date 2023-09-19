package com.dev.scaffdone.scaffold.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateModule {

    private float size;
    private int quantity;

    @Override
    public String toString() {
        return size + " x "
                + quantity;
    }
}


