package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import java.io.EOFException;
import java.io.IOException;

public final class e implements n {
    public final void a(Format format) {
    }

    public final int a(g input, int length, boolean allowEndOfInput) throws IOException, InterruptedException {
        int bytesSkipped = input.a(length);
        if (bytesSkipped != -1) {
            return bytesSkipped;
        }
        if (allowEndOfInput) {
            return -1;
        }
        throw new EOFException();
    }

    public final void a(k data, int length) {
        data.d(length);
    }

    public final void a(long timeUs, int flags, int size, int offset, byte[] encryptionKey) {
    }
}
