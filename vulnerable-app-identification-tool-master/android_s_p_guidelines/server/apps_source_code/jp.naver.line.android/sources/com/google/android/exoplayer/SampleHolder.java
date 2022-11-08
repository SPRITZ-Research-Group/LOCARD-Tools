package com.google.android.exoplayer;

import java.nio.ByteBuffer;

public final class SampleHolder {
    public static final int BUFFER_REPLACEMENT_MODE_DIRECT = 2;
    public static final int BUFFER_REPLACEMENT_MODE_DISABLED = 0;
    public static final int BUFFER_REPLACEMENT_MODE_NORMAL = 1;
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo = new CryptoInfo();
    public ByteBuffer data;
    public int flags;
    public int size;
    public long timeUs;

    public SampleHolder(int i) {
        this.bufferReplacementMode = i;
    }

    public final boolean replaceBuffer(int i) {
        switch (this.bufferReplacementMode) {
            case 1:
                this.data = ByteBuffer.allocate(i);
                return true;
            case 2:
                this.data = ByteBuffer.allocateDirect(i);
                return true;
            default:
                return false;
        }
    }

    public final boolean isEncrypted() {
        return (this.flags & 2) != 0;
    }

    public final boolean isDecodeOnly() {
        return (this.flags & C.SAMPLE_FLAG_DECODE_ONLY) != 0;
    }

    public final boolean isSyncFrame() {
        return (this.flags & 1) != 0;
    }

    public final void clearData() {
        if (this.data != null) {
            this.data.clear();
        }
    }
}
