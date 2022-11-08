package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;
import defpackage.bcz;
import defpackage.beg;

@TargetApi(17)
public final class DummySurface extends Surface {
    private static int b;
    private static boolean c;
    public final boolean a;
    private final b d;
    private boolean e;

    /* synthetic */ DummySurface(b bVar, SurfaceTexture surfaceTexture, boolean z, byte b) {
        this(bVar, surfaceTexture, z);
    }

    public static synchronized boolean a(Context context) {
        synchronized (DummySurface.class) {
            if (!c) {
                int i;
                if (beg.a >= 24 && ((beg.a >= 26 || !("samsung".equals(beg.c) || "XT1650".equals(beg.d))) && (beg.a >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")))) {
                    String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    if (eglQueryString != null && eglQueryString.contains("EGL_EXT_protected_content")) {
                        i = eglQueryString.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
                        b = i;
                        c = true;
                    }
                }
                i = 0;
                b = i;
                c = true;
            }
            if (b != 0) {
                return true;
            }
            return false;
        }
    }

    private DummySurface(b bVar, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.d = bVar;
        this.a = z;
    }

    public final void release() {
        super.release();
        synchronized (this.d) {
            if (!this.e) {
                this.d.a();
                this.e = true;
            }
        }
    }

    public static DummySurface a(Context context, boolean z) {
        if (beg.a >= 17) {
            int i = 0;
            boolean z2 = !z || a(context);
            bcz.b(z2);
            b bVar = new b();
            if (z) {
                i = b;
            }
            return bVar.a(i);
        }
        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }
}
