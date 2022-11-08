package com.facebook.crypto.d;

import java.io.IOException;

public final class a {
    public static void a(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public static void b(boolean expression, String errorMessage) throws IOException {
        if (!expression) {
            throw new IOException(errorMessage);
        }
    }
}
