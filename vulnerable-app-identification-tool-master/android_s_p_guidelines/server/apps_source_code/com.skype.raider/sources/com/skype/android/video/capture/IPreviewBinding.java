package com.skype.android.video.capture;

public interface IPreviewBinding {

    public interface Callback {
        void onBindingCreated(long j);

        void onBindingFailed();

        void onBindingReleased();

        void onFrameSizeChanged(Object obj, int i, int i2);

        void onPreviewSurfaceUnset(Object obj);
    }

    void dispose();

    long getNativeBindingEvent();

    int getNativeBindingType();

    void setPreviewSurface(Object obj);
}
