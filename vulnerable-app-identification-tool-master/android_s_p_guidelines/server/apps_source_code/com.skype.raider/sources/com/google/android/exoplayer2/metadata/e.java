package com.google.android.exoplayer2.metadata;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.h;
import java.util.Arrays;

public final class e extends com.google.android.exoplayer2.a implements Callback {
    private final c a;
    private final a b;
    private final Handler c;
    private final h d;
    private final d e;
    private final Metadata[] f;
    private final long[] g;
    private int h;
    private int i;
    private a j;
    private boolean k;

    public interface a {
    }

    public e(a output, Looper outputLooper) {
        this(output, outputLooper, c.a);
    }

    private e(a output, Looper outputLooper, c decoderFactory) {
        super(4);
        this.b = (a) com.google.android.exoplayer2.d.a.a((Object) output);
        this.c = outputLooper == null ? null : new Handler(outputLooper, this);
        this.a = (c) com.google.android.exoplayer2.d.a.a((Object) decoderFactory);
        this.d = new h();
        this.e = new d();
        this.f = new Metadata[5];
        this.g = new long[5];
    }

    public final int a(Format format) {
        return this.a.a(format) ? 3 : 0;
    }

    protected final void a(Format[] formats) throws ExoPlaybackException {
        this.j = this.a.b(formats[0]);
    }

    protected final void a(long positionUs, boolean joining) {
        v();
        this.k = false;
    }

    public final void a(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        if (!this.k && this.i < 5) {
            this.e.a();
            if (a(this.d, this.e, false) == -4) {
                if (this.e.c()) {
                    this.k = true;
                } else if (!this.e.k_()) {
                    this.e.d = this.d.a.w;
                    this.e.h();
                    try {
                        int index = (this.h + this.i) % 5;
                        this.f[index] = this.j.a(this.e);
                        this.g[index] = this.e.c;
                        this.i++;
                    } catch (Exception e) {
                        throw ExoPlaybackException.a(e, r());
                    }
                }
            }
        }
        if (this.i > 0 && this.g[this.h] <= positionUs) {
            Object obj = this.f[this.h];
            if (this.c != null) {
                this.c.obtainMessage(0, obj).sendToTarget();
            }
            this.f[this.h] = null;
            this.h = (this.h + 1) % 5;
            this.i--;
        }
    }

    protected final void p() {
        v();
        this.j = null;
    }

    public final boolean u() {
        return this.k;
    }

    public final boolean t() {
        return true;
    }

    private void v() {
        Arrays.fill(this.f, null);
        this.h = 0;
        this.i = 0;
    }

    public final boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                Object obj = msg.obj;
                return true;
            default:
                throw new IllegalStateException();
        }
    }
}
