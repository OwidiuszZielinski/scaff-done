package com.dev.scaffdone.core.scaffolding.model;

import lombok.Getter;

@Getter
public enum Colors {
    GREEN_COLOR("#4e8752");
    private final String hexCode;
    Colors(String hexCode) {
        this.hexCode = hexCode;
    }
    public String toString() {
        return hexCode;
    }
}
