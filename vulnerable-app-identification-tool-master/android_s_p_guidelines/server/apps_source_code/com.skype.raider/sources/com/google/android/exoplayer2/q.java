package com.google.android.exoplayer2;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.exoplayer2.a.d;
import com.google.android.exoplayer2.c.h;
import com.google.android.exoplayer2.d.c;
import com.google.android.exoplayer2.video.e;

@TargetApi(16)
public final class q implements d {
    protected final m[] a;
    private final d b;
    private final a c = new a();
    private final int d;
    private final int e;
    private Format f;
    private Format g;
    private Surface h;
    private boolean i;
    private int j;
    private SurfaceHolder k;
    private TextureView l;
    private b m;
    private d n;
    private e o;
    private com.google.android.exoplayer2.decoder.d p;
    private com.google.android.exoplayer2.decoder.d q;
    private int r;
    private int s;
    private float t;

    public interface b {
        void a(int i, int i2);
    }

    private final class a implements Callback, SurfaceTextureListener, d, com.google.android.exoplayer2.metadata.e.a, com.google.android.exoplayer2.text.i.a, e {
        final /* synthetic */ q a;

        private a(q qVar) {
            this.a = qVar;
        }

        /* synthetic */ a(q x0, byte b) {
            this(x0);
        }

        public final void a(com.google.android.exoplayer2.decoder.d counters) {
            this.a.p = counters;
            if (this.a.o != null) {
                this.a.o.a(counters);
            }
        }

        public final void a(Format format) {
            this.a.f = format;
            if (this.a.o != null) {
                this.a.o.a(format);
            }
        }

        public final void a(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            if (this.a.m != null) {
                this.a.m.a(width, height);
            }
            if (this.a.o != null) {
                this.a.o.a(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
            }
        }

        public final void b(com.google.android.exoplayer2.decoder.d counters) {
            if (this.a.o != null) {
                this.a.o.b(counters);
            }
            this.a.f = null;
            this.a.p = null;
        }

        public final void c(com.google.android.exoplayer2.decoder.d counters) {
            this.a.q = counters;
            if (this.a.n != null) {
                this.a.n.c(counters);
            }
        }

        public final void a(int sessionId) {
            this.a.r = sessionId;
            if (this.a.n != null) {
                this.a.n.a(sessionId);
            }
        }

        public final void b(Format format) {
            this.a.g = format;
            if (this.a.n != null) {
                this.a.n.b(format);
            }
        }

        public final void d(com.google.android.exoplayer2.decoder.d counters) {
            if (this.a.n != null) {
                this.a.n.d(counters);
            }
            this.a.g = null;
            this.a.q = null;
            this.a.r = 0;
        }

        public final void surfaceCreated(SurfaceHolder holder) {
            this.a.a(holder.getSurface(), false);
        }

        public final void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        public final void surfaceDestroyed(SurfaceHolder holder) {
            this.a.a(null, false);
        }

        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            this.a.a(new Surface(surfaceTexture), true);
        }

        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        }

        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.a.a(null, true);
            return true;
        }

        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    protected q(p renderersFactory, h trackSelector, j loadControl) {
        this.a = renderersFactory.a(new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()), this.c, this.c, this.c, this.c);
        int videoRendererCount = 0;
        int audioRendererCount = 0;
        for (m a : this.a) {
            switch (a.a()) {
                case 1:
                    audioRendererCount++;
                    break;
                case 2:
                    videoRendererCount++;
                    break;
                default:
                    break;
            }
        }
        this.d = videoRendererCount;
        this.e = audioRendererCount;
        this.t = 1.0f;
        this.r = 0;
        this.s = 3;
        this.j = 1;
        this.b = new f(this.a, trackSelector, loadControl);
    }

    public final void a(Surface surface) {
        j();
        a(surface, false);
    }

    public final void a(float audioVolume) {
        this.t = audioVolume;
        c[] messages = new c[this.e];
        m[] mVarArr = this.a;
        int length = mVarArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            m renderer = mVarArr[i];
            if (renderer.a() == 1) {
                count2 = count + 1;
                messages[count] = new c(renderer, 2, Float.valueOf(audioVolume));
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        this.b.a(messages);
    }

    public final Format h() {
        return this.g;
    }

    public final int i() {
        return this.r;
    }

    public final void a(b listener) {
        this.m = listener;
    }

    public final void a(com.google.android.exoplayer2.d.a listener) {
        this.b.a(listener);
    }

    public final void a(com.google.android.exoplayer2.source.e mediaSource) {
        this.b.a(mediaSource);
    }

    public final void a(boolean playWhenReady) {
        this.b.a(playWhenReady);
    }

    public final boolean a() {
        return this.b.a();
    }

    public final void b() {
        this.b.b();
    }

    public final void a(long positionMs) {
        this.b.a(positionMs);
    }

    public final void c() {
        this.b.c();
    }

    public final void d() {
        this.b.d();
        j();
        if (this.h != null) {
            if (this.i) {
                this.h.release();
            }
            this.h = null;
        }
    }

    public final void a(c... messages) {
        this.b.a(messages);
    }

    public final void b(c... messages) {
        this.b.b(messages);
    }

    public final long e() {
        return this.b.e();
    }

    public final long f() {
        return this.b.f();
    }

    public final int g() {
        return this.b.g();
    }

    private void j() {
        if (this.l != null) {
            if (this.l.getSurfaceTextureListener() == this.c) {
                this.l.setSurfaceTextureListener(null);
            }
            this.l = null;
        }
        if (this.k != null) {
            this.k.removeCallback(this.c);
            this.k = null;
        }
    }

    private void a(Surface surface, boolean ownsSurface) {
        c[] messages = new c[this.d];
        m[] mVarArr = this.a;
        int length = mVarArr.length;
        int i = 0;
        int count = 0;
        while (i < length) {
            int count2;
            m renderer = mVarArr[i];
            if (renderer.a() == 2) {
                count2 = count + 1;
                messages[count] = new c(renderer, 1, surface);
            } else {
                count2 = count;
            }
            i++;
            count = count2;
        }
        if (this.h == null || this.h == surface) {
            this.b.a(messages);
        } else {
            if (this.i) {
                this.h.release();
            }
            this.b.b(messages);
        }
        this.h = surface;
        this.i = ownsSurface;
    }
}
