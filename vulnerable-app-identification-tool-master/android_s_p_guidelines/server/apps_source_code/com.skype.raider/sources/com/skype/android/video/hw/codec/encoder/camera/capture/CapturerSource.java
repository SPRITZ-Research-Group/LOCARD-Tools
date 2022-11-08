package com.skype.android.video.hw.codec.encoder.camera.capture;

import android.graphics.SurfaceTexture;
import com.skype.android.video.hw.format.Resolution;
import java.io.Closeable;

public interface CapturerSource extends Closeable {
    void configure(Resolution resolution, int i) throws CapturerException;

    long getNativeCtx();

    void setFramerate(int i) throws CapturerException;

    void setResolution(Resolution resolution) throws CapturerException;

    void setSurfaceTexture(SurfaceTexture surfaceTexture) throws CapturerException;

    void start() throws CapturerException;

    void stop();

    void unlock();
}
