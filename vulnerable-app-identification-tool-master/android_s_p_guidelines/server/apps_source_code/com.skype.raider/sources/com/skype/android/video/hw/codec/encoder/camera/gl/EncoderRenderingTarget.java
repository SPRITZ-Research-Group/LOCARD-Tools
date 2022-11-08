package com.skype.android.video.hw.codec.encoder.camera.gl;

import com.skype.android.video.hw.codec.encoder.camera.gl.AbstractRenderingTarget.Events;

public class EncoderRenderingTarget extends AbstractRenderingTarget {
    private static final int[] EGL_CONTEXT_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, EGL_RECORDABLE_ANDROID, 1, 12344};
    private static final int EGL_RECORDABLE_ANDROID = 12610;

    public EncoderRenderingTarget(Context sharedContext, Events eventsListener) throws GLException {
        super(sharedContext, EGL_CONTEXT_ATTRIBUTES, eventsListener);
    }

    protected void doInitialBinding() throws GLException {
        if (!isBoundAny()) {
            bind();
        }
    }
}
