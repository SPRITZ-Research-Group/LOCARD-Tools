package com.google.android.gms.internal.measurement;

final class gg extends ga<Boolean> {
    gg(gk gkVar, String str, Boolean bool) {
        super(gkVar, str, bool, (byte) 0);
    }

    protected final /* synthetic */ Object a(String str) {
        if (fw.a.matcher(str).matches()) {
            return Boolean.valueOf(true);
        }
        if (fw.b.matcher(str).matches()) {
            return Boolean.valueOf(false);
        }
        String str2 = this.a;
        new StringBuilder((String.valueOf(str2).length() + 28) + String.valueOf(str).length()).append("Invalid boolean value for ").append(str2).append(": ").append(str);
        return null;
    }
}
