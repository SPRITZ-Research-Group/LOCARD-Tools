package com.google.android.exoplayer2.text.a;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.g;
import com.google.android.exoplayer2.text.h;
import java.util.LinkedList;
import java.util.TreeSet;

abstract class d implements com.google.android.exoplayer2.text.d {
    private final LinkedList<g> a = new LinkedList();
    private final LinkedList<h> b;
    private final TreeSet<g> c;
    private g d;
    private long e;

    protected abstract void a(g gVar);

    protected abstract boolean e();

    protected abstract c f();

    public final /* synthetic */ Object a() throws Exception {
        return h();
    }

    public final /* synthetic */ void a(Object obj) throws Exception {
        b((g) obj);
    }

    public final /* synthetic */ Object b() throws Exception {
        return g();
    }

    public d() {
        int i;
        for (i = 0; i < 10; i++) {
            this.a.add(new g());
        }
        this.b = new LinkedList();
        for (i = 0; i < 2; i++) {
            this.b.add(new e(this));
        }
        this.c = new TreeSet();
    }

    public void a(long positionUs) {
        this.e = positionUs;
    }

    public g h() throws e {
        a.b(this.d == null);
        if (this.a.isEmpty()) {
            return null;
        }
        this.d = (g) this.a.pollFirst();
        return this.d;
    }

    public void b(g inputBuffer) throws e {
        boolean z = true;
        a.a(inputBuffer != null);
        if (inputBuffer != this.d) {
            z = false;
        }
        a.a(z);
        if (inputBuffer.k_()) {
            c(inputBuffer);
        } else {
            this.c.add(inputBuffer);
        }
        this.d = null;
    }

    public h g() throws e {
        if (this.b.isEmpty()) {
            return null;
        }
        while (!this.c.isEmpty() && ((g) this.c.first()).c <= this.e) {
            g inputBuffer = (g) this.c.pollFirst();
            h outputBuffer;
            if (inputBuffer.c()) {
                outputBuffer = (h) this.b.pollFirst();
                outputBuffer.b(4);
                c(inputBuffer);
                return outputBuffer;
            }
            a(inputBuffer);
            if (e()) {
                c subtitle = f();
                if (!inputBuffer.k_()) {
                    outputBuffer = (h) this.b.pollFirst();
                    outputBuffer.a(inputBuffer.c, subtitle, Long.MAX_VALUE);
                    c(inputBuffer);
                    return outputBuffer;
                }
            }
            c(inputBuffer);
        }
        return null;
    }

    private void c(g inputBuffer) {
        inputBuffer.a();
        this.a.add(inputBuffer);
    }

    protected final void a(h outputBuffer) {
        outputBuffer.a();
        this.b.add(outputBuffer);
    }

    public void c() {
        this.e = 0;
        while (!this.c.isEmpty()) {
            c((g) this.c.pollFirst());
        }
        if (this.d != null) {
            c(this.d);
            this.d = null;
        }
    }

    public void d() {
    }
}
