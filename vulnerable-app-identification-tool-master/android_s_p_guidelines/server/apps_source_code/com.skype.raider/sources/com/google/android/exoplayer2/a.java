package com.google.android.exoplayer2;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.g;
import java.io.IOException;

public abstract class a implements m, n {
    private final int a;
    private o b;
    private int c;
    private int d;
    private g e;
    private long f;
    private boolean g = true;
    private boolean h;

    public a(int trackType) {
        this.a = trackType;
    }

    public final int a() {
        return this.a;
    }

    public final n b() {
        return this;
    }

    public final void a(int index) {
        this.c = index;
    }

    public com.google.android.exoplayer2.d.g c() {
        return null;
    }

    public final int d() {
        return this.d;
    }

    public final void a(o configuration, Format[] formats, g stream, long positionUs, boolean joining, long offsetUs) throws ExoPlaybackException {
        com.google.android.exoplayer2.d.a.b(this.d == 0);
        this.b = configuration;
        this.d = 1;
        a(joining);
        a(formats, stream, offsetUs);
        a(positionUs, joining);
    }

    public final void e() throws ExoPlaybackException {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        com.google.android.exoplayer2.d.a.b(z);
        this.d = 2;
        n();
    }

    public final void a(Format[] formats, g stream, long offsetUs) throws ExoPlaybackException {
        com.google.android.exoplayer2.d.a.b(!this.h);
        this.e = stream;
        this.g = false;
        this.f = offsetUs;
        a(formats);
    }

    public final g f() {
        return this.e;
    }

    public final boolean g() {
        return this.g;
    }

    public final void h() {
        this.h = true;
    }

    public final boolean i() {
        return this.h;
    }

    public final void j() throws IOException {
        this.e.b();
    }

    public final void a(long positionUs) throws ExoPlaybackException {
        this.h = false;
        this.g = false;
        a(positionUs, false);
    }

    public final void k() throws ExoPlaybackException {
        com.google.android.exoplayer2.d.a.b(this.d == 2);
        this.d = 1;
        o();
    }

    public final void l() {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        com.google.android.exoplayer2.d.a.b(z);
        this.d = 0;
        this.e = null;
        this.h = false;
        p();
    }

    public int m() throws ExoPlaybackException {
        return 0;
    }

    public void a(int what, Object object) throws ExoPlaybackException {
    }

    protected void a(boolean joining) throws ExoPlaybackException {
    }

    protected void a(Format[] formats) throws ExoPlaybackException {
    }

    protected void a(long positionUs, boolean joining) throws ExoPlaybackException {
    }

    protected void n() throws ExoPlaybackException {
    }

    protected void o() throws ExoPlaybackException {
    }

    protected void p() {
    }

    protected final o q() {
        return this.b;
    }

    protected final int r() {
        return this.c;
    }

    protected final int a(h formatHolder, DecoderInputBuffer buffer, boolean formatRequired) {
        int result = this.e.a(formatHolder, buffer, formatRequired);
        if (result == -4) {
            if (buffer.c()) {
                this.g = true;
                return this.h ? -4 : -3;
            } else {
                buffer.c += this.f;
                return result;
            }
        } else if (result != -5) {
            return result;
        } else {
            Format format = formatHolder.a;
            if (format.w == Long.MAX_VALUE) {
                return result;
            }
            formatHolder.a = format.a(format.w + this.f);
            return result;
        }
    }

    protected final void b(long positionUs) {
        this.e.a(positionUs - this.f);
    }

    protected final boolean s() {
        return this.g ? this.h : this.e.a();
    }
}
