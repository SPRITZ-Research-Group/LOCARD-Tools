package com.google.android.gms.internal.measurement;

final class ge extends ga<Long> {
    ge(gk gkVar, String str, Long l) {
        super(gkVar, str, l, (byte) 0);
    }

    private final Long c(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            String str2 = this.a;
            new StringBuilder((String.valueOf(str2).length() + 25) + String.valueOf(str).length()).append("Invalid long value for ").append(str2).append(": ").append(str);
            return null;
        }
    }

    protected final /* synthetic */ Object a(String str) {
        return c(str);
    }
}
