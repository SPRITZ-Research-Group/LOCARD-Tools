package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.c.f;
import com.google.android.exoplayer2.c.h;
import com.google.android.exoplayer2.c.i;
import com.google.android.exoplayer2.d.p;
import com.google.android.exoplayer2.d.r;
import com.google.android.exoplayer2.source.e;
import java.io.IOException;

final class g implements Callback, com.google.android.exoplayer2.c.h.a, com.google.android.exoplayer2.source.d.a, com.google.android.exoplayer2.source.e.a {
    private c A;
    private long B;
    private a C;
    private a D;
    private a E;
    private r F;
    private final m[] a;
    private final n[] b;
    private final h c;
    private final j d;
    private final p e;
    private final Handler f;
    private final HandlerThread g;
    private final Handler h;
    private final d i;
    private final com.google.android.exoplayer2.r.b j;
    private final com.google.android.exoplayer2.r.a k;
    private b l;
    private l m;
    private m n;
    private com.google.android.exoplayer2.d.g o;
    private e p;
    private m[] q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private int v = 1;
    private int w;
    private int x;
    private long y;
    private int z;

    private static final class a {
        public final com.google.android.exoplayer2.source.d a;
        public final Object b;
        public final com.google.android.exoplayer2.source.g[] c;
        public final boolean[] d;
        public final long e;
        public int f;
        public long g;
        public boolean h;
        public boolean i;
        public boolean j;
        public a k;
        public boolean l;
        public i m;
        private final m[] n;
        private final n[] o;
        private final h p;
        private final j q;
        private final e r;
        private i s;

        public a(m[] renderers, n[] rendererCapabilities, long rendererPositionOffsetUs, h trackSelector, j loadControl, e mediaSource, Object periodUid, int periodIndex, boolean isLastPeriod, long startPositionUs) {
            this.n = renderers;
            this.o = rendererCapabilities;
            this.e = rendererPositionOffsetUs;
            this.p = trackSelector;
            this.q = loadControl;
            this.r = mediaSource;
            this.b = com.google.android.exoplayer2.d.a.a(periodUid);
            this.f = periodIndex;
            this.h = isLastPeriod;
            this.g = startPositionUs;
            this.c = new com.google.android.exoplayer2.source.g[renderers.length];
            this.d = new boolean[renderers.length];
            this.a = mediaSource.a(periodIndex, loadControl.d(), startPositionUs);
        }

        public final long a() {
            return this.e - this.g;
        }

        public final void a(int index, boolean isLast) {
            this.f = index;
            this.h = isLast;
        }

        public final boolean b() {
            return this.i && (!this.j || this.a.g() == Long.MIN_VALUE);
        }

        public final boolean c() throws ExoPlaybackException {
            boolean z;
            i selectorResult = this.p.a(this.o, this.a.e());
            i iVar = this.s;
            if (iVar == null) {
                z = false;
            } else {
                for (int i = 0; i < selectorResult.b.a; i++) {
                    if (!selectorResult.a(iVar, i)) {
                        z = false;
                        break;
                    }
                }
                z = true;
            }
            if (z) {
                return false;
            }
            this.m = selectorResult;
            return true;
        }

        public final long a(long positionUs) {
            return a(positionUs, false, new boolean[this.n.length]);
        }

        public final long a(long positionUs, boolean forceRecreateStreams, boolean[] streamResetFlags) {
            boolean z;
            com.google.android.exoplayer2.c.g trackSelections = this.m.b;
            int i = 0;
            while (i < trackSelections.a) {
                boolean[] zArr = this.d;
                if (forceRecreateStreams || !this.m.a(this.s, i)) {
                    z = false;
                } else {
                    z = true;
                }
                zArr[i] = z;
                i++;
            }
            positionUs = this.a.a(trackSelections.a(), this.d, this.c, streamResetFlags, positionUs);
            this.s = this.m;
            this.j = false;
            for (i = 0; i < this.c.length; i++) {
                if (this.c[i] != null) {
                    if (trackSelections.a(i) != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    com.google.android.exoplayer2.d.a.b(z);
                    this.j = true;
                } else {
                    com.google.android.exoplayer2.d.a.b(trackSelections.a(i) == null);
                }
            }
            this.q.a(this.n, trackSelections);
            return positionUs;
        }

        public final void d() {
            try {
                this.r.a(this.a);
            } catch (RuntimeException e) {
            }
        }
    }

    public static final class b {
        public final int a;
        public final long b;
        public volatile long c;
        public volatile long d;

        public b(int periodIndex, long startPositionUs) {
            this.a = periodIndex;
            this.b = startPositionUs;
            this.c = startPositionUs;
            this.d = startPositionUs;
        }
    }

    private static final class c {
        public final r a;
        public final int b;
        public final long c;

        public c(r timeline, int windowIndex, long windowPositionUs) {
            this.a = timeline;
            this.b = windowIndex;
            this.c = windowPositionUs;
        }
    }

    public static final class d {
        public final r a;
        public final Object b;
        public final b c;
        public final int d;

        public d(r timeline, Object manifest, b playbackInfo, int seekAcks) {
            this.a = timeline;
            this.b = manifest;
            this.c = playbackInfo;
            this.d = seekAcks;
        }
    }

    public final /* synthetic */ void a(com.google.android.exoplayer2.source.h hVar) {
        this.f.obtainMessage(9, (com.google.android.exoplayer2.source.d) hVar).sendToTarget();
    }

    public g(m[] renderers, h trackSelector, j loadControl, boolean playWhenReady, Handler eventHandler, b playbackInfo, d player) {
        this.a = renderers;
        this.c = trackSelector;
        this.d = loadControl;
        this.s = playWhenReady;
        this.h = eventHandler;
        this.l = playbackInfo;
        this.i = player;
        this.b = new n[renderers.length];
        for (int i = 0; i < renderers.length; i++) {
            renderers[i].a(i);
            this.b[i] = renderers[i].b();
        }
        this.e = new p();
        this.q = new m[0];
        this.j = new com.google.android.exoplayer2.r.b();
        this.k = new com.google.android.exoplayer2.r.a();
        trackSelector.a((com.google.android.exoplayer2.c.h.a) this);
        this.m = l.a;
        this.g = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.g.start();
        this.f = new Handler(this.g.getLooper(), this);
    }

    public final void a(e mediaSource) {
        this.f.obtainMessage(0, 1, 0, mediaSource).sendToTarget();
    }

    public final void a(boolean playWhenReady) {
        int i;
        Handler handler = this.f;
        if (playWhenReady) {
            i = 1;
        } else {
            i = 0;
        }
        handler.obtainMessage(1, i, 0).sendToTarget();
    }

    public final void a(r timeline, int windowIndex, long positionUs) {
        this.f.obtainMessage(3, new c(timeline, windowIndex, positionUs)).sendToTarget();
    }

    public final void a() {
        this.f.sendEmptyMessage(5);
    }

    public final void a(com.google.android.exoplayer2.d.c... messages) {
        if (!this.r) {
            this.w++;
            this.f.obtainMessage(11, messages).sendToTarget();
        }
    }

    public final synchronized void b(com.google.android.exoplayer2.d.c... messages) {
        if (!this.r) {
            int messageNumber = this.w;
            this.w = messageNumber + 1;
            this.f.obtainMessage(11, messages).sendToTarget();
            while (this.x <= messageNumber) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public final synchronized void b() {
        if (!this.r) {
            this.f.sendEmptyMessage(6);
            while (!this.r) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            this.g.quit();
        }
    }

    public final void a(r timeline, Object manifest) {
        this.f.obtainMessage(7, Pair.create(timeline, manifest)).sendToTarget();
    }

    public final void a(com.google.android.exoplayer2.source.d source) {
        this.f.obtainMessage(8, source).sendToTarget();
    }

    public final boolean handleMessage(Message msg) {
        int i;
        int intValue;
        long longValue;
        try {
            Object obj;
            boolean z;
            int i2;
            Object obj2;
            int i3;
            long j;
            a aVar;
            long j2;
            int i4;
            m mVar;
            Pair a;
            a aVar2;
            com.google.android.exoplayer2.source.d dVar;
            switch (msg.what) {
                case 0:
                    e eVar = (e) msg.obj;
                    if (msg.arg1 != 0) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    this.h.sendEmptyMessage(0);
                    c(true);
                    this.d.a();
                    if (obj != null) {
                        this.l = new b(0, -9223372036854775807L);
                    }
                    this.p = eVar;
                    eVar.a(this.i, this);
                    a(2);
                    this.f.sendEmptyMessage(2);
                    return true;
                case 1:
                    if (msg.arg1 != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.t = false;
                    this.s = z;
                    if (!z) {
                        d();
                        e();
                    } else if (this.v == 3) {
                        c();
                        this.f.sendEmptyMessage(2);
                    } else if (this.v == 2) {
                        this.f.sendEmptyMessage(2);
                    }
                    return true;
                case 2:
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (this.F != null) {
                        int i5;
                        m mVar2;
                        com.google.android.exoplayer2.source.g gVar;
                        i iVar;
                        i iVar2;
                        m mVar3;
                        f a2;
                        o oVar;
                        Format[] formatArr;
                        if (this.C == null) {
                            i5 = this.l.a;
                        } else {
                            i2 = this.C.f;
                            if (!this.C.h && this.C.b() && this.F.a(i2, this.k, false).d != -9223372036854775807L && (this.E == null || i2 - this.E.f != 100)) {
                                i5 = this.C.f + 1;
                            }
                            if (this.C != null || this.C.b()) {
                                b(false);
                            } else if (this.C != null && this.C.l) {
                                h();
                            }
                            if (this.E != null) {
                                while (this.E != this.D && this.B >= this.E.k.e) {
                                    this.E.d();
                                    b(this.E.k);
                                    this.l = new b(this.E.f, this.E.g);
                                    e();
                                    this.h.obtainMessage(5, this.l).sendToTarget();
                                }
                                if (!this.D.h) {
                                    while (i2 < this.a.length) {
                                        mVar2 = this.a[i2];
                                        gVar = this.D.c[i2];
                                        if (mVar2.f() == gVar && (gVar == null || mVar2.g())) {
                                        }
                                    }
                                    if (this.D.k != null && this.D.k.i) {
                                        iVar = this.D.m;
                                        this.D = this.D.k;
                                        iVar2 = this.D.m;
                                        obj2 = this.D.a.f() == -9223372036854775807L ? 1 : null;
                                        i = 0;
                                        while (true) {
                                            i3 = i;
                                            if (i3 < this.a.length) {
                                                mVar3 = this.a[i3];
                                                if (iVar.b.a(i3) == null) {
                                                    if (obj2 == null) {
                                                        if (!mVar3.i()) {
                                                            a2 = iVar2.b.a(i3);
                                                            obj = iVar.d[i3];
                                                            oVar = iVar2.d[i3];
                                                            if (a2 != null && oVar.equals(obj)) {
                                                                formatArr = new Format[a2.b()];
                                                                for (i = 0; i < formatArr.length; i++) {
                                                                    formatArr[i] = a2.a(i);
                                                                }
                                                                mVar3.a(formatArr, this.D.c[i3], this.D.a());
                                                            }
                                                        }
                                                    }
                                                    mVar3.h();
                                                }
                                                i = i3 + 1;
                                            }
                                        }
                                    }
                                } else {
                                    for (i2 = 0; i2 < this.a.length; i2++) {
                                        mVar2 = this.a[i2];
                                        gVar = this.D.c[i2];
                                        if (gVar != null && mVar2.f() == gVar && mVar2.g()) {
                                            mVar2.h();
                                        }
                                    }
                                }
                            }
                        }
                        if (i5 >= this.F.c()) {
                            this.p.a();
                        } else {
                            long j3;
                            if (this.C == null) {
                                j3 = this.l.c;
                            } else {
                                i3 = this.F.a(i5, this.k, false).c;
                                if (i5 != this.F.a(i3, this.j, 0).f) {
                                    j3 = 0;
                                } else {
                                    Pair a3 = a(this.F, i3, -9223372036854775807L, Math.max(0, (this.C.a() + this.F.a(this.C.f, this.k, false).d) - this.B));
                                    if (a3 != null) {
                                        i5 = ((Integer) a3.first).intValue();
                                        j3 = ((Long) a3.second).longValue();
                                    }
                                }
                            }
                            if (this.C == null) {
                                j = j3 + 60000000;
                            } else {
                                j = this.F.a(this.C.f, this.k, false).d + this.C.a();
                            }
                            this.F.a(i5, this.k, true);
                            boolean z2 = i5 == this.F.c() + -1 && !this.F.a(this.k.c, this.j, 0).e;
                            aVar = new a(this.a, this.b, j, this.c, this.d, this.p, this.k.b, i5, z2, j3);
                            if (this.C != null) {
                                this.C.k = aVar;
                            }
                            this.C = aVar;
                            this.C.a.a((com.google.android.exoplayer2.source.d.a) this);
                            b(true);
                        }
                        if (this.C != null) {
                            break;
                        }
                        b(false);
                        if (this.E != null) {
                            while (this.E != this.D) {
                                this.E.d();
                                b(this.E.k);
                                this.l = new b(this.E.f, this.E.g);
                                e();
                                this.h.obtainMessage(5, this.l).sendToTarget();
                                break;
                            }
                            if (!this.D.h) {
                                while (i2 < this.a.length) {
                                    mVar2 = this.a[i2];
                                    gVar = this.D.c[i2];
                                    mVar2.h();
                                    break;
                                }
                            }
                            for (i2 = 0; i2 < this.a.length; i2++) {
                                mVar2 = this.a[i2];
                                gVar = this.D.c[i2];
                            }
                            iVar = this.D.m;
                            this.D = this.D.k;
                            iVar2 = this.D.m;
                            if (this.D.a.f() == -9223372036854775807L) {
                            }
                            i = 0;
                            while (true) {
                                i3 = i;
                                if (i3 < this.a.length) {
                                    mVar3 = this.a[i3];
                                    if (iVar.b.a(i3) == null) {
                                        if (obj2 == null) {
                                            if (!mVar3.i()) {
                                                a2 = iVar2.b.a(i3);
                                                obj = iVar.d[i3];
                                                oVar = iVar2.d[i3];
                                                formatArr = new Format[a2.b()];
                                                for (i = 0; i < formatArr.length; i++) {
                                                    formatArr[i] = a2.a(i);
                                                }
                                                mVar3.a(formatArr, this.D.c[i3], this.D.a());
                                                break;
                                            }
                                        }
                                        mVar3.h();
                                    }
                                    i = i3 + 1;
                                }
                            }
                            break;
                        }
                    }
                    this.p.a();
                    if (this.E == null) {
                        g();
                        a(elapsedRealtime, 10);
                    } else {
                        r.a("doSomeWork");
                        e();
                        j2 = this.l.c;
                        Object obj3 = 1;
                        z = true;
                        for (m mVar4 : this.q) {
                            mVar4.a(this.B, this.y);
                            obj3 = (obj3 == null || !mVar4.u()) ? null : 1;
                            obj = (mVar4.t() || mVar4.u()) ? 1 : null;
                            if (obj == null) {
                                mVar4.j();
                            }
                            if (!z || obj == null) {
                                z = false;
                            } else {
                                z = true;
                            }
                        }
                        if (!z) {
                            g();
                        }
                        if (this.o != null) {
                            l z3 = this.o.z();
                            if (!z3.equals(this.m)) {
                                this.m = z3;
                                this.e.a(this.o);
                                this.h.obtainMessage(7, z3).sendToTarget();
                            }
                        }
                        j = this.F.a(this.E.f, this.k, false).d;
                        if (obj3 != null && ((j == -9223372036854775807L || j <= this.l.c) && this.E.h)) {
                            a(4);
                            d();
                        } else if (this.v == 2) {
                            if (this.q.length > 0) {
                                if (z) {
                                    z = this.t;
                                    if (this.C.i) {
                                        j2 = this.C.a.g();
                                    } else {
                                        j2 = this.C.g;
                                    }
                                    if (j2 == Long.MIN_VALUE) {
                                        if (this.C.h) {
                                            z = true;
                                            if (z) {
                                                z = true;
                                            }
                                        } else {
                                            j2 = this.F.a(this.C.f, this.k, false).d;
                                        }
                                    }
                                    z = this.d.a(j2 - (this.B - this.C.a()), z);
                                    if (z) {
                                        z = true;
                                    }
                                }
                                z = false;
                            } else {
                                z = b(j);
                            }
                            if (z) {
                                a(3);
                                if (this.s) {
                                    c();
                                }
                            }
                        } else if (this.v == 3) {
                            if (this.q.length <= 0) {
                                z = b(j);
                            }
                            if (!z) {
                                this.t = this.s;
                                a(2);
                                d();
                            }
                        }
                        if (this.v == 2) {
                            for (m j4 : this.q) {
                                j4.j();
                            }
                        }
                        if ((this.s && this.v == 3) || this.v == 2) {
                            a(elapsedRealtime, 10);
                        } else if (this.q.length != 0) {
                            a(elapsedRealtime, 1000);
                        } else {
                            this.f.removeMessages(2);
                        }
                        r.a();
                    }
                    return true;
                case 3:
                    c cVar = (c) msg.obj;
                    if (this.F == null) {
                        this.z++;
                        this.A = cVar;
                    } else {
                        a = a(cVar);
                        if (a == null) {
                            this.l = new b(0, 0);
                            this.h.obtainMessage(4, 1, 0, this.l).sendToTarget();
                            this.l = new b(0, -9223372036854775807L);
                            a(4);
                            c(false);
                        } else {
                            if (cVar.c == -9223372036854775807L) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            intValue = ((Integer) a.first).intValue();
                            longValue = ((Long) a.second).longValue();
                            if (intValue == this.l.a && longValue / 1000 == this.l.c / 1000) {
                                this.l = new b(intValue, longValue);
                                this.h.obtainMessage(4, i != 0 ? 1 : 0, 0, this.l).sendToTarget();
                            } else {
                                long a4 = a(intValue, longValue);
                                i2 = (longValue != a4 ? 1 : 0) | i;
                                this.l = new b(intValue, a4);
                                this.h.obtainMessage(4, i2 != 0 ? 1 : 0, 0, this.l).sendToTarget();
                            }
                        }
                    }
                    return true;
                case 4:
                    l lVar = (l) msg.obj;
                    if (this.o != null) {
                        lVar = this.o.a(lVar);
                    } else {
                        lVar = this.e.a(lVar);
                    }
                    this.m = lVar;
                    this.h.obtainMessage(7, lVar).sendToTarget();
                    return true;
                case 5:
                    f();
                    return true;
                case 6:
                    c(true);
                    this.d.c();
                    a(1);
                    synchronized (this) {
                        this.r = true;
                        notifyAll();
                    }
                    return true;
                case 7:
                    a aVar3;
                    Pair b;
                    Object obj4;
                    b bVar;
                    b bVar2;
                    int i6;
                    a aVar4;
                    Pair pair = (Pair) msg.obj;
                    r rVar = this.F;
                    this.F = (r) pair.first;
                    Object obj5 = pair.second;
                    if (rVar == null) {
                        if (this.z > 0) {
                            a = a(this.A);
                            i = this.z;
                            this.z = 0;
                            this.A = null;
                            if (a == null) {
                                a(obj5, i);
                            } else {
                                this.l = new b(((Integer) a.first).intValue(), ((Long) a.second).longValue());
                                i4 = i;
                                if (this.E == null) {
                                    aVar3 = this.E;
                                } else {
                                    aVar3 = this.C;
                                }
                                if (aVar3 != null) {
                                    i = this.F.a(aVar3.b);
                                    if (i != -1) {
                                        i2 = a(aVar3.f, rVar, this.F);
                                        if (i2 != -1) {
                                            a(obj5, i4);
                                        } else {
                                            b = b(this.F.a(i2, this.k, false).c);
                                            i = ((Integer) b.first).intValue();
                                            j = ((Long) b.second).longValue();
                                            this.F.a(i, this.k, true);
                                            obj4 = this.k.b;
                                            aVar3.f = -1;
                                            while (aVar3.k != null) {
                                                aVar3 = aVar3.k;
                                                aVar3.f = aVar3.b.equals(obj4) ? i : -1;
                                            }
                                            this.l = new b(i, a(i, j));
                                        }
                                    } else {
                                        this.F.a(i, this.k, false);
                                        z = (i == this.F.c() + -1 || this.F.a(this.k.c, this.j, 0).e) ? false : true;
                                        aVar3.a(i, z);
                                        i2 = aVar3 != this.D ? 1 : 0;
                                        if (i != this.l.a) {
                                            bVar = this.l;
                                            bVar2 = new b(i, bVar.b);
                                            bVar2.c = bVar.c;
                                            bVar2.d = bVar.d;
                                            this.l = bVar2;
                                        }
                                        aVar2 = aVar3;
                                        i6 = i;
                                        i = i2;
                                        i2 = i6;
                                        while (aVar2.k != null) {
                                            aVar4 = aVar2.k;
                                            i3 = i2 + 1;
                                            this.F.a(i3, this.k, true);
                                            z = (i3 == this.F.c() + -1 || this.F.a(this.k.c, this.j, 0).e) ? false : true;
                                            if (aVar4.b.equals(this.k.b)) {
                                                aVar4.a(i3, z);
                                                if (aVar4 != this.D) {
                                                    i2 = 1;
                                                } else {
                                                    i2 = 0;
                                                }
                                                i = i2 | i;
                                                aVar2 = aVar4;
                                                i2 = i3;
                                            } else if (i != 0) {
                                                i2 = this.E.f;
                                                this.l = new b(i2, a(i2, this.l.c));
                                            } else {
                                                this.C = aVar2;
                                                this.C.k = null;
                                                a(aVar4);
                                            }
                                        }
                                    }
                                }
                                b(obj5, i4);
                            }
                        } else if (this.l.b == -9223372036854775807L) {
                            if (this.F.a()) {
                                a(obj5, 0);
                            } else {
                                a = b(0);
                                this.l = new b(((Integer) a.first).intValue(), ((Long) a.second).longValue());
                            }
                        }
                        return true;
                    }
                    i4 = 0;
                    if (this.E == null) {
                        aVar3 = this.C;
                    } else {
                        aVar3 = this.E;
                    }
                    if (aVar3 != null) {
                        i = this.F.a(aVar3.b);
                        if (i != -1) {
                            this.F.a(i, this.k, false);
                            if (i == this.F.c() + -1) {
                                break;
                            }
                            aVar3.a(i, z);
                            if (aVar3 != this.D) {
                            }
                            if (i != this.l.a) {
                                bVar = this.l;
                                bVar2 = new b(i, bVar.b);
                                bVar2.c = bVar.c;
                                bVar2.d = bVar.d;
                                this.l = bVar2;
                            }
                            aVar2 = aVar3;
                            i6 = i;
                            i = i2;
                            i2 = i6;
                            while (aVar2.k != null) {
                                aVar4 = aVar2.k;
                                i3 = i2 + 1;
                                this.F.a(i3, this.k, true);
                                if (i3 == this.F.c() + -1) {
                                    break;
                                }
                                if (aVar4.b.equals(this.k.b)) {
                                    aVar4.a(i3, z);
                                    if (aVar4 != this.D) {
                                        i2 = 0;
                                    } else {
                                        i2 = 1;
                                    }
                                    i = i2 | i;
                                    aVar2 = aVar4;
                                    i2 = i3;
                                } else if (i != 0) {
                                    this.C = aVar2;
                                    this.C.k = null;
                                    a(aVar4);
                                } else {
                                    i2 = this.E.f;
                                    this.l = new b(i2, a(i2, this.l.c));
                                }
                            }
                        } else {
                            i2 = a(aVar3.f, rVar, this.F);
                            if (i2 != -1) {
                                b = b(this.F.a(i2, this.k, false).c);
                                i = ((Integer) b.first).intValue();
                                j = ((Long) b.second).longValue();
                                this.F.a(i, this.k, true);
                                obj4 = this.k.b;
                                aVar3.f = -1;
                                while (aVar3.k != null) {
                                    aVar3 = aVar3.k;
                                    if (aVar3.b.equals(obj4)) {
                                    }
                                    aVar3.f = aVar3.b.equals(obj4) ? i : -1;
                                }
                                this.l = new b(i, a(i, j));
                            } else {
                                a(obj5, i4);
                                return true;
                            }
                        }
                    }
                    b(obj5, i4);
                    return true;
                case 8:
                    dVar = (com.google.android.exoplayer2.source.d) msg.obj;
                    if (this.C != null && this.C.a == dVar) {
                        aVar = this.C;
                        aVar.i = true;
                        aVar.c();
                        aVar.g = aVar.a(aVar.g);
                        if (this.E == null) {
                            this.D = this.C;
                            a(this.D.g);
                            b(this.D);
                        }
                        h();
                    }
                    return true;
                case 9:
                    dVar = (com.google.android.exoplayer2.source.d) msg.obj;
                    if (this.C != null && this.C.a == dVar) {
                        h();
                    }
                    return true;
                case 10:
                    if (this.E != null) {
                        obj2 = 1;
                        aVar2 = this.E;
                        while (aVar2 != null && aVar2.i) {
                            if (aVar2.c()) {
                                if (obj2 != null) {
                                    z = this.D != this.E;
                                    a(this.E.k);
                                    this.E.k = null;
                                    this.C = this.E;
                                    this.D = this.E;
                                    boolean[] zArr = new boolean[this.a.length];
                                    j2 = this.E.a(this.l.c, z, zArr);
                                    if (j2 != this.l.c) {
                                        this.l.c = j2;
                                        a(j2);
                                    }
                                    i = 0;
                                    boolean[] zArr2 = new boolean[this.a.length];
                                    i3 = 0;
                                    while (true) {
                                        i2 = i;
                                        if (i3 < this.a.length) {
                                            mVar4 = this.a[i3];
                                            zArr2[i3] = mVar4.d() != 0;
                                            com.google.android.exoplayer2.source.g gVar2 = this.E.c[i3];
                                            if (gVar2 != null) {
                                                i2++;
                                            }
                                            if (zArr2[i3]) {
                                                if (gVar2 != mVar4.f()) {
                                                    if (mVar4 == this.n) {
                                                        if (gVar2 == null) {
                                                            this.e.a(this.o);
                                                        }
                                                        this.o = null;
                                                        this.n = null;
                                                    }
                                                    a(mVar4);
                                                    mVar4.l();
                                                } else if (zArr[i3]) {
                                                    mVar4.a(this.B);
                                                }
                                            }
                                            i = i3 + 1;
                                        } else {
                                            this.h.obtainMessage(3, aVar2.m).sendToTarget();
                                            a(zArr2, i2);
                                        }
                                    }
                                } else {
                                    this.C = aVar2;
                                    for (aVar = this.C.k; aVar != null; aVar = aVar.k) {
                                        aVar.d();
                                    }
                                    this.C.k = null;
                                    if (this.C.i) {
                                        this.C.a(Math.max(this.C.g, this.B - this.C.a()));
                                    }
                                }
                                h();
                                e();
                                this.f.sendEmptyMessage(2);
                            } else {
                                if (aVar2 == this.D) {
                                    obj2 = null;
                                }
                                aVar2 = aVar2.k;
                            }
                        }
                    }
                    return true;
                case 11:
                    for (com.google.android.exoplayer2.d.c cVar2 : (com.google.android.exoplayer2.d.c[]) msg.obj) {
                        cVar2.a.a(cVar2.b, cVar2.c);
                    }
                    if (this.p != null) {
                        this.f.sendEmptyMessage(2);
                    }
                    synchronized (this) {
                        this.x++;
                        notifyAll();
                    }
                    return true;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            this.h.obtainMessage(8, e).sendToTarget();
            f();
            return true;
        } catch (IOException e2) {
            this.h.obtainMessage(8, ExoPlaybackException.a(e2)).sendToTarget();
            f();
            return true;
        } catch (RuntimeException e3) {
            this.h.obtainMessage(8, ExoPlaybackException.a(e3)).sendToTarget();
            f();
            return true;
        } catch (Throwable th) {
            this.l = new b(intValue, longValue);
            this.h.obtainMessage(4, i != 0 ? 1 : 0, 0, this.l).sendToTarget();
        }
    }

    private void a(int state) {
        if (this.v != state) {
            this.v = state;
            this.h.obtainMessage(1, state, 0).sendToTarget();
        }
    }

    private void b(boolean isLoading) {
        if (this.u != isLoading) {
            int i;
            this.u = isLoading;
            Handler handler = this.h;
            if (isLoading) {
                i = 1;
            } else {
                i = 0;
            }
            handler.obtainMessage(2, i, 0).sendToTarget();
        }
    }

    private void c() throws ExoPlaybackException {
        int i = 0;
        this.t = false;
        this.e.a();
        m[] mVarArr = this.q;
        int length = mVarArr.length;
        while (i < length) {
            mVarArr[i].e();
            i++;
        }
    }

    private void d() throws ExoPlaybackException {
        this.e.b();
        for (m a : this.q) {
            a(a);
        }
    }

    private void e() throws ExoPlaybackException {
        if (this.E != null) {
            long bufferedPositionUs;
            long periodPositionUs = this.E.a.f();
            if (periodPositionUs != -9223372036854775807L) {
                a(periodPositionUs);
            } else {
                if (this.n == null || this.n.u()) {
                    this.B = this.e.y();
                } else {
                    this.B = this.o.y();
                    this.e.a(this.B);
                }
                periodPositionUs = this.B - this.E.a();
            }
            this.l.c = periodPositionUs;
            this.y = SystemClock.elapsedRealtime() * 1000;
            if (this.q.length == 0) {
                bufferedPositionUs = Long.MIN_VALUE;
            } else {
                bufferedPositionUs = this.E.a.g();
            }
            b bVar = this.l;
            if (bufferedPositionUs == Long.MIN_VALUE) {
                bufferedPositionUs = this.F.a(this.E.f, this.k, false).d;
            }
            bVar.d = bufferedPositionUs;
        }
    }

    private void a(long thisOperationStartTimeMs, long intervalMs) {
        this.f.removeMessages(2);
        long nextOperationDelayMs = (thisOperationStartTimeMs + intervalMs) - SystemClock.elapsedRealtime();
        if (nextOperationDelayMs <= 0) {
            this.f.sendEmptyMessage(2);
        } else {
            this.f.sendEmptyMessageDelayed(2, nextOperationDelayMs);
        }
    }

    private long a(int periodIndex, long periodPositionUs) throws ExoPlaybackException {
        d();
        this.t = false;
        a(2);
        a newPlayingPeriodHolder = null;
        if (this.E != null) {
            a periodHolder = this.E;
            while (periodHolder != null) {
                if (periodHolder.f == periodIndex && periodHolder.i) {
                    newPlayingPeriodHolder = periodHolder;
                } else {
                    periodHolder.d();
                }
                periodHolder = periodHolder.k;
            }
        } else if (this.C != null) {
            this.C.d();
        }
        if (!(this.E == newPlayingPeriodHolder && this.E == this.D)) {
            for (m l : this.q) {
                l.l();
            }
            this.q = new m[0];
            this.o = null;
            this.n = null;
            this.E = null;
        }
        if (newPlayingPeriodHolder != null) {
            newPlayingPeriodHolder.k = null;
            this.C = newPlayingPeriodHolder;
            this.D = newPlayingPeriodHolder;
            b(newPlayingPeriodHolder);
            if (this.E.j) {
                periodPositionUs = this.E.a.b(periodPositionUs);
            }
            a(periodPositionUs);
            h();
        } else {
            this.C = null;
            this.D = null;
            this.E = null;
            a(periodPositionUs);
        }
        this.f.sendEmptyMessage(2);
        return periodPositionUs;
    }

    private void a(long periodPositionUs) throws ExoPlaybackException {
        long j;
        if (this.E == null) {
            j = 60000000 + periodPositionUs;
        } else {
            j = this.E.a() + periodPositionUs;
        }
        this.B = j;
        this.e.a(this.B);
        for (m a : this.q) {
            a.a(this.B);
        }
    }

    private void f() {
        c(true);
        this.d.b();
        a(1);
    }

    private void c(boolean releaseMediaSource) {
        this.f.removeMessages(2);
        this.t = false;
        this.e.b();
        this.o = null;
        this.n = null;
        this.B = 60000000;
        for (m renderer : this.q) {
            try {
                a(renderer);
                renderer.l();
            } catch (ExoPlaybackException e) {
            } catch (RuntimeException e2) {
            }
        }
        this.q = new m[0];
        a(this.E != null ? this.E : this.C);
        this.C = null;
        this.D = null;
        this.E = null;
        b(false);
        if (releaseMediaSource) {
            if (this.p != null) {
                this.p.b();
                this.p = null;
            }
            this.F = null;
        }
    }

    private static void a(m renderer) throws ExoPlaybackException {
        if (renderer.d() == 2) {
            renderer.k();
        }
    }

    private boolean b(long playingPeriodDurationUs) {
        return playingPeriodDurationUs == -9223372036854775807L || this.l.c < playingPeriodDurationUs || (this.E.k != null && this.E.k.i);
    }

    private void g() throws IOException {
        if (this.C != null && !this.C.i) {
            if (this.D == null || this.D.k == this.C) {
                m[] mVarArr = this.q;
                int length = mVarArr.length;
                int i = 0;
                while (i < length) {
                    if (mVarArr[i].g()) {
                        i++;
                    } else {
                        return;
                    }
                }
                this.C.a.d();
            }
        }
    }

    private void a(Object manifest, int processedInitialSeekCount) {
        this.l = new b(0, 0);
        b(manifest, processedInitialSeekCount);
        this.l = new b(0, -9223372036854775807L);
        a(4);
        c(false);
    }

    private void b(Object manifest, int processedInitialSeekCount) {
        this.h.obtainMessage(6, new d(this.F, manifest, this.l, processedInitialSeekCount)).sendToTarget();
    }

    private int a(int oldPeriodIndex, r oldTimeline, r newTimeline) {
        int newPeriodIndex = -1;
        while (newPeriodIndex == -1 && oldPeriodIndex < oldTimeline.c() - 1) {
            oldPeriodIndex++;
            newPeriodIndex = newTimeline.a(oldTimeline.a(oldPeriodIndex, this.k, true).b);
        }
        return newPeriodIndex;
    }

    private Pair<Integer, Long> a(c seekPosition) {
        r seekTimeline = seekPosition.a;
        if (seekTimeline.a()) {
            seekTimeline = this.F;
        }
        try {
            Pair<Integer, Long> periodPosition = b(seekTimeline, seekPosition.b, seekPosition.c);
            if (this.F == seekTimeline) {
                return periodPosition;
            }
            int periodIndex = this.F.a(seekTimeline.a(((Integer) periodPosition.first).intValue(), this.k, true).b);
            if (periodIndex != -1) {
                return Pair.create(Integer.valueOf(periodIndex), periodPosition.second);
            }
            periodIndex = a(((Integer) periodPosition.first).intValue(), seekTimeline, this.F);
            if (periodIndex != -1) {
                return b(this.F.a(periodIndex, this.k, false).c);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            throw new i(this.F, seekPosition.b, seekPosition.c);
        }
    }

    private Pair<Integer, Long> b(int windowIndex) {
        return b(this.F, windowIndex, -9223372036854775807L);
    }

    private Pair<Integer, Long> b(r timeline, int windowIndex, long windowPositionUs) {
        return a(timeline, windowIndex, windowPositionUs, 0);
    }

    private Pair<Integer, Long> a(r timeline, int windowIndex, long windowPositionUs, long defaultPositionProjectionUs) {
        com.google.android.exoplayer2.d.a.a(windowIndex, timeline.b());
        timeline.a(windowIndex, this.j, defaultPositionProjectionUs);
        if (windowPositionUs == -9223372036854775807L) {
            windowPositionUs = this.j.h;
            if (windowPositionUs == -9223372036854775807L) {
                return null;
            }
        }
        int periodIndex = this.j.f;
        long periodPositionUs = this.j.j + windowPositionUs;
        long periodDurationUs = timeline.a(periodIndex, this.k, false).d;
        while (periodDurationUs != -9223372036854775807L && periodPositionUs >= periodDurationUs && periodIndex < this.j.g) {
            periodPositionUs -= periodDurationUs;
            periodIndex++;
            periodDurationUs = timeline.a(periodIndex, this.k, false).d;
        }
        return Pair.create(Integer.valueOf(periodIndex), Long.valueOf(periodPositionUs));
    }

    private void h() {
        long nextLoadPositionUs;
        if (this.C.i) {
            nextLoadPositionUs = this.C.a.a();
        } else {
            nextLoadPositionUs = 0;
        }
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            b(false);
            return;
        }
        long loadingPeriodPositionUs = this.B - this.C.a();
        boolean continueLoading = this.d.a(nextLoadPositionUs - loadingPeriodPositionUs);
        b(continueLoading);
        if (continueLoading) {
            this.C.l = false;
            this.C.a.a(loadingPeriodPositionUs);
            return;
        }
        this.C.l = true;
    }

    private static void a(a periodHolder) {
        while (periodHolder != null) {
            periodHolder.d();
            periodHolder = periodHolder.k;
        }
    }

    private void b(a periodHolder) throws ExoPlaybackException {
        if (this.E != periodHolder) {
            int enabledRendererCount = 0;
            boolean[] rendererWasEnabledFlags = new boolean[this.a.length];
            int i = 0;
            while (i < this.a.length) {
                m renderer = this.a[i];
                rendererWasEnabledFlags[i] = renderer.d() != 0;
                f newSelection = periodHolder.m.b.a(i);
                if (newSelection != null) {
                    enabledRendererCount++;
                }
                if (rendererWasEnabledFlags[i] && (newSelection == null || (renderer.i() && renderer.f() == this.E.c[i]))) {
                    if (renderer == this.n) {
                        this.e.a(this.o);
                        this.o = null;
                        this.n = null;
                    }
                    a(renderer);
                    renderer.l();
                }
                i++;
            }
            this.E = periodHolder;
            this.h.obtainMessage(3, periodHolder.m).sendToTarget();
            a(rendererWasEnabledFlags, enabledRendererCount);
        }
    }

    private void a(boolean[] rendererWasEnabledFlags, int enabledRendererCount) throws ExoPlaybackException {
        this.q = new m[enabledRendererCount];
        enabledRendererCount = 0;
        for (int i = 0; i < this.a.length; i++) {
            m renderer = this.a[i];
            f newSelection = this.E.m.b.a(i);
            if (newSelection != null) {
                int enabledRendererCount2 = enabledRendererCount + 1;
                this.q[enabledRendererCount] = renderer;
                if (renderer.d() == 0) {
                    o rendererConfiguration = this.E.m.d[i];
                    boolean playing = this.s && this.v == 3;
                    boolean joining = !rendererWasEnabledFlags[i] && playing;
                    Format[] formats = new Format[newSelection.b()];
                    for (int j = 0; j < formats.length; j++) {
                        formats[j] = newSelection.a(j);
                    }
                    renderer.a(rendererConfiguration, formats, this.E.c[i], this.B, joining, this.E.a());
                    com.google.android.exoplayer2.d.g mediaClock = renderer.c();
                    if (mediaClock != null) {
                        if (this.o != null) {
                            throw ExoPlaybackException.a(new IllegalStateException("Multiple renderer media clocks enabled."));
                        }
                        this.o = mediaClock;
                        this.n = renderer;
                        this.o.a(this.m);
                    }
                    if (playing) {
                        renderer.e();
                    }
                }
                enabledRendererCount = enabledRendererCount2;
            }
        }
    }
}
