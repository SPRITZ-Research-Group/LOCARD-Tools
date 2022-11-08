package com.skype.android.video.render;

import android.graphics.Bitmap;
import com.skype.android.util2.Log;
import com.skype.android.video.render.BindingRenderer.Callback;
import java.util.concurrent.atomic.AtomicInteger;

public final class GLESBindingRenderer implements BindingRenderer {
    private final String TAG = (System.identityHashCode(this));
    private final Callback _cb;
    private final AtomicInteger cntPendingRegisterViewCalls = new AtomicInteger(0);
    private final AtomicInteger cntPendingUnregisterViewCalls = new AtomicInteger(0);
    private int lastFrameHeight = 0;
    private int lastFrameWidth = 0;
    private long nativePtr;
    private final Renderer renderer = new Renderer();

    private class Renderer implements SurfaceTextureRenderer {
        private volatile int numFramesRendered;
        private boolean started;
        private GLTextureView view;
        private int viewHeight;
        private int viewWidth;

        private Renderer() {
            this.started = false;
            this.numFramesRendered = 0;
        }

        int getFramesRendered() {
            return this.numFramesRendered;
        }

        boolean hasView() {
            return this.view != null;
        }

        void setTextureView(Object glTextureView) {
            GLESBindingRenderer.this.TAG;
            if (Log.a(3)) {
                Log.a(GLESBindingRenderer.this.TAG, "setTextureView: " + System.identityHashCode(glTextureView) + " view " + System.identityHashCode(this.view));
            }
            if (glTextureView != null && this.view == null && !this.started) {
                this.view = (GLTextureView) glTextureView;
                this.view.setRenderer(this);
            } else if (glTextureView == null && this.view != null) {
                this.view.setRenderer(null);
                if (!this.started) {
                    this.view = null;
                }
            }
        }

        void start() {
            GLESBindingRenderer.this.TAG;
            if (Log.a(3)) {
                Log.a(GLESBindingRenderer.this.TAG, "start: " + System.identityHashCode(this.view));
            }
            this.started = true;
            this.view.start();
        }

        void stop() {
            GLESBindingRenderer.this.TAG;
            if (Log.a(3)) {
                Log.a(GLESBindingRenderer.this.TAG, "stop: " + System.identityHashCode(this.view));
            }
            this.started = false;
            this.view.stop();
            GLESBindingRenderer.this.TAG;
            if (Log.a(3)) {
                Log.a(GLESBindingRenderer.this.TAG, "stopped: " + System.identityHashCode(this.view));
            }
            this.view = null;
        }

        boolean onFrameArrived(int width, int height) {
            return true;
        }

        void onNewFrameReady() {
            this.view.requestRender();
        }

        public boolean render(int viewWidth, int viewHeight) {
            this.viewWidth = viewWidth;
            this.viewHeight = viewHeight;
            boolean retval = GLESBindingRenderer.this.queryNextFrame(viewWidth, viewHeight);
            if (retval) {
                this.numFramesRendered++;
            }
            return retval;
        }

        public void attach() {
            GLESBindingRenderer.this.attach();
        }

        public void detach() {
            GLESBindingRenderer.this.detach();
        }

        public void onVisibilityChanged(boolean isVisible) {
            GLESBindingRenderer.this.onVisibilityChanged(isVisible);
        }
    }

    private native void attach();

    private native void detach();

    private native CapturedFrame nativeCaptureFrame();

    private native void nativeInit();

    private native void nativeUninit();

    private native void onVisibilityChanged(boolean z);

    private native boolean queryNextFrame(int i, int i2);

    public final native long getNativeBindingEvent();

    public final native int getNativeBindingType();

    public GLESBindingRenderer(Callback cb) {
        if (Log.a(3)) {
            Log.a(this.TAG, "ctor");
        }
        this._cb = cb;
        nativeInit();
    }

    public final void checkView(Object view) {
        if (view == null) {
            throw new NullPointerException("view is null");
        } else if (!(view instanceof GLTextureView)) {
            throw new ClassCastException("view is not of correct type");
        }
    }

    private void checkInvariant(int a, int b) {
        int diff = b - a;
        if (a < -1 || diff < 0 || diff > 1) {
            throw new IllegalStateException("contract violation");
        }
    }

    public final void registerView(Object view) {
        checkView(view);
        checkInvariant(this.cntPendingRegisterViewCalls.get(), this.cntPendingUnregisterViewCalls.get());
        this.renderer.setTextureView(view);
        this.cntPendingRegisterViewCalls.incrementAndGet();
    }

    public final void unregisterView(Object view) {
        checkView(view);
        checkInvariant(this.cntPendingUnregisterViewCalls.get(), this.cntPendingRegisterViewCalls.get());
        this.renderer.setTextureView(null);
        this.cntPendingUnregisterViewCalls.incrementAndGet();
    }

    public final Bitmap captureFrame() {
        CapturedFrame result = captureFrame2();
        if (result == null) {
            return null;
        }
        return result.bitmap;
    }

    public final CapturedFrame captureFrame2() {
        if (this.renderer.hasView()) {
            return nativeCaptureFrame();
        }
        return null;
    }

    public final void dispose() {
        if (Log.a(3)) {
            Log.a(this.TAG, "dispose");
        }
        nativeUninit();
    }

    public final int getFramesRendered() {
        return this.renderer.getFramesRendered();
    }

    private void onBindingCreated(long bindingRef) {
        checkInvariant(this.cntPendingRegisterViewCalls.decrementAndGet(), this.cntPendingUnregisterViewCalls.get());
        this.renderer.start();
        this._cb.onBindingCreated(bindingRef);
    }

    private void onBindingReleased() {
        checkInvariant(this.cntPendingUnregisterViewCalls.decrementAndGet(), this.cntPendingRegisterViewCalls.get());
        this.renderer.stop();
        this.lastFrameWidth = 0;
        this.lastFrameHeight = 0;
        this._cb.onBindingReleased();
    }

    private void onBindingFailed() {
        checkInvariant(this.cntPendingRegisterViewCalls.decrementAndGet(), this.cntPendingUnregisterViewCalls.get());
        this._cb.onBindingFailed();
    }

    private boolean onFrameArrived(int width, int height) {
        if (!(this.lastFrameWidth == width && this.lastFrameHeight == height)) {
            this._cb.onSizeChanged(width, height);
            this.lastFrameWidth = width;
            this.lastFrameHeight = height;
        }
        return this.renderer.onFrameArrived(width, height);
    }

    private void onNewFrameReady() {
        this.renderer.onNewFrameReady();
    }

    private void onFirstFrameRendered() {
        this._cb.onFirstFrameRendered();
    }
}
