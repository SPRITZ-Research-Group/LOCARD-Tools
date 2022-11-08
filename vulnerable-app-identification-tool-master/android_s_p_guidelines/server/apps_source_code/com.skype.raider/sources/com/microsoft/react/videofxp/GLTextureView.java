package com.microsoft.react.videofxp;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;

public class GLTextureView extends TextureView implements Callback, SurfaceTextureListener {
    private final String a;
    private HandlerThread b;
    private Handler c;
    private boolean d;
    private boolean e;
    private volatile boolean f;
    private final Object g;
    private final a h;
    private SurfaceTexture i;
    private e j;
    private j k;
    private int l;
    private int m;

    public interface a {
    }

    private GLTextureView(Context context, byte looper) {
        super(context);
        this.g = new Object();
        this.a = System.identityHashCode(this);
        this.b = new HandlerThread("GLTextureView");
        this.b.start();
        this.c = new Handler(this.b.getLooper(), this);
        this.d = false;
        this.h = null;
        setSurfaceTextureListener(this);
        this.e = true;
    }

    public GLTextureView(Context context) {
        this(context, (byte) 0);
    }

    private String b() {
        return " _renderer " + System.identityHashCode(this.k) + " _surface " + System.identityHashCode(this.j) + " _surfaceTexture " + System.identityHashCode(this.i);
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                if (!(!this.f || this.k == null || this.j == null)) {
                    try {
                        if (this.d) {
                            this.j.a(true);
                        }
                        if (this.k.a()) {
                            this.j.a();
                            break;
                        }
                    } catch (d e) {
                        new StringBuilder("_render exception: ").append(e.getLocalizedMessage());
                        break;
                    }
                }
                break;
            case 2:
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_AVAILABLE enter surfaceTexture ").append(System.identityHashCode(msg.obj)).append(" width ").append(msg.arg1).append(" height ").append(msg.arg2).append(b());
                }
                this.i = (SurfaceTexture) msg.obj;
                this.l = msg.arg1;
                this.m = msg.arg2;
                if (this.k != null) {
                    c();
                    e();
                }
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_AVAILABLE exit").append(b());
                    break;
                }
                break;
            case 3:
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_SIZE_CHANGED surfaceTexture ").append(System.identityHashCode(msg.obj)).append(" width ").append(msg.arg1).append(" height ").append(msg.arg2);
                }
                this.l = msg.arg1;
                this.m = msg.arg2;
                if (this.k != null) {
                    f();
                    d();
                    c();
                    e();
                }
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_SIZE_CHANGED exit").append(b());
                    break;
                }
                break;
            case 4:
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_DESTROYED enter surfaceTexture ").append(System.identityHashCode(msg.obj)).append(b());
                }
                this.i.release();
                this.i = null;
                if (this.j != null) {
                    if (this.k != null) {
                        f();
                    }
                    d();
                }
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_TEXTURE_DESTROYED exit").append(b());
                    break;
                }
                break;
            case 5:
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_SET_RENDERER enter renderer ").append(System.identityHashCode(msg.obj)).append(b());
                }
                j renderer = msg.obj;
                if (renderer != null) {
                    this.f = true;
                    this.k = renderer;
                    if (this.i != null && this.j == null) {
                        c();
                    }
                    if (this.j != null) {
                        e();
                    }
                } else {
                    if (this.j != null) {
                        if (this.k != null) {
                            f();
                        }
                        d();
                    }
                    this.k = null;
                    synchronized (this.g) {
                        this.f = false;
                        this.g.notify();
                    }
                }
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_SET_RENDERER exit renderer ").append(System.identityHashCode(msg.obj)).append(b());
                    break;
                }
                break;
            case 6:
                if (Log.isLoggable(this.a, 3)) {
                    new StringBuilder("handleMessage: MSG_STOP_THREAD").append(b());
                }
                this.b.quit();
                this.b = null;
                break;
            default:
                return false;
        }
        return true;
    }

    private void c() {
        this.j = new c();
        this.j.a(this.i);
        if (!this.d) {
            this.j.a(true);
        }
    }

    private void d() {
        this.j.b(this.e);
        this.j = null;
    }

    private void e() {
        if (this.d) {
            this.j.a(true);
        }
        this.k.b();
    }

    private void f() {
        if (this.d) {
            this.j.a(true);
        }
        this.k.c();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (Log.isLoggable(this.a, 3)) {
            new StringBuilder("onSurfaceTextureAvailable: surfaceTexture ").append(System.identityHashCode(surface)).append(" width ").append(width).append(" height ").append(height);
        }
        this.c.sendMessage(this.c.obtainMessage(2, width, height, surface));
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        if (Log.isLoggable(this.a, 3)) {
            new StringBuilder("onSurfaceTextureSizeChanged: surfaceTexture ").append(System.identityHashCode(surface)).append(" width ").append(width).append(" height ").append(height);
        }
        this.c.sendMessage(this.c.obtainMessage(3, width, height, surface));
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (Log.isLoggable(this.a, 3)) {
            new StringBuilder("onSurfaceTextureDestroyed: surfaceTexture ").append(System.identityHashCode(surface));
        }
        this.c.sendMessage(this.c.obtainMessage(4, surface));
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public final void w_() {
        this.c.removeMessages(1);
        this.c.sendEmptyMessage(1);
    }

    public void setRenderer(j renderer) {
        if (Log.isLoggable(this.a, 3)) {
            new StringBuilder("setRenderer ").append(System.identityHashCode(renderer));
        }
        this.c.sendMessage(this.c.obtainMessage(5, renderer));
    }

    public void setReleaseSurfaceThread(boolean doReleaseSurfaceThread) {
        this.e = doReleaseSurfaceThread;
    }
}
