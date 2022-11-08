package com.google.android.exoplayer2.decoder;

import com.google.android.exoplayer2.d.a;
import java.util.LinkedList;

public abstract class f<I extends DecoderInputBuffer, O extends e, E extends Exception> implements c<I, O, E> {
    private final Thread a;
    private final Object b = new Object();
    private final LinkedList<I> c = new LinkedList();
    private final LinkedList<O> d = new LinkedList();
    private final I[] e;
    private final O[] f;
    private int g;
    private int h;
    private I i;
    private E j;
    private boolean k;
    private boolean l;
    private int m;

    protected abstract E a(I i, O o, boolean z);

    protected abstract I f();

    protected abstract O g();

    public final /* synthetic */ Object a() throws Exception {
        return h();
    }

    public final /* synthetic */ void a(Object obj) throws Exception {
        DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) obj;
        synchronized (this.b) {
            j();
            a.a(decoderInputBuffer == this.i);
            this.c.addLast(decoderInputBuffer);
            k();
            this.i = null;
        }
    }

    public final /* synthetic */ Object b() throws Exception {
        return i();
    }

    protected f(I[] inputBuffers, O[] outputBuffers) {
        int i;
        this.e = inputBuffers;
        this.g = 2;
        for (i = 0; i < this.g; i++) {
            this.e[i] = f();
        }
        this.f = outputBuffers;
        this.h = 2;
        for (i = 0; i < this.h; i++) {
            this.f[i] = g();
        }
        this.a = new Thread(this) {
            final /* synthetic */ f a;

            {
                this.a = this$0;
            }

            public final void run() {
                f.a(this.a);
            }
        };
        this.a.start();
    }

    protected final void e() {
        boolean z;
        int i = 0;
        if (this.g == this.e.length) {
            z = true;
        } else {
            z = false;
        }
        a.b(z);
        DecoderInputBuffer[] decoderInputBufferArr = this.e;
        int length = decoderInputBufferArr.length;
        while (i < length) {
            decoderInputBufferArr[i].d(1024);
            i++;
        }
    }

    private I h() throws Exception {
        I i;
        synchronized (this.b) {
            DecoderInputBuffer decoderInputBuffer;
            j();
            a.b(this.i == null);
            if (this.g == 0) {
                decoderInputBuffer = null;
            } else {
                DecoderInputBuffer[] decoderInputBufferArr = this.e;
                int i2 = this.g - 1;
                this.g = i2;
                decoderInputBuffer = decoderInputBufferArr[i2];
            }
            this.i = decoderInputBuffer;
            i = this.i;
        }
        return i;
    }

    private O i() throws Exception {
        O o;
        synchronized (this.b) {
            j();
            if (this.d.isEmpty()) {
                o = null;
            } else {
                e o2 = (e) this.d.removeFirst();
            }
        }
        return o2;
    }

    protected void a(O outputBuffer) {
        synchronized (this.b) {
            b(outputBuffer);
            k();
        }
    }

    public final void c() {
        synchronized (this.b) {
            this.k = true;
            this.m = 0;
            if (this.i != null) {
                a(this.i);
                this.i = null;
            }
            while (!this.c.isEmpty()) {
                a((DecoderInputBuffer) this.c.removeFirst());
            }
            while (!this.d.isEmpty()) {
                b((e) this.d.removeFirst());
            }
        }
    }

    public final void d() {
        synchronized (this.b) {
            this.l = true;
            this.b.notify();
        }
        try {
            this.a.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void j() throws Exception {
        if (this.j != null) {
            throw this.j;
        }
    }

    private void k() {
        if (m()) {
            this.b.notify();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean l() throws InterruptedException {
        synchronized (this.b) {
            while (!this.l && !m()) {
                this.b.wait();
            }
            if (this.l) {
                return false;
            }
            DecoderInputBuffer inputBuffer = (DecoderInputBuffer) this.c.removeFirst();
            e[] eVarArr = this.f;
            int i = this.h - 1;
            this.h = i;
            O outputBuffer = eVarArr[i];
            boolean resetDecoder = this.k;
            this.k = false;
        }
    }

    private boolean m() {
        return !this.c.isEmpty() && this.h > 0;
    }

    private void a(I inputBuffer) {
        inputBuffer.a();
        DecoderInputBuffer[] decoderInputBufferArr = this.e;
        int i = this.g;
        this.g = i + 1;
        decoderInputBufferArr[i] = inputBuffer;
    }

    private void b(O outputBuffer) {
        outputBuffer.a();
        e[] eVarArr = this.f;
        int i = this.h;
        this.h = i + 1;
        eVarArr[i] = outputBuffer;
    }

    static /* synthetic */ void a(f x0) {
        do {
            try {
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        } while (x0.l());
    }
}
