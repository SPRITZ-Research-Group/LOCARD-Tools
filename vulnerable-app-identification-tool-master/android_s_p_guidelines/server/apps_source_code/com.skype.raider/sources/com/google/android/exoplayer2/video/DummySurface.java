package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Surface;
import com.google.android.exoplayer2.d.s;

@TargetApi(17)
public final class DummySurface extends Surface {
    public static final boolean a;
    public final boolean b;
    private final a c;
    private boolean d;

    private static class a extends HandlerThread implements OnFrameAvailableListener, Callback {
        private final int[] a;
        private Handler b;
        private SurfaceTexture c;
        private Error d;
        private RuntimeException e;
        private DummySurface f;

        public final void a() {
            this.b.sendEmptyMessage(3);
        }

        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.b.sendEmptyMessage(2);
        }

        public final boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    try {
                        boolean z;
                        if (msg.arg1 != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
                        com.google.android.exoplayer2.d.a.b(eglGetDisplay != null, "eglGetDisplay failed");
                        int[] iArr = new int[2];
                        com.google.android.exoplayer2.d.a.b(EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1), "eglInitialize failed");
                        EGLConfig[] eGLConfigArr = new EGLConfig[1];
                        int[] iArr2 = new int[1];
                        boolean z2 = EGL14.eglChooseConfig(eglGetDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr2, 0) && iArr2[0] > 0 && eGLConfigArr[0] != null;
                        com.google.android.exoplayer2.d.a.b(z2, "eglChooseConfig failed");
                        EGLConfig eGLConfig = eGLConfigArr[0];
                        if (z) {
                            iArr = new int[]{12440, 2, 12992, 1, 12344};
                        } else {
                            iArr = new int[]{12440, 2, 12344};
                        }
                        EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
                        if (eglCreateContext != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        com.google.android.exoplayer2.d.a.b(z2, "eglCreateContext failed");
                        if (z) {
                            iArr = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
                        } else {
                            iArr = new int[5];
                            iArr = new int[]{12375, 1, 12374, 1, 12344};
                        }
                        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, iArr, 0);
                        if (eglCreatePbufferSurface != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        com.google.android.exoplayer2.d.a.b(z2, "eglCreatePbufferSurface failed");
                        com.google.android.exoplayer2.d.a.b(EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext), "eglMakeCurrent failed");
                        GLES20.glGenTextures(1, this.a, 0);
                        this.c = new SurfaceTexture(this.a[0]);
                        this.c.setOnFrameAvailableListener(this);
                        this.f = new DummySurface(this, this.c, z, (byte) 0);
                        synchronized (this) {
                            notify();
                        }
                    } catch (RuntimeException e) {
                        this.e = e;
                        synchronized (this) {
                            notify();
                            break;
                        }
                    } catch (Error e2) {
                        this.d = e2;
                        synchronized (this) {
                            notify();
                            break;
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                        }
                    }
                    break;
                case 2:
                    this.c.updateTexImage();
                    break;
                case 3:
                    try {
                        this.c.release();
                        this.f = null;
                        this.c = null;
                        GLES20.glDeleteTextures(1, this.a, 0);
                        quit();
                        break;
                    } catch (Throwable th2) {
                        quit();
                    }
            }
            return true;
        }
    }

    /* synthetic */ DummySurface(a x0, SurfaceTexture x1, boolean x2, byte b) {
        this(x0, x1, x2);
    }

    static {
        boolean z = false;
        if (s.a >= 17) {
            String extensions = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
            if (extensions != null && extensions.contains("EGL_EXT_protected_content")) {
                z = true;
            }
            a = z;
            return;
        }
        a = false;
    }

    private DummySurface(a thread, SurfaceTexture surfaceTexture, boolean secure) {
        super(surfaceTexture);
        this.c = thread;
        this.b = secure;
    }

    public final void release() {
        super.release();
        synchronized (this.c) {
            if (!this.d) {
                this.c.a();
                this.d = true;
            }
        }
    }
}
