package com.facebook.imageformat;

import javax.annotation.Nullable;

public final class c {
    public static final c a = new c("UNKNOWN", null);
    private final String b;
    private final String c;

    public interface a {
        int a();

        @Nullable
        c a(byte[] bArr, int i);
    }

    public c(String name, @Nullable String fileExtension) {
        this.c = name;
        this.b = fileExtension;
    }

    public final String a() {
        return this.c;
    }

    public final String toString() {
        return this.c;
    }
}
