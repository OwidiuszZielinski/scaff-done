package com.dev.scaffdone.scaffold.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScaffoldModule {

    private float dimension;
    private int quantity;

    @Override
    public String toString() {
        return dimension + " x "
                + quantity;
    }
}


