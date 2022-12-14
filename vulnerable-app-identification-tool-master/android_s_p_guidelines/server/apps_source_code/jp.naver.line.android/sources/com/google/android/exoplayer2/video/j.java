package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import defpackage.beg;

final class j implements Callback, FrameCallback {
    private static final j b = new j();
    public volatile long a = -9223372036854775807L;
    private final Handler c;
    private final HandlerThread d = new HandlerThread("ChoreographerOwner:Handler");
    private Choreographer e;
    private int f;

    public static j a() {
        return b;
    }

    private j() {
        this.d.start();
        this.c = beg.a(this.d.getLooper(), (Callback) this);
        this.c.sendEmptyMessage(0);
    }

    public final void b() {
        this.c.sendEmptyMessage(1);
    }

    public final void c() {
        this.c.sendEmptyMessage(2);
    }

    public final void doFrame(long j) {
        this.a = j;
        this.e.postFrameCallbackDelayed(this, 500);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.e = Choreographer.getInstance();
                return true;
            case 1:
                this.f++;
                if (this.f == 1) {
                    this.e.postFrameCallback(this);
                }
                return true;
            case 2:
                this.f--;
                if (this.f == 0) {
                    this.e.removeFrameCallback(this);
                    this.a = -9223372036854775807L;
                }
                return true;
            default:
                return false;
        }
    }
}
