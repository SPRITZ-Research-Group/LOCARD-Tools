package com.microsoft.dl.video.capture.impl.mock;

import android.util.SparseArray;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.capture.api.Camera;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraManager;
import com.microsoft.dl.video.capture.api.CameraManagerFactory;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import java.util.Collection;

public class MockCameraManagerImpl implements CameraManager, MockCameraManager {
    private final SparseArray<CameraCapabilities> a = new SparseArray();
    private final SparseArray<MockCameraImpl> b = new SparseArray();
    private int c;

    public static class Factory implements CameraManagerFactory {
        public final CameraManager createCameraManager() {
            return new MockCameraManagerImpl();
        }
    }

    public final int getNumberOfCameras() {
        return this.a.size();
    }

    public final Camera openCamera(int id) throws CaptureException {
        CameraCapabilities cap = (CameraCapabilities) this.a.get(id);
        if (cap == null) {
            throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
        } else if (this.b.get(id) != null) {
            throw new CaptureException("Camera " + id + " is already open", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
        } else if (this.b.size() >= this.c) {
            throw new CaptureException("Can not open more than " + this.c + " cameras", ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
        } else {
            MockCameraImpl camera = new MockCameraImpl(cap);
            this.b.append(id, camera);
            return camera;
        }
    }

    public final CameraCapabilities getCameraCapabilities(int id) throws CaptureException {
        CameraCapabilities cap = (CameraCapabilities) this.a.get(id);
        if (cap != null) {
            return cap.clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
    }

    public final StaticCameraCapabilities getStaticCameraCapabilities(int id) throws CaptureException {
        StaticCameraCapabilities cap = (StaticCameraCapabilities) this.a.get(id);
        if (cap != null) {
            return cap.clone();
        }
        throw new CaptureException("No such camera " + id, ErrorCode.ANDROID_MOCK_CAMERA_FAILED);
    }

    public final void setMockCameraConfigs(Collection<CameraCapabilities> capabilities, int maxOpenCameras) {
        this.c = maxOpenCameras;
        for (CameraCapabilities cap : capabilities) {
            this.a.append(cap.getCameraId(), cap.clone());
        }
    }

    public final MockCamera getOpenCamera(int id) {
        return (MockCamera) this.b.get(id);
    }

    public void close() {
    }
}
