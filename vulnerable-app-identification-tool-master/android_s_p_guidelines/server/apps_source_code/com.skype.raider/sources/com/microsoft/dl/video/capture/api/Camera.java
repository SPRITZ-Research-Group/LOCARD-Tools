package com.microsoft.dl.video.capture.api;

public interface Camera {
    void addCallbackBuffer(byte[] bArr) throws CaptureException;

    void close() throws CaptureException;

    CameraParameters getParameters() throws CaptureException;

    void setCallback(CameraCallback cameraCallback) throws CaptureException;

    void setDisplayOrientation(int i) throws CaptureException;

    void setParameters(CameraParameters cameraParameters) throws CaptureException;

    void setPreviewDisplay(Object obj) throws CaptureException;

    void startPreview() throws CaptureException;

    void stopPreview() throws CaptureException;
}
