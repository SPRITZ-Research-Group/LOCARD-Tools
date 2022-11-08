package com.facebook.crypto.c;

import com.facebook.crypto.cipher.NativeGCMCipher;
import com.skype.Defines;
import java.io.IOException;
import java.io.InputStream;

public final class b extends InputStream {
    private final d a;
    private final NativeGCMCipher b;
    private byte[] c;
    private boolean d = false;

    public b(InputStream cipherDelegate, NativeGCMCipher cipher, int tagLength) {
        this.a = new d(cipherDelegate, tagLength);
        this.b = cipher;
    }

    public final int available() throws IOException {
        return this.a.available();
    }

    public final void close() throws IOException {
        try {
            a();
        } finally {
            this.a.close();
        }
    }

    public final void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

    public final boolean markSupported() {
        return false;
    }

    public final int read() throws IOException {
        throw new UnsupportedOperationException();
    }

    public final int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public final int read(byte[] buffer, int offset, int length) throws IOException {
        if (buffer.length < offset + length) {
            throw new ArrayIndexOutOfBoundsException(offset + length);
        }
        int read = this.a.read(buffer, offset, length);
        if (read != -1) {
            return this.b.a(buffer, offset, read, buffer, offset);
        }
        a();
        return -1;
    }

    private void a() throws IOException {
        if (!this.d) {
            this.d = true;
            try {
                byte[] tail = this.a.a();
                this.b.c(tail, tail.length);
            } finally {
                this.b.a();
            }
        }
    }

    public final synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    public final long skip(long byteCount) throws IOException {
        if (this.c == null) {
            this.c = new byte[Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE];
        }
        long skipped = 0;
        while (byteCount > 0) {
            int read = read(this.c, 0, (int) Math.min(byteCount, 256));
            if (read < 0) {
                break;
            }
            skipped += (long) read;
            byteCount -= (long) read;
        }
        return skipped == 0 ? -1 : skipped;
    }
}
