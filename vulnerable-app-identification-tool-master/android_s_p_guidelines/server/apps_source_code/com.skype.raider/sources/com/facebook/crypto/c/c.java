package com.facebook.crypto.c;

import com.facebook.crypto.cipher.NativeGCMCipher;
import com.skype.Defines;
import java.io.IOException;
import java.io.OutputStream;

public final class c extends OutputStream {
    private final OutputStream a;
    private final NativeGCMCipher b;
    private final int c;
    private final byte[] d;
    private final byte[] e;
    private boolean f = false;

    public c(OutputStream cipherDelegate, NativeGCMCipher cipher, int tagLength) {
        this.a = cipherDelegate;
        this.b = cipher;
        this.e = new byte[tagLength];
        int cipherBlockSize = this.b.b();
        byte[] encryptBuffer = new byte[(cipherBlockSize + Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE)];
        this.c = encryptBuffer.length - cipherBlockSize;
        this.d = encryptBuffer;
    }

    public final void flush() throws IOException {
        this.a.flush();
    }

    public final void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    public final void write(byte[] buffer, int offset, int count) throws IOException {
        if (buffer.length < offset + count) {
            throw new ArrayIndexOutOfBoundsException(offset + count);
        }
        int times = count / this.c;
        int remainder = count % this.c;
        for (int i = 0; i < times; i++) {
            this.a.write(this.d, 0, this.b.a(buffer, offset, this.c, this.d, 0));
            offset += this.c;
        }
        if (remainder > 0) {
            this.a.write(this.d, 0, this.b.a(buffer, offset, remainder, this.d, 0));
        }
    }

    public final void write(int oneByte) throws IOException {
        write(new byte[]{(byte) oneByte}, 0, 1);
    }

    public final void close() throws IOException {
        try {
            if (!this.f) {
                this.f = true;
                this.b.b(this.e, this.e.length);
                this.a.write(this.e);
                this.b.a();
            }
            this.a.close();
        } catch (Throwable th) {
            this.a.close();
        }
    }
}
