package com.microsoft.dl;

public final class BuildInfo {
    public static final Flavour FLAVOUR = a();

    public enum Flavour {
        DEBUG,
        INTERNAL,
        RELEASE,
        UNKNOWN
    }

    private static native int getFlavourNative();

    private BuildInfo() {
    }

    private static Flavour a() {
        try {
            return Flavour.values()[getFlavourNative()];
        } catch (UnsatisfiedLinkError e) {
            return Flavour.UNKNOWN;
        }
    }
}
