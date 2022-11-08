package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.WindowManager;

@TargetApi(16)
public final class d {
    private final a a;
    private final boolean b;
    private final long c;
    private final long d;
    private long e;
    private long f;
    private long g;
    private boolean h;
    private long i;
    private long j;
    private long k;

    private static final class a implements Callback, FrameCallback {
        private static final a b = new a();
        public volatile long a;
        private final Handler c;
        private final HandlerThread d = new HandlerThread("ChoreographerOwner:Handler");
        private Choreographer e;
        private int f;

        public static a a() {
            return b;
        }

        private a() {
            this.d.start();
            this.c = new Handler(this.d.getLooper(), this);
            this.c.sendEmptyMessage(0);
        }

        public final void b() {
            this.c.sendEmptyMessage(1);
        }

        public final void c() {
            this.c.sendEmptyMessage(2);
        }

        public final void doFrame(long vsyncTimeNs) {
            this.a = vsyncTimeNs;
            this.e.postFrameCallbackDelayed(this, 500);
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.e = Choreographer.getInstance();
                    return true;
                case 1:
                    this.f++;
                    if (this.f != 1) {
                        return true;
                    }
                    this.e.postFrameCallback(this);
                    return true;
                case 2:
                    this.f--;
                    if (this.f != 0) {
                        return true;
                    }
                    this.e.removeFrameCallback(this);
                    this.a = 0;
                    return true;
                default:
                    return false;
            }
        }
    }

    public d() {
        this(-1.0d, false);
    }

    private d(double defaultDisplayRefreshRate, boolean useDefaultDisplayVsync) {
        this.b = useDefaultDisplayVsync;
        if (useDefaultDisplayVsync) {
            this.a = a.a();
            this.c = (long) (1.0E9d / defaultDisplayRefreshRate);
            this.d = (this.c * 80) / 100;
            return;
        }
        this.a = null;
        this.c = -1;
        this.d = -1;
    }

    public final void a() {
        this.h = false;
        if (this.b) {
            this.a.b();
        }
    }

    public final void b() {
        if (this.b) {
            this.a.c();
        }
    }

    public final long a(long framePresentationTimeUs, long unadjustedReleaseTimeNs) {
        long framePresentationTimeNs = framePresentationTimeUs * 1000;
        long adjustedFrameTimeNs = framePresentationTimeNs;
        long adjustedReleaseTimeNs = unadjustedReleaseTimeNs;
        if (this.h) {
            if (framePresentationTimeUs != this.e) {
                this.k++;
                this.f = this.g;
            }
            if (this.k >= 6) {
                long candidateAdjustedFrameTimeNs = this.f + ((framePresentationTimeNs - this.j) / this.k);
                if (b(candidateAdjustedFrameTimeNs, unadjustedReleaseTimeNs)) {
                    this.h = false;
                } else {
                    adjustedFrameTimeNs = candidateAdjustedFrameTimeNs;
                    adjustedReleaseTimeNs = (this.i + adjustedFrameTimeNs) - this.j;
                }
            } else if (b(framePresentationTimeNs, unadjustedReleaseTimeNs)) {
                this.h = false;
            }
        }
        if (!this.h) {
            this.j = framePresentationTimeNs;
            this.i = unadjustedReleaseTimeNs;
            this.k = 0;
            this.h = true;
        }
        this.e = framePresentationTimeUs;
        this.g = adjustedFrameTimeNs;
        if (this.a == null || this.a.a == 0) {
            return adjustedReleaseTimeNs;
        }
        long j = this.a.a;
        long j2 = this.c;
        j += ((adjustedReleaseTimeNs - j) / j2) * j2;
        if (adjustedReleaseTimeNs <= j) {
            j2 = j - j2;
        } else {
            long j3 = j2 + j;
            j2 = j;
            j = j3;
        }
        if (j - adjustedReleaseTimeNs >= adjustedReleaseTimeNs - j2) {
            j = j2;
        }
        return j - this.d;
    }

    private boolean b(long frameTimeNs, long releaseTimeNs) {
        return Math.abs((releaseTimeNs - this.i) - (frameTimeNs - this.j)) > 20000000;
    }

    public d(Context context) {
        this((double) ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate(), true);
    }
}
