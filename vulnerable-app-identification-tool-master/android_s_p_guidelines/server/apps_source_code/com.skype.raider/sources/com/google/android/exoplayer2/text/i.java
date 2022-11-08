package com.google.android.exoplayer2.text;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.h;
import java.util.Collections;
import java.util.List;

public final class i extends com.google.android.exoplayer2.a implements Callback {
    private final Handler a;
    private final a b;
    private final f c;
    private final h d;
    private boolean e;
    private boolean f;
    private int g;
    private Format h;
    private d i;
    private g j;
    private h k;
    private h l;
    private int m;

    public interface a {
    }

    public i(a output, Looper outputLooper) {
        this(output, outputLooper, f.a);
    }

    private i(a output, Looper outputLooper, f decoderFactory) {
        super(3);
        this.b = (a) com.google.android.exoplayer2.d.a.a((Object) output);
        this.a = outputLooper == null ? null : new Handler(outputLooper, this);
        this.c = decoderFactory;
        this.d = new h();
    }

    public final int a(Format format) {
        if (this.c.a(format)) {
            return 3;
        }
        return com.google.android.exoplayer2.d.h.c(format.f) ? 1 : 0;
    }

    protected final void a(Format[] formats) throws ExoPlaybackException {
        this.h = formats[0];
        if (this.i != null) {
            this.g = 1;
        } else {
            this.i = this.c.b(this.h);
        }
    }

    protected final void a(long positionUs, boolean joining) {
        a(Collections.emptyList());
        this.e = false;
        this.f = false;
        if (this.g != 0) {
            x();
            return;
        }
        v();
        this.i.c();
    }

    public final void a(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        if (!this.f) {
            if (this.l == null) {
                this.i.a(positionUs);
                try {
                    this.l = (h) this.i.b();
                } catch (Exception e) {
                    throw ExoPlaybackException.a(e, r());
                }
            }
            if (d() == 2) {
                boolean textRendererNeedsUpdate = false;
                if (this.k != null) {
                    long subtitleNextEventTimeUs = y();
                    while (subtitleNextEventTimeUs <= positionUs) {
                        this.m++;
                        subtitleNextEventTimeUs = y();
                        textRendererNeedsUpdate = true;
                    }
                }
                if (this.l != null) {
                    if (this.l.c()) {
                        if (!textRendererNeedsUpdate && y() == Long.MAX_VALUE) {
                            if (this.g == 2) {
                                x();
                            } else {
                                v();
                                this.f = true;
                            }
                        }
                    } else if (this.l.a <= positionUs) {
                        if (this.k != null) {
                            this.k.f();
                        }
                        this.k = this.l;
                        this.l = null;
                        this.m = this.k.a(positionUs);
                        textRendererNeedsUpdate = true;
                    }
                }
                if (textRendererNeedsUpdate) {
                    a(this.k.b(positionUs));
                }
                if (this.g != 2) {
                    while (!this.e) {
                        try {
                            if (this.j == null) {
                                this.j = (g) this.i.a();
                                if (this.j == null) {
                                    return;
                                }
                            }
                            if (this.g == 1) {
                                this.j.a_(4);
                                this.i.a(this.j);
                                this.j = null;
                                this.g = 2;
                                return;
                            }
                            int result = a(this.d, this.j, false);
                            if (result == -4) {
                                if (this.j.c()) {
                                    this.e = true;
                                } else {
                                    this.j.d = this.d.a.w;
                                    this.j.h();
                                }
                                this.i.a(this.j);
                                this.j = null;
                            } else if (result == -3) {
                                return;
                            }
                        } catch (Exception e2) {
                            throw ExoPlaybackException.a(e2, r());
                        }
                    }
                }
            }
        }
    }

    protected final void p() {
        this.h = null;
        a(Collections.emptyList());
        w();
    }

    public final boolean u() {
        return this.f;
    }

    public final boolean t() {
        return true;
    }

    private void v() {
        this.j = null;
        this.m = -1;
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
    }

    private void w() {
        v();
        this.i.d();
        this.i = null;
        this.g = 0;
    }

    private void x() {
        w();
        this.i = this.c.b(this.h);
    }

    private long y() {
        if (this.m == -1 || this.m >= this.k.b()) {
            return Long.MAX_VALUE;
        }
        return this.k.a(this.m);
    }

    private void a(List<Cue> cues) {
        if (this.a != null) {
            this.a.obtainMessage(0, cues).sendToTarget();
        }
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
