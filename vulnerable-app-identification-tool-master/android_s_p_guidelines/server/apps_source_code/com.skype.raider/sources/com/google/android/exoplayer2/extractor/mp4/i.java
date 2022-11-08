package com.google.android.exoplayer2.extractor.mp4;

public final class i {
    public final boolean a;
    public final int b;
    public final byte[] c;

    public i(boolean isEncrypted, int initializationVectorSize, byte[] keyId) {
        this.a = isEncrypted;
        this.b = initializationVectorSize;
        this.c = keyId;
    }
}
