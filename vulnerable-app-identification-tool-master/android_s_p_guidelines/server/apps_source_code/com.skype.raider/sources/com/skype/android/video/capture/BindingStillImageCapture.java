package com.skype.android.video.capture;

public interface BindingStillImageCapture {

    public interface Callback {
        void onBindingCreated(long j);

        void onBindingFailed();

        void onBindingReleased();

        void onStillImageCaptureCompleted(byte[] bArr, int i, int i2, int i3);
    }

    void captureStillImage();

    long getNativeBindingEvent();

    int getNativeBindingType();
}
