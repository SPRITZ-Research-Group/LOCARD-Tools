package com.skpcamera.antediluvian;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;

public class SkypeCameraView extends ViewGroup implements com.skpcamera.antediluvian.u.a {
    private final Context a;
    private u b = null;
    private int c = -1;
    private a d = null;

    public interface a {
        void a(SkypeCameraView skypeCameraView, int i);
    }

    public SkypeCameraView(Context context, a callback) {
        super(context);
        this.a = context;
        this.d = callback;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int causeId = t.c();
        FLog.i("SkypeCameraView", "onLayout with causeId " + causeId);
        a(left, top, right, bottom, causeId);
    }

    public void setCameraType(int type, int causeId) {
        if (this.b != null) {
            this.b.a(type, this, causeId);
            return;
        }
        this.b = new u(this.a, type, this, causeId);
        addView(this.b, 0);
    }

    private void b(int causeId) {
        a(getLeft(), getTop(), getRight(), getBottom(), causeId);
    }

    private void a(int left, int top, int right, int bottom, int causeId) {
        if (this.b != null) {
            int viewfinderHeight;
            int viewfinderWidth;
            FLog.i("SkypeCameraView", "layout viewfinder with " + left + " " + right + " " + top + " " + bottom + " (causeId " + causeId + ")");
            float width = (float) (right - left);
            float height = (float) (bottom - top);
            t instance = t.b();
            double ratio = (double) (((float) instance.d()) / ((float) instance.e()));
            if (((double) height) * ratio < ((double) width)) {
                viewfinderHeight = (int) (((double) width) / ratio);
                viewfinderWidth = (int) width;
            } else {
                viewfinderWidth = (int) (((double) height) * ratio);
                viewfinderHeight = (int) height;
            }
            int viewFinderPaddingX = (int) ((width - ((float) viewfinderWidth)) / 2.0f);
            int viewFinderPaddingY = (int) ((height - ((float) viewfinderHeight)) / 2.0f);
            this.b.layout(viewFinderPaddingX, viewFinderPaddingY, viewFinderPaddingX + viewfinderWidth, viewFinderPaddingY + viewfinderHeight);
            postInvalidate(getLeft(), getTop(), getRight(), getBottom());
            FLog.i("SkypeCameraView", "layout setCropPadding " + viewFinderPaddingX + " " + viewFinderPaddingY + " (causeId " + causeId + ")");
            this.b.a(-viewFinderPaddingX, -viewFinderPaddingY);
        }
    }

    public final void a(int type, int causeId) {
        FLog.i("SkypeCameraView", "on camera type changed (causeId " + causeId + ")");
        b(causeId);
        if (this.d != null) {
            this.d.a(this, type);
        }
    }

    public final void a(int causeId) {
        FLog.i("SkypeCameraView", "on camera size changed (causeId " + causeId + ")");
        b(causeId);
    }
}
