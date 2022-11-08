package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.c;
import defpackage.bbs;
import defpackage.bcz;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class p {
    public final int a;
    public final m b;
    private final CopyOnWriteArrayList<q> c;
    private final long d;

    public p() {
        this(new CopyOnWriteArrayList(), null);
    }

    private p(CopyOnWriteArrayList<q> copyOnWriteArrayList, m mVar) {
        this.c = copyOnWriteArrayList;
        this.a = 0;
        this.b = mVar;
        this.d = 0;
    }

    public final p a(m mVar) {
        return new p(this.c, mVar);
    }

    public final void a(Handler handler, o oVar) {
        boolean z = (handler == null || oVar == null) ? false : true;
        bcz.a(z);
        this.c.add(new q(handler, oVar));
    }

    public final void a(o oVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            if (qVar.b == oVar) {
                this.c.remove(qVar);
            }
        }
    }

    public final void a() {
        m mVar = (m) bcz.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$1dN9RZcEyk_wWADaJj4l5zsxvlE(this, qVar.b, mVar));
        }
    }

    private /* synthetic */ void c(o oVar, m mVar) {
        oVar.onMediaPeriodCreated(this.a, mVar);
    }

    public final void b() {
        m mVar = (m) bcz.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$sS9T3iuqh5OT2jtjqaEDPvTrIk0(this, qVar.b, mVar));
        }
    }

    private /* synthetic */ void b(o oVar, m mVar) {
        oVar.onMediaPeriodReleased(this.a, mVar);
    }

    public final void a(bbs bbs, int i, long j) {
        a(bbs, i, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j);
    }

    public final void a(bbs bbs, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
        bbs bbs2 = bbs;
        a(new r(bbs2, bbs2.a, Collections.emptyMap(), j3, 0, 0), new s(i, i2, format, i3, obj, a(j), a(j2)));
    }

    private void a(r rVar, s sVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$MFA2G9DRGqWGWStc58eiFjCEYVw(this, qVar.b, rVar, sVar));
        }
    }

    private /* synthetic */ void c(o oVar, r rVar, s sVar) {
        oVar.onLoadStarted(this.a, this.b, rVar, sVar);
    }

    public final void a(bbs bbs, Uri uri, Map<String, List<String>> map, long j, long j2, long j3) {
        a(bbs, uri, map, 4, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void a(bbs bbs, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        p pVar = this;
        b(new r(bbs, uri, map, j3, j4, j5), new s(i, i2, format, i3, obj, a(j), a(j2)));
    }

    private void b(r rVar, s sVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$CPZsCuRZgRoqDdRDCRzZhmV9oio(this, qVar.b, rVar, sVar));
        }
    }

    private /* synthetic */ void b(o oVar, r rVar, s sVar) {
        oVar.onLoadCompleted(this.a, this.b, rVar, sVar);
    }

    public final void b(bbs bbs, Uri uri, Map<String, List<String>> map, long j, long j2, long j3) {
        b(bbs, uri, map, 4, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3);
    }

    public final void b(bbs bbs, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        p pVar = this;
        c(new r(bbs, uri, map, j3, j4, j5), new s(i, i2, format, i3, obj, a(j), a(j2)));
    }

    private void c(r rVar, s sVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$D8G6JIsHMDecMrhzs-QkGnFEIRw(this, qVar.b, rVar, sVar));
        }
    }

    private /* synthetic */ void a(o oVar, r rVar, s sVar) {
        oVar.onLoadCanceled(this.a, this.b, rVar, sVar);
    }

    public final void a(bbs bbs, Uri uri, Map<String, List<String>> map, long j, long j2, long j3, IOException iOException, boolean z) {
        a(bbs, uri, map, 4, -1, null, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, j3, iOException, z);
    }

    public final void a(bbs bbs, Uri uri, Map<String, List<String>> map, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
        p pVar = this;
        a(new r(bbs, uri, map, j3, j4, j5), new s(i, i2, format, i3, obj, a(j), a(j2)), iOException, z);
    }

    private void a(r rVar, s sVar, IOException iOException, boolean z) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$eb5DAfd8jCXGQDskJzZ4kVqyI7c(this, qVar.b, rVar, sVar, iOException, z));
        }
    }

    private /* synthetic */ void a(o oVar, r rVar, s sVar, IOException iOException, boolean z) {
        oVar.onLoadError(this.a, this.b, rVar, sVar, iOException, z);
    }

    public final void c() {
        m mVar = (m) bcz.a(this.b);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$CUTLxAVzqVJdxdWPfsG5ql90WWU(this, qVar.b, mVar));
        }
    }

    private /* synthetic */ void a(o oVar, m mVar) {
        oVar.onReadingStarted(this.a, mVar);
    }

    public final void a(int i, Format format, int i2, Object obj, long j) {
        s sVar = new s(1, i, format, i2, obj, a(j), -9223372036854775807L);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            a(qVar.a, new -$$Lambda$p$NNBUjcgdD001zR55VqFN2tkT_KI(this, qVar.b, sVar));
        }
    }

    private /* synthetic */ void a(o oVar, s sVar) {
        oVar.onDownstreamFormatChanged(this.a, this.b, sVar);
    }

    private long a(long j) {
        j = c.a(j);
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.d + j;
    }

    private static void a(Handler handler, Runnable runnable) {
        if (handler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }
}
