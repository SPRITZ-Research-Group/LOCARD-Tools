package com.facebook.crypto;

import com.facebook.crypto.a.b;

final class a implements com.facebook.crypto.b.a {
    private final com.facebook.crypto.b.a a;
    private final f b;

    public a(com.facebook.crypto.b.a mDelegate, f mConfig) {
        this.a = mDelegate;
        this.b = mConfig;
    }

    public final byte[] a() throws b {
        byte[] result = this.a.a();
        a(result, this.b.d, "Key");
        return result;
    }

    public final byte[] b() throws b {
        byte[] result = this.a.b();
        a(result, this.b.e, "IV");
        return result;
    }

    private static void a(byte[] key, int lengthInBytes, String name) {
        if (key.length != lengthInBytes) {
            throw new IllegalStateException(name + " should be " + lengthInBytes + " bytes long but is " + key.length);
        }
    }
}
