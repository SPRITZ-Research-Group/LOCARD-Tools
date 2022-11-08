package com.bumptech.glide;

public enum l {
    LOW(0.5f),
    NORMAL(1.0f),
    HIGH(1.5f);
    
    private final float multiplier;

    private l(float f) {
        this.multiplier = f;
    }
}
