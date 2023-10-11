package com.dev.scaffdone.core.scaffolding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaffoldingModule {

    private float dimension;
    private int quantity;

    @Override
    public String toString() {
        return dimension + " x "
                + quantity;
    }
}


