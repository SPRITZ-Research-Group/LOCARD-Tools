package com.google.gson.a;

public final class a {
    public static <T> T a(T obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }

    public static void a(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }
}
