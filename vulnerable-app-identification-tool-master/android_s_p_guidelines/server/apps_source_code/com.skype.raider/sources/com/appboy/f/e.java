package com.appboy.f;

import java.util.Set;

public class e {
    private static final String a = c.a(e.class);

    public static boolean a(String key, Set<String> blacklistedAttributes) {
        if (key == null) {
            c.f(a, "Custom attribute key cannot be null.");
            return false;
        } else if (!blacklistedAttributes.contains(key)) {
            return true;
        } else {
            c.f(a, "Custom attribute key cannot be blacklisted attribute: " + key + ".");
            return false;
        }
    }

    public static boolean a(String value) {
        if (value != null) {
            return true;
        }
        c.f(a, "Custom attribute value cannot be null.");
        return false;
    }

    public static String[] a(String[] values) {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                values[i] = j.c(values[i]);
            }
        }
        return values;
    }
}
