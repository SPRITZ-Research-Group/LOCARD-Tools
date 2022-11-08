package com.facebook.react.modules.network;

public final class c {
    public static String a(String value) {
        StringBuilder builder = new StringBuilder(value.length());
        boolean modified = false;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            if ((c <= 31 || c >= 127) && c != 9) {
                modified = true;
            } else {
                builder.append(c);
            }
        }
        return modified ? builder.toString() : value;
    }
}
