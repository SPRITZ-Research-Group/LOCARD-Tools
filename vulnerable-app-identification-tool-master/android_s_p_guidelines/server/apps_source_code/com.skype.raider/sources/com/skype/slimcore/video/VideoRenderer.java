package com.skype.slimcore.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.skype.VideoImpl;
import com.skype.android.video.render.BindingRenderer;
import com.skype.android.video.render.GLESBindingRenderer;
import com.skype.android.video.render.GLTextureView;
import com.skype.slimcore.video.RNGLTextureView.RNSurfaceTextureAvailableListener;
import java.util.Locale;

public class VideoRenderer implements com.skype.android.video.render.BindingRenderer.Callback {
    private final Object a = new Object();
    private Callback b;
    @Nullable
    private RNGLTextureView c;
    @Nullable
    private BindingRenderer d;
    @Nullable
    private VideoImpl e;
    @Nullable
    private VideoImpl f;
    @Nullable
    private VideoImpl g;
    private boolean h;
    private boolean i;
    private long j;
    private int k;
    private int l;
    private int m;

    public interface Callback {
        void a();

        void a(int i, int i2);

        void b();

        void c();

        void d();

        void e();
    }

    public VideoRenderer(Callback cb, int videoObjectId) {
        this.b = cb;
        this.m = videoObjectId;
    }

    public final void a(Callback cb) {
        this.b = cb;
        if (this.j != 0) {
            this.b.a();
        }
    }

    private String c() {
        return String.format(Locale.US, "%s[%d:%d:%b]", new Object[]{"VideoRenderer", Integer.valueOf(this.m), Long.valueOf(this.j), Boolean.valueOf(this.h)});
    }

    public final GLTextureView a(Context context, RNSurfaceTextureAvailableListener listener) {
        GLTextureView gLTextureView;
        synchronized (this.a) {
            if (this.c == null) {
                this.c = new RNGLTextureView(context, listener);
                FLog.i(c(), "createView create new view %d", Integer.valueOf(System.identityHashCode(this.c)));
            }
            FLog.i(c(), "createView view %d", Integer.valueOf(System.identityHashCode(this.c)));
            this.h = true;
            gLTextureView = this.c;
        }
        return gLTextureView;
    }

    @Nullable
    public final RNGLTextureView a() {
        return this.c;
    }

    public final void b() {
        synchronized (this.a) {
            FLog.i(c(), "releaseView view %d", Integer.valueOf(System.identityHashCode(this.c)));
            this.h = false;
            d();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(VideoImpl video) {
        boolean z = false;
        synchronized (this.a) {
            if (this.h) {
                FLog.i(c(), "startVideo videoObjId %d view %d", Integer.valueOf(video.getObjectID()), Integer.valueOf(System.identityHashCode(this.c)));
                if (this.e == null) {
                    if (this.d == null) {
                        FLog.i(c(), "startVideo create new bindingRenderer for view %d", Integer.valueOf(System.identityHashCode(this.c)));
                        this.d = new GLESBindingRenderer(this);
                        this.k = this.d.getNativeBindingType();
                        this.l = (int) this.d.getNativeBindingEvent();
                    }
                    this.e = video;
                    if (this.j != 0) {
                        if (this.f == video) {
                            this.f = null;
                        } else {
                            this.g = video;
                        }
                    } else if (this.f == video) {
                        this.f = null;
                    } else {
                        this.g = video;
                        if (this.f == null) {
                            e();
                        }
                    }
                } else if (this.e.getObjectID() == video.getObjectID()) {
                    z = true;
                }
            } else {
                FLog.i(c(), "startVideo videoObjId %d called with no view attached", Integer.valueOf(video.getObjectID()));
                return false;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean b(VideoImpl video) {
        synchronized (this.a) {
            boolean isOurVideo;
            if (this.d == null || this.e == null || this.e.getObjectID() != video.getObjectID()) {
                isOurVideo = false;
            } else {
                isOurVideo = true;
            }
            FLog.i(c(), "stopVideo videoObjId %d view %d will stop %b", Integer.valueOf(video.getObjectID()), Integer.valueOf(System.identityHashCode(this.c)), Boolean.valueOf(isOurVideo));
            if (isOurVideo) {
                this.e = null;
                if (this.i) {
                    d();
                    return true;
                } else if (this.j == 0) {
                    if (this.g == video) {
                        this.g = null;
                    } else {
                        this.f = video;
                    }
                } else if (this.g == video) {
                    this.g = null;
                } else {
                    this.f = video;
                    if (this.g == null) {
                        f();
                    }
                }
            } else {
                return false;
            }
        }
    }

    public final Bitmap a(int causeId) {
        Bitmap bitmap;
        synchronized (this.a) {
            if (this.d == null) {
                FLog.e("VideoRenderer", "captureFrame: no GLES renderer, causeId %x", Integer.valueOf(causeId));
                bitmap = null;
            } else {
                bitmap = this.d.captureFrame();
            }
        }
        return bitmap;
    }

    public void onBindingCreated(long bindingRef) {
        synchronized (this.a) {
            FLog.i(c(), "onBindingCreated binding %d view %d", Long.valueOf(bindingRef), Integer.valueOf(System.identityHashCode(this.c)));
            this.j = bindingRef;
            f();
            this.b.a();
        }
    }

    public void onBindingReleased() {
        synchronized (this.a) {
            FLog.i(c(), "onBindingReleased binding %d view %d", Long.valueOf(this.j), Integer.valueOf(System.identityHashCode(this.c)));
            this.j = 0;
            e();
            d();
            this.b.b();
        }
    }

    public void onBindingFailed() {
        synchronized (this.a) {
            FLog.i(c(), "onBindingFailed view %d", Integer.valueOf(System.identityHashCode(this.c)));
            this.f = null;
            if (!e()) {
                this.i = true;
            }
            d();
            this.b.c();
        }
    }

    public void onSizeChanged(int w, int h) {
        FLog.i(c(), "onSizeChanged wxh: %d x %d view %d", Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(System.identityHashCode(this.c)));
        this.b.a(w, h);
    }

    public void onFirstFrameRendered() {
        FLog.i(c(), "onFirstFrameRendered view %d", Integer.valueOf(System.identityHashCode(this.c)));
        this.b.d();
    }

    private void d() {
        boolean z = (this.j == 0 && this.e == null && this.g == null && this.f == null) ? false : true;
        if (!(z || this.d == null)) {
            FLog.i(c(), "dispose bindingRenderer for view %d", Integer.valueOf(System.identityHashCode(this.c)));
            this.d.dispose();
            this.d = null;
            this.i = false;
            this.k = 0;
            this.l = 0;
        }
        if (!this.h && this.d == null) {
            FLog.i(c(), "dispose view %d", Integer.valueOf(System.identityHashCode(this.c)));
            this.b.e();
            if (this.c != null) {
                this.c.dispose();
                this.c = null;
            }
        }
    }

    private boolean e() {
        if (this.g == null) {
            return false;
        }
        FLog.i(c(), "RegisterViewAndCreateBinding videoObjId %d view %d", Integer.valueOf(this.g.getObjectID()), Integer.valueOf(System.identityHashCode(this.c)));
        if (this.d != null) {
            this.d.registerView(this.c);
        }
        this.g.createBinding(this.k, this.l);
        this.g = null;
        return true;
    }

    private boolean f() {
        if (this.f == null) {
            return false;
        }
        FLog.i(c(), "UnregisterViewAndReleaseBinding binding %d videoObjId %d view %d", Long.valueOf(this.j), Integer.valueOf(this.f.getObjectID()), Integer.valueOf(System.identityHashCode(this.c)));
        if (this.d != null) {
            this.d.unregisterView(this.c);
        }
        this.f.releaseBinding((int) this.j);
        this.f = null;
        return true;
    }
}
