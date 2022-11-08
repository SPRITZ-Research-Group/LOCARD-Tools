package com.microsoft.dl.video.capture.api;

import java.io.Closeable;

public interface CameraManager extends Closeable {
    CameraCapabilities getCameraCapabilities(int i) throws CaptureException;

    int getNumberOfCameras() throws CaptureException;

    StaticCameraCapabilities getStaticCameraCapabilities(int i) throws CaptureException;

    Camera openCamera(int i) throws CaptureException;
}
