package com.microsoft.dl.video.capture;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraManager;
import com.microsoft.dl.video.capture.api.CameraManagerSingleton;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import java.util.ArrayList;
import java.util.List;

public final class DeviceManager {
    private static DeviceManager a;
    private List<CameraInfo> b = new ArrayList();

    private static class CameraInfo {
        private final String a;
        private final StaticCameraCapabilities b;

        public CameraInfo(String name, StaticCameraCapabilities staticCapabilities) {
            this.a = name;
            this.b = staticCapabilities;
        }

        public String getName() {
            return this.a;
        }

        public StaticCameraCapabilities getStaticCameraCapabilities() {
            return this.b;
        }

        public String toString() {
            return getClass().getSimpleName() + " [staticCapabilities=" + this.b + ", name=" + this.a + "]";
        }
    }

    private DeviceManager() throws CaptureException {
        CameraManager cameraManager = CameraManagerSingleton.getInstance();
        int last = cameraManager.getNumberOfCameras();
        for (int id = 0; id < last; id++) {
            StaticCameraCapabilities caps = cameraManager.getStaticCameraCapabilities(id);
            this.b.add(new CameraInfo("/" + caps.getFacing().name() + "/" + id, caps));
        }
    }

    public static synchronized DeviceManager getInstance() {
        DeviceManager deviceManager = null;
        synchronized (DeviceManager.class) {
            if (a == null) {
                try {
                    a = new DeviceManager();
                } catch (CaptureException e) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Could not instantiate DeviceManager", e);
                    }
                } catch (RuntimeException e2) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught", e2);
                    }
                }
            }
            deviceManager = a;
        }
        return deviceManager;
    }

    public final int getNumCameras() {
        try {
            return this.b.size();
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return 0;
        }
    }

    public final int getCameraFacing(int cameraId) {
        try {
            return ((CameraInfo) this.b.get(cameraId)).getStaticCameraCapabilities().getFacing().ordinal();
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return -1;
        }
    }

    public final String getCameraName(int cameraId) {
        try {
            return ((CameraInfo) this.b.get(cameraId)).getName();
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return null;
        }
    }

    public final int getCameraOrientation(int cameraId) {
        try {
            return ((CameraInfo) this.b.get(cameraId)).getStaticCameraCapabilities().getOrientation();
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return -1;
        }
    }

    public final String toString() {
        return getClass().getSimpleName() + " [cameras=" + this.b + "]";
    }
}
