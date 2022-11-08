package com.a.a.a.a;

import com.a.a.a.c;
import com.a.a.a.c.b;
import com.a.a.a.h;
import java.io.IOException;

public abstract class a extends c {
    protected h b;
    protected int c;
    protected boolean d;
    protected b e = b.f();
    protected boolean f;

    protected abstract void d(String str) throws IOException, com.a.a.a.b;

    protected a(int i, h hVar) {
        this.c = i;
        this.b = hVar;
        this.d = a(com.a.a.a.c.a.WRITE_NUMBERS_AS_STRINGS);
    }

    public final boolean a(com.a.a.a.c.a aVar) {
        return (this.c & aVar.b()) != 0;
    }

    public final b f() {
        return this.e;
    }

    public void close() throws IOException {
        this.f = true;
    }

    protected static void e(String str) throws com.a.a.a.b {
        throw new com.a.a.a.b(str);
    }
}
