package com.facebook.common.c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class c {

    public static class a extends IOException {
        public a(String message) {
            super(message);
        }

        public a(String message, Throwable innerException) {
            super(message);
            initCause(innerException);
        }
    }

    public static class b extends IOException {
        public b(String message) {
            super(message);
        }
    }

    public static class c extends FileNotFoundException {
        public c(String message) {
            super(message);
        }
    }

    public static class d extends IOException {
        public d(String message, Throwable innerException) {
            super(message);
            initCause(innerException);
        }
    }

    public static void a(File directory) throws a {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                if (!directory.delete()) {
                    throw new a(directory.getAbsolutePath(), new b(directory.getAbsolutePath()));
                }
            }
            return;
        }
        if (!directory.mkdirs() && !directory.isDirectory()) {
            throw new a(directory.getAbsolutePath());
        }
    }
}
