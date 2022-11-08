package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;

public abstract class AbstractRenderingTarget implements Closeable {
    protected Context context;
    private long delayedTimestamp;
    private Events eventsListener;
    private float fitFactor;
    private boolean isEnabled;
    private boolean isHorizFlipped;
    private boolean isVertFlipped;
    private int rotationAngle;
    private float targetFrameRate;
    private TargetSurface targetSurface;

    public interface Events {
        void onFrameRendered(long j);
    }

    private class TargetSurface implements Closeable {
        private EGLSurface eglSurface = EGL14.EGL_NO_SURFACE;
        private Resolution resolution;
        private Object surface;

        public TargetSurface(Object surface, Resolution resolution) throws GLException {
            if (resolution == null) {
                throw new IllegalArgumentException("resolution is null");
            }
            this.surface = surface;
            this.resolution = resolution;
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating EGL surface for " + surface);
            }
            this.eglSurface = AbstractRenderingTarget.this.doCreateEGLSurface(surface, resolution);
            EGLUtils.validate("Failed to create EGL surface.");
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": EGL surface " + Integer.toHexString(System.identityHashCode(this.eglSurface)) + " created with " + surface);
            }
        }

        public void close() {
            if (this.eglSurface != null) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, getClass().getSimpleName() + ": Destroying EGL surface " + EGLUtils.idStringOf(this.eglSurface));
                }
                if (!EGL14.eglDestroySurface(AbstractRenderingTarget.this.context.getEGLDisplay(), this.eglSurface) && Log.isLoggable(Commons.TAG, 5)) {
                    Log.w(Commons.TAG, getClass().getSimpleName() + ": Failed to destroy EGL surface " + EGLUtils.idStringOf(this.eglSurface) + ". Error: " + Integer.toHexString(EGL14.eglGetError()));
                }
                this.eglSurface = EGL14.EGL_NO_SURFACE;
            }
            this.surface = null;
            this.resolution = null;
        }

        public Resolution getResolution() {
            return this.resolution;
        }

        public void setResolution(Resolution resolution) {
            this.resolution = resolution;
        }

        public Object getSurface() {
            return this.surface;
        }

        public boolean isBound() {
            return this.eglSurface.equals(EGL14.eglGetCurrentSurface(12377));
        }

        public void bind() throws GLException {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Binding EGL surface " + Integer.toHexString(System.identityHashCode(this.eglSurface)));
            }
            EGL14.eglMakeCurrent(AbstractRenderingTarget.this.context.getEGLDisplay(), this.eglSurface, this.eglSurface, AbstractRenderingTarget.this.context.getEGLContext());
            EGLUtils.validate("Failed to bind the surface.");
        }

        public void unbind() throws GLException {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Unbinding EGL surface " + Integer.toHexString(System.identityHashCode(this.eglSurface)));
            }
            EGL14.eglMakeCurrent(AbstractRenderingTarget.this.context.getEGLDisplay(), EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGLUtils.validate("Failed to unbind the surface.");
        }

        public boolean swapBuffers() throws GLException {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Swapping buffers of EGL surface " + Integer.toHexString(System.identityHashCode(this.eglSurface)));
            }
            boolean result = EGL14.eglSwapBuffers(AbstractRenderingTarget.this.context.getEGLDisplay(), this.eglSurface);
            EGLUtils.validate("Failed to swap buffers.");
            return result;
        }

        public void setTimestamp(long timestamp) throws GLException {
            EGLExt.eglPresentationTimeANDROID(AbstractRenderingTarget.this.context.getEGLDisplay(), this.eglSurface, timestamp);
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, SurfaceTextureRenderer.class.getSimpleName() + ": current frame surface timestamp " + timestamp + " ns");
            }
            EGLUtils.validate("Failed to set timestamp.");
        }

        public String toString() {
            return getClass().getSimpleName() + " [eglSurface=" + this.eglSurface + ", surface=" + this.surface + ", resolution=" + this.resolution + "]";
        }
    }

    protected abstract void doInitialBinding() throws GLException;

    protected AbstractRenderingTarget(Context sharedContext, int[] eglConfigAttributes, Events eventsListener) throws GLException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Creating");
        }
        this.eventsListener = eventsListener;
        this.context = new Context(sharedContext, eglConfigAttributes);
        if (Log.isLoggable(Commons.TAG, 3)) {
            Log.d(Commons.TAG, getClass().getSimpleName() + ": Created");
        }
    }

    void onFrameReady(long timestamp) {
        if (this.eventsListener != null) {
            this.eventsListener.onFrameRendered(timestamp);
        }
    }

    public void setSurface(Object surface, Resolution resolution) throws GLException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Setting " + surface + " at " + resolution);
        }
        unsetSurface();
        this.targetSurface = new TargetSurface(surface, resolution);
        doInitialBinding();
    }

    public void unsetSurface() {
        if (this.targetSurface != null) {
            this.targetSurface.close();
            this.targetSurface = null;
        }
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setFitFactor(float fitFactor) {
        this.fitFactor = fitFactor;
    }

    public float getFitFactor() {
        return this.fitFactor;
    }

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public int getRotationAngle() {
        return this.rotationAngle;
    }

    public void setFlipped(boolean isHorizFlipped, boolean isVertFlipped) {
        this.isHorizFlipped = isHorizFlipped;
        this.isVertFlipped = isVertFlipped;
    }

    public boolean isHorizFlipped() {
        return this.isHorizFlipped;
    }

    public boolean isVertFlipped() {
        return this.isVertFlipped;
    }

    public Resolution getResolution() {
        if (this.targetSurface != null) {
            return this.targetSurface.getResolution();
        }
        throw new IllegalStateException("no targetSurface set");
    }

    public Object getSurface() {
        if (this.targetSurface != null) {
            return this.targetSurface.getSurface();
        }
        throw new IllegalStateException("no targetSurface set");
    }

    public boolean hasSurface() {
        return this.targetSurface != null;
    }

    public boolean isBound() {
        if (this.targetSurface != null) {
            return this.targetSurface.isBound();
        }
        throw new IllegalStateException("no targetSurface set");
    }

    public void bind() throws GLException {
        if (this.targetSurface == null) {
            throw new IllegalStateException("no targetSurface set");
        }
        this.targetSurface.bind();
    }

    public void unbind() throws GLException {
        if (this.targetSurface == null) {
            throw new IllegalStateException("no targetSurface set");
        }
        this.targetSurface.unbind();
    }

    public boolean swapBuffers() throws GLException {
        if (this.targetSurface != null) {
            return this.targetSurface.swapBuffers();
        }
        throw new IllegalStateException("no targetSurface set");
    }

    public void setTimestamp(long timestamp) throws GLException {
        if (this.targetSurface == null) {
            throw new IllegalStateException("no targetSurface set");
        }
        this.targetSurface.setTimestamp(timestamp);
    }

    public void setDelayedTimestmap(long timestamp) {
        this.delayedTimestamp = timestamp;
    }

    public long getDelayedTimestamp() {
        return this.delayedTimestamp;
    }

    public void close() {
        if (this.context != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, getClass().getSimpleName() + ": Closing");
            }
            unsetSurface();
            this.context.close();
            this.context = null;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, getClass().getSimpleName() + ": Closed");
            }
        }
        this.eventsListener = null;
    }

    protected boolean isBoundAny() {
        return (EGL14.eglGetCurrentContext() == EGL14.EGL_NO_CONTEXT || EGL14.eglGetCurrentSurface(12377) == EGL14.EGL_NO_SURFACE) ? false : true;
    }

    public String toString() {
        return getClass().getSimpleName() + " [context=" + this.context + ", targetSurface=" + this.targetSurface + ", isEnabled=" + this.isEnabled + ", isHorizFlipped=" + this.isHorizFlipped + ", isVertFlipped=" + this.isVertFlipped + ", rotationAngle=" + this.rotationAngle + "]";
    }

    protected EGLSurface doCreateEGLSurface(Object surface, Resolution resolution) {
        return EGL14.eglCreateWindowSurface(this.context.getEGLDisplay(), this.context.getEGLConfig(), surface, new int[]{12344}, 0);
    }

    public void setTargetFrameRate(float targetFrameRate) {
        this.targetFrameRate = targetFrameRate;
    }

    public float getTargetFrameRate() {
        return this.targetFrameRate;
    }
}
