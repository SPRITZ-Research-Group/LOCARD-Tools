package com.google.android.gms.internal.measurement;

final class gh extends ga<Double> {
    gh(gk gkVar, String str, Double d) {
        super(gkVar, str, d, (byte) 0);
    }

    private final Double c(String str) {
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException e) {
            String str2 = this.a;
            new StringBuilder((String.valueOf(str2).length() + 27) + String.valueOf(str).length()).append("Invalid double value for ").append(str2).append(": ").append(str);
            return null;
        }
    }

    protected final /* synthetic */ Object a(String str) {
        return c(str);
    }
}
