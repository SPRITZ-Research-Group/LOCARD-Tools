package com.skype.slimcore.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import com.skype.android.video.render.GLTextureView;

public class RNGLTextureView extends GLTextureView {
    private RNSurfaceTextureAvailableListener a;

    public interface RNSurfaceTextureAvailableListener {
        void b();
    }

    public RNGLTextureView(Context context, RNSurfaceTextureAvailableListener listener) {
        super(context, null, false, null);
        setRNSurfaceTextureAvailableListener(listener);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        super.onSurfaceTextureAvailable(surface, width, height);
        if (this.a != null) {
            this.a.b();
        }
    }

    public void setRNSurfaceTextureAvailableListener(RNSurfaceTextureAvailableListener listener) {
        this.a = listener;
        if (isAvailable() && this.a != null) {
            this.a.b();
        }
    }
}
