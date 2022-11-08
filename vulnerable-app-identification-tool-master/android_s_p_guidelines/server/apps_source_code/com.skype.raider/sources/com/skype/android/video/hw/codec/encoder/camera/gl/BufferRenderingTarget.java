package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.EGL14;
import android.opengl.EGLSurface;
import com.skype.android.video.hw.codec.encoder.camera.gl.AbstractRenderingTarget.Events;
import com.skype.android.video.hw.format.Resolution;

public class BufferRenderingTarget extends AbstractRenderingTarget {
    private static final int[] EGL_CONTEXT_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12339, 1, 12344};

    public BufferRenderingTarget(Context sharedContext, Events eventsListener) throws GLException {
        super(sharedContext, EGL_CONTEXT_ATTRIBUTES, eventsListener);
    }

    protected EGLSurface doCreateEGLSurface(Object surface, Resolution resolution) {
        if (surface != null) {
            throw new IllegalArgumentException("surfce is not supported by " + getClass().getCanonicalName());
        }
        return EGL14.eglCreatePbufferSurface(this.context.getEGLDisplay(), this.context.getEGLConfig(), new int[]{12375, resolution.getWidth(), 12374, resolution.getHeight(), 12344}, 0);
    }

    protected void doInitialBinding() throws GLException {
        bind();
    }
}
