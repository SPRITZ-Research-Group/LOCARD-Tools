package com.facebook.common.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class b {
    @VisibleForTesting
    static final Logger a = Logger.getLogger(b.class.getName());

    private b() {
    }

    public static void a(@Nullable Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                a.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
            }
        }
    }

    public static void a(@Nullable InputStream inputStream) {
        try {
            a((Closeable) inputStream);
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
    }
}
