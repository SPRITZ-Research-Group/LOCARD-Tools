package com.facebook.ads.internal.a;

public enum a {
    CANNOT_OPEN,
    CANNOT_TRACK;

    public static boolean a(a aVar) {
        return CANNOT_OPEN.equals(aVar) || CANNOT_TRACK.equals(aVar);
    }
}
