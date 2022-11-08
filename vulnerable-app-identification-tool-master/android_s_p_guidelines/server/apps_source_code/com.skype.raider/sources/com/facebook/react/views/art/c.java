package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;
import javax.annotation.Nullable;

public class c extends h implements SurfaceTextureListener {
    @Nullable
    private Surface a;
    private boolean b;
    private int c;

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer color) {
        this.b = color != null;
        if (this.b) {
            this.c = color.intValue();
        }
        i();
    }

    public final boolean a() {
        return false;
    }

    public final boolean b() {
        return true;
    }

    public final void a(al uiUpdater) {
        super.a(uiUpdater);
        c();
        uiUpdater.a(A(), (Object) this);
    }

    private void c() {
        RuntimeException e;
        if (this.a == null || !this.a.isValid()) {
            g(this);
            return;
        }
        try {
            Canvas canvas = this.a.lockCanvas(null);
            canvas.drawColor(0, Mode.CLEAR);
            if (this.b) {
                canvas.drawColor(this.c);
            }
            Paint paint = new Paint();
            for (int i = 0; i < y(); i++) {
                e child = (e) c(i);
                child.a(canvas, paint, 1.0f);
                child.v();
            }
            if (this.a != null) {
                this.a.unlockCanvasAndPost(canvas);
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            FLog.e("React", e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        } catch (IllegalStateException e3) {
            e = e3;
            FLog.e("React", e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    private void g(w shadowNode) {
        for (int i = 0; i < shadowNode.y(); i++) {
            w child = shadowNode.c(i);
            child.v();
            g(child);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.a = new Surface(surface);
        c();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        surface.release();
        this.a = null;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
