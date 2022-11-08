package com.facebook.android.a.a;

import java.security.SecureRandom;

public final class b extends SecureRandom {
    public final synchronized void nextBytes(byte[] bytes) {
        c.a();
        super.nextBytes(bytes);
    }
}
