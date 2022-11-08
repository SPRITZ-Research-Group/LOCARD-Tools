package com.a.a.a;

import com.a.a.a.b.c;
import com.a.a.a.b.e;
import com.a.a.a.e.b;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.ref.SoftReference;

public final class a implements Serializable {
    protected static final int a = a.a();
    protected static final int b = com.a.a.a.e.a.a();
    protected static final int c = com.a.a.a.c.a.a();
    protected static final ThreadLocal<SoftReference<com.a.a.a.e.a>> d = new ThreadLocal();
    private static final j n = b.a;
    protected final transient com.a.a.a.d.b e;
    protected final transient com.a.a.a.d.a f;
    protected h g;
    protected int h;
    protected int i;
    protected int j;
    protected com.a.a.a.b.b k;
    protected e l;
    protected j m;

    public enum a {
        INTERN_FIELD_NAMES,
        CANONICALIZE_FIELD_NAMES;
        
        private final boolean c;

        public static int a() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.c) {
                    i |= 1 << aVar.ordinal();
                }
            }
            return i;
        }
    }

    public a() {
        this((byte) 0);
    }

    private a(byte b) {
        this.e = com.a.a.a.d.b.a();
        this.f = com.a.a.a.d.a.a();
        this.h = a;
        this.i = b;
        this.j = c;
        this.m = n;
        this.g = null;
    }

    public final c a(Writer writer) throws IOException {
        SoftReference softReference = (SoftReference) d.get();
        com.a.a.a.e.a aVar = softReference == null ? null : (com.a.a.a.e.a) softReference.get();
        if (aVar == null) {
            aVar = new com.a.a.a.e.a();
            d.set(new SoftReference(aVar));
        }
        c cVar = new c(aVar, writer);
        if (this.l != null) {
            writer = this.l.a();
        }
        c cVar2 = new com.a.a.a.c.c(cVar, this.j, this.g, writer);
        if (this.k != null) {
            cVar2.a(this.k);
        }
        j jVar = this.m;
        if (jVar != n) {
            cVar2.a(jVar);
        }
        return cVar2;
    }
}
