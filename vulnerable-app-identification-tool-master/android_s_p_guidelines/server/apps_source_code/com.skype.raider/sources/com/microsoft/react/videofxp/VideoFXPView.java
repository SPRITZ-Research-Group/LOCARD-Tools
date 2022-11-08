package com.microsoft.react.videofxp;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.v;
import com.facebook.react.uimanager.ae;
import java.util.concurrent.atomic.AtomicBoolean;

public class VideoFXPView extends GLTextureView implements OnFrameAvailableListener, v, j {
    private AtomicBoolean a = new AtomicBoolean();
    private k b;
    private SurfaceTexture c;
    private String d;
    private boolean e = false;
    private boolean f = true;
    private MediaPlayer g;
    private boolean h = false;

    public VideoFXPView(ae context) {
        super(context);
        FLog.i(VideoFXPViewManager.REACT_CLASS, VideoFXPViewManager.REACT_CLASS);
        this.b = new k();
        this.b.a(0, 1.0f);
        this.b.b(0, 1.0f);
        this.b.a(1.0f);
        if (!this.a.getAndSet(true)) {
            FLog.i(VideoFXPViewManager.REACT_CLASS, "addLifecycleEventListener");
            context.a(this);
        }
    }

    public void setLensModeOffsetX(float x) {
        this.b.a(x);
    }

    public void setLensModeLeft(String shader, float intensity) {
        this.b.a(k.a(shader), intensity);
    }

    public void setLensModeRight(String shader, float intensity) {
        this.b.b(k.a(shader), intensity);
    }

    public final int d() {
        return this.b.b();
    }

    public final float e() {
        return this.b.c();
    }

    public final String f() {
        return this.d;
    }

    public void setSource(String source) {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "setSource %s %b", (Object) source, Boolean.valueOf(this.e));
        this.d = source;
        if (this.e) {
            FLog.i(VideoFXPViewManager.REACT_CLASS, "setSource: isAttached");
            setRenderer(this);
        }
    }

    public synchronized void onFrameAvailable(SurfaceTexture surface) {
        w_();
    }

    public final boolean a() {
        this.c.updateTexImage();
        if (this.f) {
            this.b.a(this.c);
            return true;
        }
        FLog.w(VideoFXPViewManager.REACT_CLASS, "render isVisible:false");
        return false;
    }

    public final void b() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "attach");
        this.g = new MediaPlayer();
        this.g.setLooping(true);
        try {
            this.g.setDataSource(this.d);
        } catch (Exception e) {
            FLog.e(VideoFXPViewManager.REACT_CLASS, "Could not set data source: " + e.getLocalizedMessage());
        }
        this.b.d();
        FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: new SurfaceTexture");
        this.c = new SurfaceTexture(this.b.a());
        an.a(this.c != null, "Surface texture should not be null!");
        FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: new Surface");
        Surface surface = new Surface(this.c);
        an.a(true, "Surface should not be null!");
        FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: setSurface");
        this.g.setSurface(surface);
        FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: surface release");
        surface.release();
        try {
            FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: prepare");
            this.g.prepare();
            FLog.i(VideoFXPViewManager.REACT_CLASS, "attach: start");
            this.g.start();
            this.h = true;
            this.c.setOnFrameAvailableListener(this);
        } catch (Exception t) {
            FLog.e(VideoFXPViewManager.REACT_CLASS, "media player prepare failed: " + t.getLocalizedMessage());
        }
        post(new Runnable(this) {
            final /* synthetic */ VideoFXPView a;

            {
                this.a = this$0;
            }

            public final void run() {
                if (this.a.e && this.a.h) {
                    FLog.i(VideoFXPViewManager.REACT_CLASS, "handler scaling view");
                    TextureView textureView = this.a;
                    int videoWidth = this.a.g.getVideoWidth();
                    int videoHeight = this.a.g.getVideoHeight();
                    if (textureView == null || !textureView.isAvailable()) {
                        FLog.w(VideoFXPViewManager.REACT_CLASS, "view cannot be scaled");
                        return;
                    }
                    float width = ((float) textureView.getWidth()) / ((float) videoWidth);
                    float height = ((float) textureView.getHeight()) / ((float) videoHeight);
                    Matrix transform = textureView.getTransform(null);
                    width = Math.min(width, height);
                    videoWidth = (int) (((float) videoWidth) * width);
                    videoHeight = (int) (((float) videoHeight) * width);
                    transform.setScale(((float) videoWidth) / ((float) textureView.getWidth()), ((float) videoHeight) / ((float) textureView.getHeight()));
                    transform.postTranslate((float) ((textureView.getWidth() - videoWidth) / 2), (float) ((textureView.getHeight() - videoHeight) / 2));
                    FLog.i(VideoFXPViewManager.REACT_CLASS, "setting transform");
                    textureView.setTransform(transform);
                }
            }
        });
    }

    public final void c() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "detach");
        this.g.stop();
        this.g.release();
        this.c.release();
        this.h = false;
    }

    public void onDetachedFromWindow() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "onDetachedFromWindow %b", Boolean.valueOf(this.h));
        this.e = false;
        this.f = false;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "onAttachedToWindow");
        this.e = true;
        this.f = true;
        if (this.d != null) {
            FLog.i(VideoFXPViewManager.REACT_CLASS, "onAttachedToWindow: has source");
            setRenderer(this);
        }
        super.onAttachedToWindow();
    }

    public void onHostResume() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "onHostResume");
        if (this.h && !this.f) {
            this.g.start();
        }
        this.f = true;
        w_();
    }

    public void onHostPause() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "onHostPause");
        if (this.h) {
            this.g.pause();
        }
        this.f = false;
    }

    public void onHostDestroy() {
        FLog.i(VideoFXPViewManager.REACT_CLASS, "onHostDestroy");
        this.f = false;
        ae context = (ae) getContext();
        if (this.a.get()) {
            context.b(this);
        }
    }
}
