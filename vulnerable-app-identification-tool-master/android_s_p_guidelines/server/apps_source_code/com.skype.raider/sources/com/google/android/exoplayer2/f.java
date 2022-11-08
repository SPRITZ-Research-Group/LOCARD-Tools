package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.c.g;
import com.google.android.exoplayer2.c.h;
import com.google.android.exoplayer2.c.i;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.c;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.g.d;
import com.google.android.exoplayer2.r.b;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.k;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class f implements d {
    private final m[] a;
    private final h b;
    private final g c;
    private final Handler d;
    private final g e;
    private final CopyOnWriteArraySet<a> f;
    private final b g;
    private final r.a h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private r o;
    private Object p;
    private k q;
    private g r;
    private l s;
    private g.b t;
    private int u;
    private int v;
    private long w;

    @SuppressLint({"HandlerLeak"})
    public f(m[] renderers, h trackSelector, j loadControl) {
        boolean z;
        new StringBuilder("Init ExoPlayerLib/2.4.2 [").append(s.e).append("]");
        if (renderers.length > 0) {
            z = true;
        } else {
            z = false;
        }
        com.google.android.exoplayer2.d.a.b(z);
        this.a = (m[]) com.google.android.exoplayer2.d.a.a((Object) renderers);
        this.b = (h) com.google.android.exoplayer2.d.a.a((Object) trackSelector);
        this.j = false;
        this.k = 1;
        this.f = new CopyOnWriteArraySet();
        this.c = new g(new com.google.android.exoplayer2.c.f[renderers.length]);
        this.o = r.a;
        this.g = new b();
        this.h = new r.a();
        this.q = k.a;
        this.r = this.c;
        this.s = l.a;
        this.d = new Handler(this, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()) {
            final /* synthetic */ f a;

            public final void handleMessage(Message msg) {
                this.a.a(msg);
            }
        };
        this.t = new g.b(0, 0);
        this.e = new g(renderers, trackSelector, loadControl, this.j, this.d, this.t, this);
    }

    public final void a(a listener) {
        this.f.add(listener);
    }

    public final void a(e mediaSource) {
        Iterator it;
        if (!(this.o.a() && this.p == null)) {
            this.o = r.a;
            this.p = null;
            it = this.f.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (this.i) {
            this.i = false;
            this.q = k.a;
            this.r = this.c;
            this.b.a(null);
            it = this.f.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        this.m++;
        this.e.a(mediaSource);
    }

    public final void a(boolean playWhenReady) {
        if (this.j != playWhenReady) {
            this.j = playWhenReady;
            this.e.a(playWhenReady);
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(playWhenReady, this.k);
            }
        }
    }

    public final boolean a() {
        return this.j;
    }

    public final void b() {
        a(h(), -9223372036854775807L);
    }

    public final void a(long positionMs) {
        a(h(), positionMs);
    }

    private void a(int windowIndex, long positionMs) {
        if (windowIndex < 0 || (!this.o.a() && windowIndex >= this.o.b())) {
            throw new i(this.o, windowIndex, positionMs);
        }
        this.l++;
        this.u = windowIndex;
        if (this.o.a()) {
            this.v = 0;
        } else {
            long resolvedPositionMs;
            this.o.a(windowIndex, this.g, 0);
            if (positionMs == -9223372036854775807L) {
                resolvedPositionMs = this.g.h;
            } else {
                resolvedPositionMs = positionMs;
            }
            int periodIndex = this.g.f;
            long periodPositionUs = this.g.j + C.b(resolvedPositionMs);
            long periodDurationUs = this.o.a(periodIndex, this.h, false).d;
            while (periodDurationUs != -9223372036854775807L && periodPositionUs >= periodDurationUs && periodIndex < this.g.g) {
                periodPositionUs -= periodDurationUs;
                periodIndex++;
                periodDurationUs = this.o.a(periodIndex, this.h, false).d;
            }
            this.v = periodIndex;
        }
        if (positionMs == -9223372036854775807L) {
            this.w = 0;
            this.e.a(this.o, windowIndex, -9223372036854775807L);
            return;
        }
        this.w = positionMs;
        this.e.a(this.o, windowIndex, C.b(positionMs));
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public final void c() {
        this.e.a();
    }

    public final void d() {
        this.e.b();
        this.d.removeCallbacksAndMessages(null);
    }

    public final void a(c... messages) {
        this.e.a(messages);
    }

    public final void b(c... messages) {
        this.e.b(messages);
    }

    private int h() {
        if (this.o.a() || this.l > 0) {
            return this.u;
        }
        return this.o.a(this.t.a, this.h, false).c;
    }

    public final long e() {
        if (this.o.a()) {
            return -9223372036854775807L;
        }
        return C.a(this.o.a(h(), this.g, 0).i);
    }

    public final long f() {
        if (this.o.a() || this.l > 0) {
            return this.w;
        }
        this.o.a(this.t.a, this.h, false);
        return this.h.a() + C.a(this.t.c);
    }

    public final int g() {
        if (this.o.a()) {
            return 0;
        }
        long position;
        if (this.o.a() || this.l > 0) {
            position = this.w;
        } else {
            this.o.a(this.t.a, this.h, false);
            position = this.h.a() + C.a(this.t.d);
        }
        long duration = e();
        if (position == -9223372036854775807L || duration == -9223372036854775807L) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return s.a((int) ((100 * position) / duration), 0, 100);
    }

    final void a(Message msg) {
        boolean z = true;
        Iterator it;
        Iterator it2;
        switch (msg.what) {
            case 0:
                this.m--;
                return;
            case 1:
                this.k = msg.arg1;
                it = this.f.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a(this.j, this.k);
                }
                return;
            case 2:
                if (msg.arg1 == 0) {
                    z = false;
                }
                this.n = z;
                it2 = this.f.iterator();
                while (it2.hasNext()) {
                    it2.next();
                }
                return;
            case 3:
                if (this.m == 0) {
                    i trackSelectorResult = msg.obj;
                    this.i = true;
                    this.q = trackSelectorResult.a;
                    this.r = trackSelectorResult.b;
                    this.b.a(trackSelectorResult.c);
                    it2 = this.f.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                    }
                    return;
                }
                return;
            case 4:
                int i = this.l - 1;
                this.l = i;
                if (i == 0) {
                    this.t = (g.b) msg.obj;
                    if (msg.arg1 != 0) {
                        it2 = this.f.iterator();
                        while (it2.hasNext()) {
                            it2.next();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.l == 0) {
                    this.t = (g.b) msg.obj;
                    it2 = this.f.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                    }
                    return;
                }
                return;
            case 6:
                d sourceInfo = msg.obj;
                this.l -= sourceInfo.d;
                if (this.m == 0) {
                    this.o = sourceInfo.a;
                    this.p = sourceInfo.b;
                    this.t = sourceInfo.c;
                    it2 = this.f.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                    }
                    return;
                }
                return;
            case 7:
                l playbackParameters = msg.obj;
                if (!this.s.equals(playbackParameters)) {
                    this.s = playbackParameters;
                    it2 = this.f.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                    }
                    return;
                }
                return;
            case 8:
                ExoPlaybackException exception = msg.obj;
                it = this.f.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).a(exception);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
