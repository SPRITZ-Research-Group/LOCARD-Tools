package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.ErrorCode;

public final class CameraManagerSingleton {
    private static CameraManager a;
    private static CameraManagerFactory b;

    private CameraManagerSingleton() {
    }

    public static synchronized void setFactory(CameraManagerFactory fctry) {
        synchronized (CameraManagerSingleton.class) {
            a = null;
            b = fctry;
        }
    }

    public static synchronized CameraManager getInstance() throws CaptureException {
        CameraManager cameraManager;
        synchronized (CameraManagerSingleton.class) {
            if (a == null) {
                if (b == null) {
                    throw new CaptureException("CameraManagerFactory is not set", ErrorCode.ANDROID_CAMERA_MANAGER_NOT_SET);
                }
                a = b.createCameraManager();
            }
            cameraManager = a;
        }
        return cameraManager;
    }
}
