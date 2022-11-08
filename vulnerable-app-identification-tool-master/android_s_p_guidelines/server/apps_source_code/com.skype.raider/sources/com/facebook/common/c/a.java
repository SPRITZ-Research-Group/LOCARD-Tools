package com.facebook.common.c;

import java.io.File;

public final class a {
    public static void a(File directory, b visitor) {
        visitor.a(directory);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    a(file, visitor);
                } else {
                    visitor.b(file);
                }
            }
        }
        visitor.c(directory);
    }

    public static boolean a(File directory) {
        File[] files = directory.listFiles();
        boolean success = true;
        if (files != null) {
            for (File file : files) {
                success &= b(file);
            }
        }
        return success;
    }

    public static boolean b(File file) {
        if (file.isDirectory()) {
            a(file);
        }
        return file.delete();
    }
}
