package com.skype.android.video.capture;

import com.skype.android.video.capture.BindingStillImageCapture.Callback;

public final class StillImageCaptureBindingEvent implements BindingStillImageCapture {
    private final Callback _cb;
    private long nativePtr;

    private native void nativeInit();

    private native void nativeUninit();

    public final native void captureStillImage();

    public final native long getNativeBindingEvent();

    public final native int getNativeBindingType();

    public StillImageCaptureBindingEvent(Callback cb) {
        this._cb = cb;
        nativeInit();
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

    private void onStillImageCaptureCompleted(byte[] framebuffer, int width, int height, int angle) {
        this._cb.onStillImageCaptureCompleted(framebuffer, width, height, angle);
    }
}
