package com.google.android.exoplayer.extractor.mp4;

public final class TrackEncryptionBox {
    public final int initializationVectorSize;
    public final boolean isEncrypted;
    public final byte[] keyId;

    public TrackEncryptionBox(boolean z, int i, byte[] bArr) {
        this.isEncrypted = z;
        this.initializationVectorSize = i;
        this.keyId = bArr;
    }
}
