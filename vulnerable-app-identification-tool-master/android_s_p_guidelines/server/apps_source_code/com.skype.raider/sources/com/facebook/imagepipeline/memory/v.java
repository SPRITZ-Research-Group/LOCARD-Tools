package com.facebook.imagepipeline.memory;

import com.facebook.common.e.h;
import com.facebook.common.e.i;
import com.facebook.common.e.k;
import com.facebook.common.e.l;
import com.facebook.common.internal.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class v implements i {
    private final l a;
    private final s b;

    public final /* synthetic */ h a(InputStream inputStream) throws IOException {
        return b(inputStream);
    }

    public final /* synthetic */ h a(InputStream inputStream, int i) throws IOException {
        return b(inputStream, i);
    }

    public final /* synthetic */ h a(byte[] bArr) {
        return b(bArr);
    }

    public v(s pool, l pooledByteStreams) {
        this.b = pool;
        this.a = pooledByteStreams;
    }

    private u b(InputStream inputStream) throws IOException {
        w outputStream = new w(this.b);
        try {
            u a = a(inputStream, outputStream);
            return a;
        } finally {
            outputStream.close();
        }
    }

    private u b(byte[] bytes) {
        w outputStream = new w(this.b, bytes.length);
        try {
            outputStream.write(bytes, 0, bytes.length);
            u c = outputStream.c();
            outputStream.close();
            return c;
        } catch (Throwable e) {
            throw com.facebook.common.internal.l.b(e);
        } catch (Throwable th) {
            outputStream.close();
        }
    }

    private u b(InputStream inputStream, int initialCapacity) throws IOException {
        w outputStream = new w(this.b, initialCapacity);
        try {
            u a = a(inputStream, outputStream);
            return a;
        } finally {
            outputStream.close();
        }
    }

    @VisibleForTesting
    private u a(InputStream inputStream, w outputStream) throws IOException {
        this.a.a(inputStream, outputStream);
        return outputStream.c();
    }

    public final /* synthetic */ k a(int i) {
        return new w(this.b, i);
    }

    public final /* synthetic */ k a() {
        return new w(this.b);
    }
}
