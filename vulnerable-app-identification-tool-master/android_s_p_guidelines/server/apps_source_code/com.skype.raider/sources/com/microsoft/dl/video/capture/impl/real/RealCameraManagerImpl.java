package com.microsoft.dl.video.capture.impl.real;

import android.hardware.Camera;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraManager;
import com.microsoft.dl.video.capture.api.CameraManagerFactory;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import com.microsoft.dl.video.capture.impl.real.impl.CameraCapabilitiesUtils;
import java.util.List;

public class RealCameraManagerImpl implements CameraManager {
    private List<CameraCapabilities> a;
    private List<StaticCameraCapabilities> b;

    public static class Factory implements CameraManagerFactory {
        public final CameraManager createCameraManager() {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "CAMERA:createCameraManager");
            }
            return new RealCameraManagerImpl();
        }
    }

    public final int getNumberOfCameras() {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA: Returning Total: " + Camera.getNumberOfCameras());
        }
        return Camera.getNumberOfCameras();
    }

    public final com.microsoft.dl.video.capture.api.Camera openCamera(int id) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA:openCamera: " + id);
        }
        return new RealCameraImpl(id);
    }

    public final CameraCapabilities getCameraCapabilities(int id) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA:getCameraCapabilities");
        }
        if (this.a == null) {
            this.a = CameraCapabilitiesUtils.obtain();
        }
        if (id >= 0 && id < getNumberOfCameras()) {
            return ((CameraCapabilities) this.a.get(id)).clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_CAMERA_INVALID_ID);
    }

    public final StaticCameraCapabilities getStaticCameraCapabilities(int id) throws CaptureException {
        if (this.b == null) {
            this.b = CameraCapabilitiesUtils.obtainStatic();
        }
        if (id >= 0 && id < getNumberOfCameras()) {
            return ((StaticCameraCapabilities) this.b.get(id)).clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_CAMERA_INVALID_ID);
    }

    public void close() {
    }
}
