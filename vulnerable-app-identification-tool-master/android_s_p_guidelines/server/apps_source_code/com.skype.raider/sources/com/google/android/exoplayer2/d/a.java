package com.google.android.exoplayer2.d;

public final class a {
    public static void a(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static int a(int index, int limit) {
        if (index >= 0 && index < limit) {
            return index;
        }
        throw new IndexOutOfBoundsException();
    }

    public static void b(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void b(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static <T> T a(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }
}
