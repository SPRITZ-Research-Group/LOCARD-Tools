package com.skype.slimcore.video;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import com.skype.VideoImpl;
import com.skype.android.video.capture.UiPreviewBinding;
import com.skype.android.video.capture.UiPreviewBinding.BindingParams;
import com.skype.android.video.capture.UiPreviewBinding.Callback;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PreviewRenderer implements Callback, IPreviewRenderer {
    private IPreviewRenderer.Callback a;
    private UiPreviewBinding b;
    private AtomicBoolean c;
    private final Object d = new Object();
    private SurfaceTexture e;

    public PreviewRenderer(IPreviewRenderer.Callback callback) {
        this.a = callback;
        this.c = new AtomicBoolean(false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(VideoImpl video) {
        FLog.i("PreviewRenderer", "startPreview videoObjId %d", Integer.valueOf(video.getObjectID()));
        synchronized (this.d) {
            if (this.c.compareAndSet(false, true)) {
                if (this.b == null) {
                    this.b = new UiPreviewBinding(new Handler(Looper.getMainLooper()), this);
                }
                BindingParams params = this.b.setup();
                video.createBinding(params.type, (int) params.event);
                if (this.e != null) {
                    this.b.setSurface(this.e);
                }
            } else {
                FLog.i("PreviewRenderer", "Not creating binding, already created");
                return false;
            }
        }
    }

    public final boolean b(VideoImpl video) {
        FLog.i("PreviewRenderer", "stopPreview videoObjId %d", Integer.valueOf(video.getObjectID()));
        synchronized (this.d) {
            if (this.c.compareAndSet(true, false)) {
                BindingParams params = this.b.destroy();
                video.releaseBindingEx(params.type, (int) params.event);
                this.b = null;
                return true;
            }
            FLog.w("PreviewRenderer", "Trying to stopPreview, binding wasn't created: ", Integer.valueOf(video.getObjectID()));
            return false;
        }
    }

    public final void a(SurfaceTexture surfaceTexture) {
        FLog.i("PreviewRenderer", "attachSurface: " + System.identityHashCode(surfaceTexture));
        synchronized (this.d) {
            this.e = surfaceTexture;
            if (this.b != null) {
                this.b.setSurface(this.e);
            }
        }
    }

    public final void b(SurfaceTexture surfaceTexture) {
        FLog.i("PreviewRenderer", "detachSurface " + System.identityHashCode(surfaceTexture));
        synchronized (this.d) {
            this.e = null;
            if (this.b != null) {
                this.b.setSurface(this.e);
            }
        }
    }

    public final void onFrameSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        FLog.i("PreviewRenderer", "onFrameSizeChanged width: " + width + " height: " + height + " surfaceTexture " + System.identityHashCode(surfaceTexture));
        this.a.a(width, height);
    }

    public final void onPreviewSurfaceUnset(SurfaceTexture surfaceTexture) {
        FLog.i("PreviewRenderer", "onPreviewSurfaceUnset " + System.identityHashCode(surfaceTexture));
    }
}
