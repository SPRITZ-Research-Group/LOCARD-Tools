package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.d;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.upstream.b;
import java.io.IOException;

public final class c implements e, com.google.android.exoplayer2.source.e.a {
    private final Uri a;
    private final com.google.android.exoplayer2.upstream.f.a b;
    private final i c;
    private final int d;
    private final Handler e;
    private final a f;
    private final com.google.android.exoplayer2.r.a g;
    private final String h;
    private com.google.android.exoplayer2.source.e.a i;
    private r j;
    private boolean k;

    public interface a {
    }

    public c(Uri uri, com.google.android.exoplayer2.upstream.f.a dataSourceFactory, i extractorsFactory) {
        this(uri, dataSourceFactory, extractorsFactory, (byte) 0);
    }

    private c(Uri uri, com.google.android.exoplayer2.upstream.f.a dataSourceFactory, i extractorsFactory, byte b) {
        this.a = uri;
        this.b = dataSourceFactory;
        this.c = extractorsFactory;
        this.d = -1;
        this.e = null;
        this.f = null;
        this.h = null;
        this.g = new com.google.android.exoplayer2.r.a();
    }

    public final void a(d player, com.google.android.exoplayer2.source.e.a listener) {
        this.i = listener;
        this.j = new i(-9223372036854775807L, false);
        listener.a(this.j, null);
    }

    public final void a() throws IOException {
    }

    public final d a(int index, b allocator, long positionUs) {
        com.google.android.exoplayer2.d.a.a(index == 0);
        return new b(this.a, this.b.a(), this.c.a(), this.d, this.e, this.f, this, allocator, this.h);
    }

    public final void a(d mediaPeriod) {
        ((b) mediaPeriod).c();
    }

    public final void b() {
        this.i = null;
    }

    public final void a(r newTimeline, Object manifest) {
        boolean newTimelineHasDuration = false;
        if (newTimeline.a(0, this.g, false).d != -9223372036854775807L) {
            newTimelineHasDuration = true;
        }
        if (!this.k || newTimelineHasDuration) {
            this.j = newTimeline;
            this.k = newTimelineHasDuration;
            this.i.a(this.j, null);
        }
    }
}
