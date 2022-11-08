package com.microsoft.dl.video.graphics.egl;

import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.utils.Resolution;

public interface TargetSurfaceContext {
    void close() throws EGLException;

    Resolution getSurfaceSize() throws EGLException;

    boolean isCurrent();

    void makeCurrent(boolean z) throws EGLException;

    void swapBuffers() throws GraphicsException;
}
