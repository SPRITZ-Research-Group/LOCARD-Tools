package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;

class Context implements Closeable {
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;

    public Context(EGLDisplay display, int[] eglConfigAttributes) throws GLException {
        this(display, EGL14.EGL_NO_CONTEXT, eglConfigAttributes);
    }

    public Context(Context sharedContext, int[] eglConfigAttributes) throws GLException {
        this(sharedContext.getEGLDisplay(), sharedContext.getEGLContext(), eglConfigAttributes);
    }

    private Context(EGLDisplay soleDisplay, EGLContext eglSharedContext, int[] eglConfigAttributes) throws GLException {
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglDisplay = soleDisplay;
        this.eglConfig = chooseConfig(this.eglDisplay, eglConfigAttributes);
        this.eglContext = createContext(this.eglDisplay, this.eglConfig, eglSharedContext);
    }

    public void close() {
        if (this.eglContext != EGL14.EGL_NO_CONTEXT) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Destroying EGL context");
            }
            EGL14.eglDestroyContext(this.eglDisplay, this.eglContext);
            this.eglContext = EGL14.EGL_NO_CONTEXT;
        }
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglConfig = null;
    }

    public boolean isCurrent() {
        if (this.eglContext != EGL14.EGL_NO_CONTEXT) {
            return this.eglContext.equals(EGL14.eglGetCurrentContext());
        }
        throw new IllegalStateException("closed");
    }

    public EGLContext getEGLContext() {
        if (this.eglContext != EGL14.EGL_NO_CONTEXT) {
            return this.eglContext;
        }
        throw new IllegalStateException("closed");
    }

    public EGLDisplay getEGLDisplay() {
        if (this.eglDisplay != EGL14.EGL_NO_DISPLAY) {
            return this.eglDisplay;
        }
        throw new IllegalStateException("closed");
    }

    public EGLConfig getEGLConfig() {
        if (this.eglConfig != null) {
            return this.eglConfig;
        }
        throw new IllegalStateException("closed");
    }

    private static EGLConfig chooseConfig(EGLDisplay display, int[] attributes) throws GLException {
        EGLConfig[] eglConfigs = new EGLConfig[1];
        int[] numEglConfigs = new int[1];
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Context.class.getSimpleName() + ": Choosing EGL config");
        }
        EGL14.eglChooseConfig(display, attributes, 0, eglConfigs, 0, 1, numEglConfigs, 0);
        EGLUtils.validate("Failed to choose EGL config.");
        return eglConfigs[0];
    }

    private static EGLContext createContext(EGLDisplay display, EGLConfig eglConfig, EGLContext sharedContext) throws GLException {
        int[] attributes = new int[]{12440, 2, 12344};
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Context.class.getSimpleName() + ": Creating EGL context");
        }
        EGLContext context = EGL14.eglCreateContext(display, eglConfig, sharedContext, attributes, 0);
        EGLUtils.validate("Failed to create EGL context.");
        return context;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.eglConfig != null) {
            hashCode = this.eglConfig.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = (hashCode + 31) * 31;
        if (this.eglContext != null) {
            hashCode = this.eglContext.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.eglDisplay != null) {
            i = this.eglDisplay.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Context other = (Context) obj;
        if (this.eglConfig == null) {
            if (other.eglConfig != null) {
                return false;
            }
        } else if (!this.eglConfig.equals(other.eglConfig)) {
            return false;
        }
        if (this.eglContext == null) {
            if (other.eglContext != null) {
                return false;
            }
        } else if (!this.eglContext.equals(other.eglContext)) {
            return false;
        }
        if (this.eglDisplay == null) {
            if (other.eglDisplay != null) {
                return false;
            }
            return true;
        } else if (this.eglDisplay.equals(other.eglDisplay)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [display=" + EGLUtils.idStringOf(this.eglDisplay) + ", context=" + EGLUtils.idStringOf(this.eglContext) + ", config=" + EGLUtils.idStringOf(this.eglConfig) + "]";
    }
}
