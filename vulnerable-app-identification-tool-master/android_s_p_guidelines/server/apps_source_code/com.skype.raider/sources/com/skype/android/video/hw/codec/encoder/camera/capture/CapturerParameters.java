package com.skype.android.video.hw.codec.encoder.camera.capture;

import com.skype.android.video.hw.format.Resolution;
import java.io.Serializable;

public class CapturerParameters implements Serializable {
    private static final long serialVersionUID = -3362722631602027004L;
    public int cameraId;
    public Resolution cameraResolution;
    public float framerate;
    public boolean isPreEncodingRC = false;

    public String toString() {
        return getClass().getSimpleName() + " [cameraId=" + this.cameraId + ", cameraResolution=" + this.cameraResolution + ", framerate=" + this.framerate + ", preEncodingRC" + this.isPreEncodingRC + "]";
    }
}
