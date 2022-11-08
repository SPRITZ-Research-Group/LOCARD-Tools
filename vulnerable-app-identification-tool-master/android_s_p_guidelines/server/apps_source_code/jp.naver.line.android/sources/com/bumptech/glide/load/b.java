package com.bumptech.glide.load;

public enum b {
    PREFER_ARGB_8888,
    PREFER_RGB_565;
    
    public static final b DEFAULT = null;

    static {
        DEFAULT = PREFER_ARGB_8888;
    }
}
