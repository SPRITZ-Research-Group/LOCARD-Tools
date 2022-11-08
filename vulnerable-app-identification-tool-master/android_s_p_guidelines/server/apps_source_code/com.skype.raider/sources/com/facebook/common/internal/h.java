package com.facebook.common.internal;

import javax.annotation.Nullable;

public final class h {
    public static void a(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void a(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(a(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void b(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void b(boolean expression, @Nullable Object errorMessage) {
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

    public static <T> T a(T reference, @Nullable Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static int a(int index, int size) {
        String str = "index";
        if (index >= 0 && index < size) {
            return index;
        }
        if (index < 0) {
            str = a("%s (%s) must not be negative", str, Integer.valueOf(index));
        } else if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        } else {
            str = a("%s (%s) must be less than size (%s)", str, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IndexOutOfBoundsException(str);
    }

    private static String a(@Nullable String template, @Nullable Object... args) {
        int i;
        template = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template.length() + (args.length * 16));
        int templateStart = 0;
        int i2 = 0;
        while (i2 < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            i = i2 + 1;
            builder.append(args[i2]);
            templateStart = placeholderStart + 2;
            i2 = i;
        }
        builder.append(template.substring(templateStart));
        if (i2 < args.length) {
            builder.append(" [");
            i = i2 + 1;
            builder.append(args[i2]);
            while (true) {
                i2 = i;
                if (i2 >= args.length) {
                    break;
                }
                builder.append(", ");
                i = i2 + 1;
                builder.append(args[i2]);
            }
            builder.append(']');
        }
        return builder.toString();
    }
}
