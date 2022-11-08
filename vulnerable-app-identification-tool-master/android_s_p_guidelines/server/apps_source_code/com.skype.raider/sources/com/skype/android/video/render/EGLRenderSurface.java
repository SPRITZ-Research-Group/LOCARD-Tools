package com.skype.android.video.render;

import android.graphics.SurfaceTexture;

interface EGLRenderSurface {
    void create(SurfaceTexture surfaceTexture);

    void destroy(boolean z);

    void makeCurrent(boolean z);

    void swapBuffers();
}
