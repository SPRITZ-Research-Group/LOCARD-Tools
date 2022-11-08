package com.facebook.crypto;

import com.adjust.sdk.Constants;
import java.nio.charset.Charset;

public final class g {
    private static final Charset a = Charset.forName("UTF-16");
    private static final Charset b = Charset.forName(Constants.ENCODING);
    private byte[] c;

    private g(byte[] bytes) {
        this.c = bytes;
    }

    public final byte[] a() {
        return this.c;
    }

    public static g a(String name) {
        return new g(name.getBytes(b));
    }
}
