package com.facebook.imageformat;

import com.facebook.common.internal.h;
import com.facebook.common.internal.l;
import com.facebook.imageformat.c.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nullable;

public final class d {
    private static d a;
    private int b;
    @Nullable
    private List<a> c;
    private final a d = new a();

    private d() {
        b();
    }

    public final void a(@Nullable List<a> customImageFormatCheckers) {
        this.c = customImageFormatCheckers;
        b();
    }

    private void b() {
        this.b = this.d.a();
        if (this.c != null) {
            for (a checker : this.c) {
                this.b = Math.max(this.b, checker.a());
            }
        }
    }

    private static int a(int maxHeaderLength, InputStream is, byte[] imageHeaderBytes) throws IOException {
        h.a((Object) is);
        h.a((Object) imageHeaderBytes);
        h.a(imageHeaderBytes.length >= maxHeaderLength);
        if (!is.markSupported()) {
            return com.facebook.common.internal.a.a(is, imageHeaderBytes, maxHeaderLength);
        }
        try {
            is.mark(maxHeaderLength);
            int a = com.facebook.common.internal.a.a(is, imageHeaderBytes, maxHeaderLength);
            return a;
        } finally {
            is.reset();
        }
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    public static c a(InputStream is) {
        try {
            d a = a();
            h.a((Object) is);
            byte[] bArr = new byte[a.b];
            int a2 = a(a.b, is, bArr);
            c a3 = a.d.a(bArr, a2);
            if (a3 != null && a3 != c.a) {
                return a3;
            }
            if (a.c != null) {
                for (a a4 : a.c) {
                    a3 = a4.a(bArr, a2);
                    if (a3 != null && a3 != c.a) {
                        return a3;
                    }
                }
            }
            return c.a;
        } catch (Throwable e) {
            throw l.b(e);
        }
    }
}
