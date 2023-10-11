package com.dev.scaffdone.core.scaffold.model;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Dimension {


    SIZE_073(0.73f), SIZE_109(1.09f), SIZE_157(1.57f),
    SIZE_207(2.07f), SIZE_257(2.57f), SIZE_307(3.07f),
    SIZE_070(0.70f), SIZE_100(1.00f), SIZE_150(1.50f),
    SIZE_200(2.00f), SIZE_250(2.50f), SIZE_300(3.00f),
    SIZE_414(4.14f),SIZE_400(4.00f),
    ONE_FRAME_005(0.05f), TWO_FRAMES_010(0.10f);

    private final float size;

    Dimension(float size) {
        this.size = size;
    }

    public static List<Dimension> getSizes() {
        return Arrays.asList(Dimension.values());
    }

}