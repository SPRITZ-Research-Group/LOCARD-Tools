package com.microsoft.dl.video;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.capture.api.CameraManagerFactory;
import com.microsoft.dl.video.capture.api.CameraManagerSingleton;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.impl.real.RealCameraManagerImpl.Factory;
import com.microsoft.dl.video.capture.impl2.RealCamera2ManagerImpl;

public final class VideoPlatform {

    private enum CameraManagerFactoryType {
        REAL_CAMERAv1,
        REAL_CAMERAv2
    }

    private static native String getCameraManagerFactoryName();

    private VideoPlatform() {
    }

    public static synchronized void initialize(boolean useCamera2APIs) {
        Exception e;
        synchronized (VideoPlatform.class) {
            try {
                getCameraManagerFactoryName();
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "VideoPlatform::intialize with camera2: " + useCamera2APIs);
                }
                if (useCamera2APIs) {
                    CameraManagerSingleton.setFactory(a(CameraManagerFactoryType.valueOf("REAL_CAMERAv2")));
                } else {
                    CameraManagerSingleton.setFactory(a(CameraManagerFactoryType.valueOf("REAL_CAMERAv1")));
                }
            } catch (Exception e2) {
                e = e2;
            } catch (Exception e22) {
                e = e22;
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Exception caught ", e);
        }
    }

    private static CameraManagerFactory a(CameraManagerFactoryType type) throws CaptureException {
        switch (type) {
            case REAL_CAMERAv1:
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "CameraManagerFactory: REAL_CAMERAv1");
                }
                return new Factory();
            case REAL_CAMERAv2:
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "CameraManagerFactory: REAL_CAMERAv2");
                }
                return new RealCamera2ManagerImpl.Factory();
            default:
                throw new CaptureException("Unknown CameraManagerFactoryType " + type, ErrorCode.ANDROID_CAMERA_MANAGER_INVALID_TYPE);
        }
    }
}
