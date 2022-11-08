package com.appboy.b;

import com.appboy.e.e;

public enum h implements e<String> {
    UNITY,
    REACT,
    CORDOVA,
    XAMARIN,
    SEGMENT,
    MPARTICLE;

    public final String a() {
        switch (this) {
            case UNITY:
                return "unity";
            case REACT:
                return "react";
            case CORDOVA:
                return "cordova";
            case XAMARIN:
                return "xamarin";
            case SEGMENT:
                return "segment";
            case MPARTICLE:
                return "mparticle";
            default:
                return null;
        }
    }
}
