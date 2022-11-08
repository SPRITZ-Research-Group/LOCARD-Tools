package com.facebook.react.modules.core;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

public final class a {
    private static final boolean a = (VERSION.SDK_INT >= 16);
    private static final a b = new a();
    private Handler c;
    private Choreographer d;

    public static abstract class a {
        private Runnable a;
        private FrameCallback b;

        public abstract void b(long j);

        @TargetApi(16)
        final FrameCallback a() {
            if (this.b == null) {
                this.b = new FrameCallback(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = this$0;
                    }

                    public final void doFrame(long frameTimeNanos) {
                        this.a.b(frameTimeNanos);
                    }
                };
            }
            return this.b;
        }

        final Runnable b() {
            if (this.a == null) {
                this.a = new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = this$0;
                    }

                    public final void run() {
                        this.a.b(System.nanoTime());
                    }
                };
            }
            return this.a;
        }
    }

    public static a a() {
        return b;
    }

    private a() {
        if (a) {
            this.d = Choreographer.getInstance();
        } else {
            this.c = new Handler(Looper.getMainLooper());
        }
    }

    public final void a(a callbackWrapper) {
        if (a) {
            this.d.postFrameCallback(callbackWrapper.a());
            return;
        }
        this.c.postDelayed(callbackWrapper.b(), 0);
    }

    public final void b(a callbackWrapper) {
        if (a) {
            this.d.removeFrameCallback(callbackWrapper.a());
            return;
        }
        this.c.removeCallbacks(callbackWrapper.b());
    }
}
