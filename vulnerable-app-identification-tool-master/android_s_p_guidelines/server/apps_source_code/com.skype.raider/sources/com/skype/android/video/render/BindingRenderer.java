package com.skype.android.video.render;

import android.graphics.Bitmap;

public interface BindingRenderer {

    public interface Callback {
        void onBindingCreated(long j);

        void onBindingFailed();

        void onBindingReleased();

        void onFirstFrameRendered();

        void onSizeChanged(int i, int i2);
    }

    Bitmap captureFrame();

    CapturedFrame captureFrame2();

    void dispose();

    long getNativeBindingEvent();

    int getNativeBindingType();

    void registerView(Object obj);

    void unregisterView(Object obj);
}
