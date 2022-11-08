package com.facebook.ads;

public enum l {
    DEFAULT,
    ON,
    OFF;

    public static l a(com.facebook.ads.internal.n.l lVar) {
        if (lVar == null) {
            return DEFAULT;
        }
        switch (lVar) {
            case DEFAULT:
                return DEFAULT;
            case ON:
                return ON;
            case OFF:
                return OFF;
            default:
                return DEFAULT;
        }
    }
}
