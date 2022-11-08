package com.facebook.imagepipeline.memory;

import com.facebook.common.e.h;
import com.facebook.common.e.k;
import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class w extends k {
    private final s a;
    private com.facebook.common.f.a<r> b;
    private int c;

    public static class a extends RuntimeException {
        public a() {
            super("OutputStream no longer valid");
        }
    }

    public final /* synthetic */ h a() {
        return c();
    }

    public w(s pool) {
        this(pool, pool.b());
    }

    public w(s pool, int initialCapacity) {
        boolean z;
        if (initialCapacity > 0) {
            z = true;
        } else {
            z = false;
        }
        com.facebook.common.internal.h.a(z);
        this.a = (s) com.facebook.common.internal.h.a((Object) pool);
        this.c = 0;
        this.b = com.facebook.common.f.a.a(this.a.a(initialCapacity), this.a);
    }

    public final u c() {
        d();
        return new u(this.b, this.c);
    }

    public final int b() {
        return this.c;
    }

    public final void write(int oneByte) throws IOException {
        write(new byte[]{(byte) oneByte});
    }

    public final void write(byte[] buffer, int offset, int count) throws IOException {
        if (offset < 0 || count < 0 || offset + count > buffer.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length + "; regionStart=" + offset + "; regionLength=" + count);
        }
        d();
        int i = this.c + count;
        d();
        if (i > ((r) this.b.a()).getSize()) {
            r rVar = (r) this.a.a(i);
            ((r) this.b.a()).copy(0, rVar, 0, this.c);
            this.b.close();
            this.b = com.facebook.common.f.a.a(rVar, this.a);
        }
        ((r) this.b.a()).write(this.c, buffer, offset, count);
        this.c += count;
    }

    public final void close() {
        com.facebook.common.f.a.c(this.b);
        this.b = null;
        this.c = -1;
        super.close();
    }

    private void d() {
        if (!com.facebook.common.f.a.a(this.b)) {
            throw new a();
        }
    }
}
