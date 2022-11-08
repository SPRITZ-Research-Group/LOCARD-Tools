package com.skype.android.video.hw.codec.encoder.camera;

import com.skype.android.video.hw.codec.encoder.camera.capture.CapturerException;
import com.skype.android.video.hw.format.Resolution;

public interface SurfaceObserver {
    void attachSurface(Object obj, Resolution resolution);

    void detachSurface();

    float getTargetFrameRate();

    boolean pushFrame() throws CapturerException;

    void setTargetFrameRate(float f);
}
