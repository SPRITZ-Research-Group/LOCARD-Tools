package com.skype.android.video;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;

public class DeviceProfile {
    static int frontCameraIndex;
    static int frontCameraMounting;
    static int rearCameraIndex;
    static int rearCameraMounting;

    static {
        frontCameraIndex = -1;
        rearCameraIndex = -1;
        try {
            int num = Camera.getNumberOfCameras();
            for (int i = 0; i < num; i++) {
                CameraInfo info = new CameraInfo();
                Camera.getCameraInfo(i, info);
                if (info.facing == 1) {
                    frontCameraIndex = i;
                    frontCameraMounting = info.orientation;
                }
                if (info.facing == 0) {
                    rearCameraIndex = i;
                    rearCameraMounting = info.orientation;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int getCameraIndex(int cameraFacing) {
        if (cameraFacing == 1) {
            return frontCameraIndex;
        }
        if (cameraFacing == 0) {
            return rearCameraIndex;
        }
        return -1;
    }

    public static synchronized boolean changePreviewDimensions(int cameraFacing, int deviceOrientation) {
        boolean z = true;
        synchronized (DeviceProfile.class) {
            if (cameraFacing == 1) {
                if ((frontCameraMounting + deviceOrientation) % 180 == 0) {
                    z = false;
                }
            } else if (cameraFacing != 0) {
                z = false;
            } else if ((rearCameraMounting - deviceOrientation) % 180 == 0) {
                z = false;
            }
        }
        return z;
    }
}
