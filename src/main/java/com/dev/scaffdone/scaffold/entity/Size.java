package com.dev.scaffdone.scaffold.entity;


import java.util.Arrays;
import java.util.List;

public enum Size {


    SIZE_073(0.73f), SIZE_109(1.09f), SIZE_157(1.57f), SIZE_207(2.07f), SIZE_257(2.57f), SIZE_307(3.07f), ONE_FRAME_005(0.05f), TWO_FRAMES_010(0.10f);

    private final float size;
    Size(float size) {
        this.size = size;
    }
    public float getSize() {
        return size;
    }
    public static List<Size> getSizes(){
        return Arrays.asList(Size.values());
    }

}