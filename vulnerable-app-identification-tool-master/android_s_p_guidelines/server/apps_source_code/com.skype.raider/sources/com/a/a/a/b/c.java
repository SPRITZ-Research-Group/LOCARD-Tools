package com.a.a.a.b;

import com.a.a.a.e.a;
import com.a.a.a.e.a.b;

public final class c {
    protected final Object a;
    protected final boolean b;
    protected final a c;
    protected byte[] d = null;
    protected byte[] e = null;
    protected byte[] f = null;
    protected char[] g = null;
    protected char[] h = null;
    protected char[] i = null;

    public c(a aVar, Object obj) {
        this.c = aVar;
        this.a = obj;
        this.b = false;
    }

    public final boolean a() {
        return this.b;
    }

    public final char[] b() {
        if (this.h != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
        char[] a = this.c.a(b.CONCAT_BUFFER, 0);
        this.h = a;
        return a;
    }

    public final void a(char[] cArr) {
        if (cArr == null) {
            return;
        }
        if (cArr != this.h) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this.h = null;
        this.c.a(b.CONCAT_BUFFER, cArr);
    }
}
