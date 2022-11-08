package com.a.a.a.e;

import com.a.a.a.b.f;
import com.a.a.a.c;
import com.a.a.a.i;
import com.a.a.a.j;
import java.io.IOException;
import java.io.Serializable;

public final class b implements i, Serializable {
    public static final f a = new f(" ");
    protected a b;
    protected a c;
    protected final j d;
    protected boolean e;
    protected transient int f;

    public interface a {
        boolean a();
    }

    public final void a(c cVar) throws IOException, com.a.a.a.b {
        if (this.d != null) {
            cVar.b(this.d);
        }
    }

    public final void b(c cVar) throws IOException, com.a.a.a.b {
        cVar.a('{');
        if (!this.c.a()) {
            this.f++;
        }
    }

    public final void d(c cVar) throws IOException, com.a.a.a.b {
        if (this.e) {
            cVar.c(" : ");
        } else {
            cVar.a(':');
        }
    }

    public final void c(c cVar) throws IOException, com.a.a.a.b {
        cVar.a(',');
    }

    public final void a(c cVar, int i) throws IOException, com.a.a.a.b {
        if (!this.c.a()) {
            this.f--;
        }
        if (i <= 0) {
            cVar.a(' ');
        }
        cVar.a('}');
    }

    public final void e(c cVar) throws IOException, com.a.a.a.b {
        if (!this.b.a()) {
            this.f++;
        }
        cVar.a('[');
    }

    public final void f(c cVar) throws IOException, com.a.a.a.b {
        cVar.a(',');
    }

    public final void b(c cVar, int i) throws IOException, com.a.a.a.b {
        if (!this.b.a()) {
            this.f--;
        }
        if (i <= 0) {
            cVar.a(' ');
        }
        cVar.a(']');
    }
}
