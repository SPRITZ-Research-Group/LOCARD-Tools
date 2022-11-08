package com.microsoft.dl.video.capture.impl2;

import com.microsoft.dl.Platform;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraManager;
import com.microsoft.dl.video.capture.api.CameraManagerFactory;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import java.util.List;

public class RealCamera2ManagerImpl implements CameraManager {
    private List<CameraCapabilities> a;
    private List<StaticCameraCapabilities> b;

    public static class Factory implements CameraManagerFactory {
        public final CameraManager createCameraManager() {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "CAMERA2:createCameraManager");
            }
            return new RealCamera2ManagerImpl();
        }
    }

    public final int getNumberOfCameras() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA2:getNumberOfCameras starts with instance: " + this);
        }
        int cameraCnt = 0;
        android.hardware.camera2.CameraManager cameraManager = (android.hardware.camera2.CameraManager) Platform.getInfo().getAppContext().getSystemService("camera");
        if (cameraManager == null) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "ERROR: cameraManager is null");
            }
            throw new CaptureException("android.hardware.camera2.CameraManager null", ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE);
        }
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                cameraCnt++;
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Camera Name: " + cameraId);
                }
            }
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "Returning Total: " + cameraCnt);
            }
            return cameraCnt;
        } catch (Throwable e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ", e);
            }
            throw new CaptureException(e, ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE);
        }
    }

    public final Camera openCamera(int id) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA2:openCamera: " + id);
        }
        return new RealCamera2Impl(id);
    }

    public final CameraCapabilities getCameraCapabilities(int id) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA2:getCameraCapabilities");
        }
        if (this.a == null) {
            this.a = Camera2CapabilitiesUtils.obtain();
        }
        if (id >= 0 && id < getNumberOfCameras()) {
            return ((CameraCapabilities) this.a.get(id)).clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_CAMERA_INVALID_ID);
    }

    public final StaticCameraCapabilities getStaticCameraCapabilities(int id) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA2:getStaticCameraCapabilities");
        }
        if (this.b == null) {
            this.b = Camera2CapabilitiesUtils.obtainStatic();
        }
        if (id >= 0 && id < getNumberOfCameras()) {
            return ((StaticCameraCapabilities) this.b.get(id)).clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_CAMERA_INVALID_ID);
    }

    public void close() {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "CAMERA2:close");
        }
    }
}
