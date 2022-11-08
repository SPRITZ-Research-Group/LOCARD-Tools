package com.facebook.react.bridge;

public final class an {
    public static void a(String message) {
        throw new c(message);
    }

    public static void a(boolean condition, String message) {
        if (!condition) {
            throw new c(message);
        }
    }
}
