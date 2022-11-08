package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.facebook.react.uimanager.al;
import com.facebook.react.uimanager.w;
import com.facebook.react.views.art.e;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import javax.annotation.Nullable;

class o extends r implements SurfaceTextureListener {
    private boolean d = false;
    @Nullable
    private Surface f;

    o() {
        j();
        c();
    }

    public final boolean a() {
        return false;
    }

    public final boolean b() {
        return true;
    }

    public final void a(al uiUpdater) {
        super.a(uiUpdater);
        l();
        uiUpdater.a(A(), (Object) this);
    }

    private void l() {
        RuntimeException e;
        if (this.f == null || !this.f.isValid()) {
            g(this);
            return;
        }
        try {
            Canvas canvas = this.f.lockCanvas(null);
            canvas.drawColor(0, Mode.CLEAR);
            Paint paint = new Paint();
            for (int i = 0; i < y(); i++) {
                e child = (e) c(i);
                child.a(canvas, paint, 1.0f);
                child.v();
            }
            if (this.f != null) {
                this.f.unlockCanvasAndPost(canvas);
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            new StringBuilder().append(e.getClass().getSimpleName()).append(" in Surface.unlockCanvasAndPost");
        } catch (IllegalStateException e3) {
            e = e3;
            new StringBuilder().append(e.getClass().getSimpleName()).append(" in Surface.unlockCanvasAndPost");
        }
    }

    private void g(w shadowNode) {
        for (int i = 0; i < shadowNode.y(); i++) {
            w child = shadowNode.c(i);
            child.v();
            g(child);
        }
    }

    public final void a(int spacingType, float padding) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.POINT || current.value != padding) {
            super.a(spacingType, padding);
            this.d = true;
            i();
        }
    }

    public final void b(int spacingType, float percent) {
        YogaValue current = h(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.a(spacingType, percent);
            this.d = true;
            i();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.f = new Surface(surface);
        l();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        surface.release();
        this.f = null;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
