package com.a.a.a.c;

import com.a.a.a.b.b;
import com.a.a.a.b.c;
import com.a.a.a.h;
import com.a.a.a.j;
import java.io.IOException;

public abstract class a extends com.a.a.a.a.a {
    protected static final int[] g = com.a.a.a.b.a.a();
    protected final c h;
    protected int[] i = g;
    protected int j;
    protected b k;
    protected j l = com.a.a.a.e.b.a;

    public a(c cVar, int i, h hVar) {
        super(i, hVar);
        this.h = cVar;
        if (a(com.a.a.a.c.a.ESCAPE_NON_ASCII)) {
            this.j = 127;
        }
    }

    public final com.a.a.a.c a(b bVar) {
        this.k = bVar;
        if (bVar == null) {
            this.i = g;
        } else {
            this.i = bVar.a();
        }
        return this;
    }

    public final com.a.a.a.c a(j jVar) {
        this.l = jVar;
        return this;
    }

    public final void a(String str, String str2) throws IOException, com.a.a.a.b {
        a(str);
        b(str2);
    }
}
