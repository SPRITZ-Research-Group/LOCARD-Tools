package com.google.android.gms.internal.measurement;

final class gf extends ga<Integer> {
    gf(gk gkVar, String str, Integer num) {
        super(gkVar, str, num, (byte) 0);
    }

    private final Integer c(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            String str2 = this.a;
            new StringBuilder((String.valueOf(str2).length() + 28) + String.valueOf(str).length()).append("Invalid integer value for ").append(str2).append(": ").append(str);
            return null;
        }
    }

    protected final /* synthetic */ Object a(String str) {
        return c(str);
    }
}
