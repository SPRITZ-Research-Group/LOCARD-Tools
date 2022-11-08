package com.facebook.internal;

import java.io.File;
import java.io.FilenameFilter;

final class w {
    private static final FilenameFilter a = new FilenameFilter() {
        public final boolean accept(File file, String str) {
            return !str.startsWith("buffer");
        }
    };
    private static final FilenameFilter b = new FilenameFilter() {
        public final boolean accept(File file, String str) {
            return str.startsWith("buffer");
        }
    };

    static FilenameFilter a() {
        return a;
    }

    static File b(File file) {
        StringBuilder stringBuilder = new StringBuilder("buffer");
        stringBuilder.append(Long.valueOf(v.b.incrementAndGet()).toString());
        return new File(file, stringBuilder.toString());
    }

    static void a(File file) {
        File[] listFiles = file.listFiles(b);
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }
}
