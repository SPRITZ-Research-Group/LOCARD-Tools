package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.ErrorCodeException;

public interface CameraCallback {
    void onCpuFrameCaptured(byte[] bArr, Camera camera);

    void onError(ErrorCodeException errorCodeException);

    void onGpuFrameCaptured(int i, int i2);

    void onGpuFrameDropped();
}
