package com.skype.android.video.capture;

import com.skype.android.video.capture.IPreviewBinding.Callback;

public final class PreviewBinding implements IPreviewBinding {
    private final Callback _cb;
    private long nativePtr;

    private native void nativeInit();

    private native void nativeUninit();

    public final native long getNativeBindingEvent();

    public final native int getNativeBindingType();

    public final native void setPreviewSurface(Object obj);

    public PreviewBinding(Callback cb) {
        this._cb = cb;
        nativeInit();
    }

    public final void dispose() {
        nativeUninit();
    }

    private void onBindingCreated(long bindingRef) {
        this._cb.onBindingCreated(bindingRef);
    }

    private void onBindingReleased() {
        this._cb.onBindingReleased();
    }

    private void onBindingFailed() {
        this._cb.onBindingFailed();
    }

    private void onFrameSizeChanged(Object holder, int width, int height) {
        this._cb.onFrameSizeChanged(holder, width, height);
    }

    private void onPreviewSurfaceUnset(Object holder) {
        this._cb.onPreviewSurfaceUnset(holder);
    }
}
