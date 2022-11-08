package com.google.android.gms.internal.clearcut;

final /* synthetic */ class ae {
    static final /* synthetic */ int[] a = new int[af.values().length];
    static final /* synthetic */ int[] b = new int[ap.values().length];

    static {
        try {
            b[ap.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[ap.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[ap.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[af.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[af.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[af.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
    }
}
