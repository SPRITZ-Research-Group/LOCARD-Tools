package com.skypecam.obscura.b;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.util.Pair;
import com.skypecam.obscura.c.b;
import com.skypecam.obscura.c.c;
import com.skypecam.obscura.c.d;
import com.skypecam.obscura.c.e;
import com.skypecam.obscura.c.l;
import com.skypecam.obscura.c.m;
import com.skypecam.obscura.e.f;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.i;
import com.skypecam.obscura.e.j;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class ac {
    private b a = new b(1);
    private d b;
    private int c;
    private final float[] d = new float[16];
    private boolean e = false;
    private SurfaceTexture f;
    private int g;
    private int h;
    private final Map<aa, m> i = new LinkedHashMap();
    private int j;
    private int k;
    private float l = 1.0f;

    private class a implements aa {
        final /* synthetic */ ac a;
        private final SurfaceTexture b;
        private int c;
        private int d;

        a(ac acVar, SurfaceTexture surface, int width, int height) {
            this.a = acVar;
            this.b = surface;
            this.c = width;
            this.d = height;
        }

        public final boolean f() {
            return true;
        }

        public final void g() {
        }

        public final int h() {
            return this.c;
        }

        public final int i() {
            return this.d;
        }

        public final void a(int width, int height) {
            this.c = width;
            this.d = height;
        }

        public final /* bridge */ /* synthetic */ Object k() {
            return this.b;
        }
    }

    public ac(OnFrameAvailableListener listener) {
        this.a.a();
        this.b = new d(new l(com.skypecam.obscura.c.l.a.TEXTURE_EXT));
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        e.a("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(36197, i);
        e.a("glBindTexture " + i);
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        e.a("glTexParameter");
        this.c = i;
        if (this.f == null) {
            this.f = new SurfaceTexture(this.c);
            this.f.setOnFrameAvailableListener(listener);
            return;
        }
        this.f.attachToGLContext(this.c);
    }

    public final void a() {
        this.l = 1.0f;
    }

    public final void a(float multiplier) {
        this.l = i.a(this.l * multiplier, 1.0f, 3.0f);
    }

    public final void a(SurfaceTexture surface, int width, int height) {
        g.a().b("CameraSurfaceApparatus", "setSurfaceTextureDimensions " + width + " " + height);
        for (aa target : this.i.keySet()) {
            if (target.k().equals(surface)) {
                g.a().b("CameraSurfaceApparatus", "setSurfaceTextureDimensions update dimensions");
                target.a(width, height);
            }
        }
    }

    public final void a(int width, int height) {
        g.a().b("CameraSurfaceApparatus", "setPreviewDimensions " + width + " " + height);
        this.g = width;
        this.h = height;
    }

    final void a(aa renderTarget) {
        g.a().b("CameraSurfaceApparatus", "addRenderTarget " + this.i.containsKey(renderTarget));
        if (!this.i.containsKey(renderTarget)) {
            try {
                this.i.put(renderTarget, new m(this.a, renderTarget.k()));
            } catch (Exception e) {
                g.a().a("CameraSurfaceApparatus", "addRenderTarget exception ", e);
            }
        }
    }

    public final void b(aa renderTarget) {
        g.a().b("CameraSurfaceApparatus", "removeRenderTarget " + this.i.containsKey(renderTarget));
        if (this.i.containsKey(renderTarget)) {
            try {
                ((m) this.i.remove(renderTarget)).e();
            } catch (Exception e) {
                g.a().a("CameraSurfaceApparatus", "removeRenderTarget exception ", e);
            }
        }
    }

    public final void b() {
        g.a().b("CameraSurfaceApparatus", "deConfigure");
        while (this.i.entrySet().iterator().hasNext()) {
            b((aa) ((Entry) this.i.entrySet().iterator().next()).getKey());
        }
    }

    public final SurfaceTexture c() {
        return this.f;
    }

    public final int a(s<com.skypecam.obscura.e.d> fileReporter) {
        int drawn = 0;
        try {
            if (this.i.isEmpty()) {
                this.a.a();
            } else {
                ((m) ((Entry) this.i.entrySet().iterator().next()).getValue()).b();
            }
            this.f.updateTexImage();
            for (Entry<aa, m> targetWithSurface : this.i.entrySet()) {
                try {
                    aa target = (aa) targetWithSurface.getKey();
                    m surface = (m) targetWithSurface.getValue();
                    surface.b();
                    int viewWidth = target.h();
                    int viewHeight = target.i();
                    if (this.e) {
                        g.a().b("CameraSurfaceApparatus", "drawFrame releaseFbo ");
                        this.b.a();
                        this.e = false;
                    }
                    GLES20.glViewport(0, 0, viewWidth, viewHeight);
                    this.f.getTransformMatrix(this.d);
                    float[] fArr = this.d;
                    int i = this.g;
                    int i2 = this.h;
                    i.a(fArr, (float) i, (float) i2, (float) viewWidth, (float) viewHeight, this.k, this.l);
                    GLES20.glViewport(0, 0, viewWidth, viewHeight);
                    this.b.a(this.c, this.d);
                    if (target.f() && fileReporter != null) {
                        g.a().b("CameraSurfaceApparatus", "doSaveFrame");
                        try {
                            final Pair d = surface.d();
                            final s<com.skypecam.obscura.e.d> sVar = fileReporter;
                            Runnable anonymousClass1 = new Runnable(this) {
                                final /* synthetic */ ac c;

                                public final void run() {
                                    try {
                                        j size = d.second;
                                        g.a().b("CameraSurfaceApparatus", "doSaveFrame completion");
                                        int rotation = -1;
                                        if (this.c.j != this.c.k) {
                                            rotation = this.c.j;
                                        }
                                        File file = c.a(size.a(), size.b(), rotation, (ByteBuffer) d.first);
                                        g.a().b("CameraSurfaceApparatus", "doSaveFrame completion: reporting file");
                                        sVar.a(new com.skypecam.obscura.e.d(file));
                                    } catch (IOException e) {
                                        g.a().d("CameraSurfaceApparatus", "doSaveFrame saveBufferToFile exception: " + e.getLocalizedMessage());
                                    }
                                }
                            };
                            if (fileReporter.a()) {
                                g.a().b("CameraSurfaceApparatus", "doSaveFrame schedule");
                                f.b().execute(anonymousClass1);
                            } else {
                                anonymousClass1.run();
                            }
                        } catch (IOException e) {
                            g.a().d("CameraSurfaceApparatus", "doSaveFrame exception: " + e.getLocalizedMessage());
                        }
                    }
                    surface.c();
                    this.f.getTimestamp();
                    target.g();
                    drawn++;
                } catch (com.skypecam.obscura.c.f ge) {
                    g.a().a("CameraSurfaceApparatus", "drawFrame: graphics exception ", ge);
                    if (ge.a() == com.skypecam.obscura.d.b.OUT_OF_MEMORY) {
                        g.a().d("CameraSurfaceApparatus", "drawFrame: out of memory ");
                        h.e().a(com.skypecam.obscura.d.b.OUT_OF_MEMORY);
                    }
                } catch (Exception e2) {
                    g.a().a("CameraSurfaceApparatus", "drawFrame: render loop exception ", e2);
                }
            }
            return drawn;
        } catch (Exception e22) {
            g.a().a("CameraSurfaceApparatus", "drawFrame: makeCurrent/updateTexImage exception ", e22);
            return 0;
        }
    }

    public final void b(int deviceRotation, int displayRotation) {
        this.j = deviceRotation;
        this.k = displayRotation;
    }
}
