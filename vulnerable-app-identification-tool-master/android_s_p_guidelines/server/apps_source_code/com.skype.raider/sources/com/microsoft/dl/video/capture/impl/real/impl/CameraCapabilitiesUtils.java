package com.microsoft.dl.video.capture.impl.real.impl;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import com.microsoft.dl.Platform;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraCapabilities.Facing;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.capture.api.ImageFormat;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import com.microsoft.dl.video.utils.Resolution;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class CameraCapabilitiesUtils {

    private static class SerializationBean implements Serializable {
        private final String a = Build.FINGERPRINT;
        private final List<CameraCapabilities> b;

        public SerializationBean(List<CameraCapabilities> capabilities) {
            this.b = capabilities;
        }

        public List<CameraCapabilities> getCapabilities() {
            return this.b;
        }

        public boolean isSameOSBuild() {
            return Build.FINGERPRINT.equals(this.a);
        }

        public String toString() {
            return getClass().getSimpleName() + " [osBuildFingerprint=" + this.a + ", capabilities=" + this.b + "]";
        }
    }

    public static class SerializationFailedException extends Exception {
        public SerializationFailedException(String detailMessage) {
            super(detailMessage);
        }

        public SerializationFailedException(Throwable throwable) {
            super(throwable);
        }
    }

    protected CameraCapabilitiesUtils() {
    }

    public static List<CameraCapabilities> obtain() throws CaptureException {
        List<CameraCapabilities> capabilities;
        boolean isLoaded = false;
        boolean isCollected = false;
        boolean isSaved = false;
        try {
            capabilities = load();
            isLoaded = true;
        } catch (SerializationFailedException e) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Could not load camera capabilities from file: " + e.getMessage());
            }
            capabilities = a();
            isCollected = true;
            try {
                save(capabilities);
                isSaved = true;
            } catch (SerializationFailedException e2) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Could not save camera capabilities to file", e);
                }
            }
        }
        if ((!isLoaded || isCollected || isSaved) && ((isLoaded || !isCollected) && Log.isLoggable(PackageInfo.TAG, 7))) {
            Log.a(PackageInfo.TAG, "The invariant has failed: isLoaded=" + isLoaded + ", isCollected=" + isCollected + ", isSaved=" + isSaved);
        }
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Camera capabilities are " + (isLoaded ? "loaded from the cache file" : "") + (isCollected ? "collected from the device" : "") + (isSaved ? " and saved to the cache file" : ""));
            for (CameraCapabilities caps : capabilities) {
                Log.i(PackageInfo.TAG, caps.toString());
            }
        }
        return capabilities;
    }

    public static List<StaticCameraCapabilities> obtainStatic() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "obtainStatic start");
        }
        List<StaticCameraCapabilities> staticCapabilities = new ArrayList();
        int last = Camera.getNumberOfCameras();
        for (int id = 0; id < last; id++) {
            CameraInfo b = b(id);
            StaticCameraCapabilities staticCameraCapabilities = new StaticCameraCapabilities();
            staticCameraCapabilities.setCameraId(id);
            staticCameraCapabilities.setFacing(a(b));
            staticCameraCapabilities.setOrientation(b(b));
            staticCapabilities.add(staticCameraCapabilities);
        }
        return staticCapabilities;
    }

    public static ImageFormat mapImageFormat(int format) {
        switch (format) {
            case 17:
                return ImageFormat.NV21;
            default:
                return null;
        }
    }

    public static int mapImageFormat(ImageFormat format) throws CaptureException {
        switch (format) {
            case NV21:
                return 17;
            case YV12:
                return 842094169;
            case NV16:
                return 16;
            case YUY2:
                return 20;
            default:
                throw new CaptureException("Could not map ImageFormat " + format.toString(), ErrorCode.ANDROID_CAMERA_IMAGE_FORMAT_MAPPING_FAILED);
        }
    }

    public static Resolution mapResolution(Size size) throws CaptureException {
        return new Resolution(size.width, size.height);
    }

    public static FpsRange mapFpsRange(int[] range) throws CaptureException {
        int min = range[0];
        int max = range[1];
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "FpsRange, min:" + min + "  max:" + max);
        }
        return new FpsRange(min, max);
    }

    public static boolean isPictureSizeSupported(Resolution resolution, Parameters cameraParameters) {
        for (Size s : cameraParameters.getSupportedPictureSizes()) {
            if (s.width == resolution.getWidth() && s.height == resolution.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public static List<CameraCapabilities> load() throws SerializationFailedException {
        Throwable e;
        try {
            FileInputStream fileStream = new FileInputStream(new File(Platform.getInfo().getCacheDir(), "camera_capabilities"));
            ObjectInputStream objStream;
            try {
                objStream = new ObjectInputStream(fileStream);
                SerializationBean serializationBean = (SerializationBean) objStream.readObject();
                if (serializationBean.isSameOSBuild()) {
                    List<CameraCapabilities> capabilities = serializationBean.getCapabilities();
                    objStream.close();
                    fileStream.close();
                    return capabilities;
                }
                throw new SerializationFailedException("The OS build fingerprint has changed");
            } catch (Throwable th) {
                fileStream.close();
            }
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new SerializationFailedException(e);
        } catch (FileNotFoundException e3) {
            e = e3;
            throw new SerializationFailedException(e);
        } catch (OptionalDataException e4) {
            e = e4;
            throw new SerializationFailedException(e);
        } catch (RuntimeException e5) {
            e = e5;
            throw new SerializationFailedException(e);
        } catch (StreamCorruptedException e6) {
            e = e6;
            throw new SerializationFailedException(e);
        } catch (Throwable e7) {
            throw new SerializationFailedException(e7);
        }
    }

    public static void save(List<CameraCapabilities> capabilities) throws SerializationFailedException {
        Throwable e;
        try {
            FileOutputStream fileStream = new FileOutputStream(new File(Platform.getInfo().getCacheDir(), "camera_capabilities"));
            ObjectOutputStream objStream;
            try {
                objStream = new ObjectOutputStream(fileStream);
                objStream.writeObject(new SerializationBean(capabilities));
                objStream.close();
                fileStream.close();
            } catch (Throwable th) {
                fileStream.close();
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            throw new SerializationFailedException(e);
        } catch (RuntimeException e3) {
            e = e3;
            throw new SerializationFailedException(e);
        } catch (Throwable e4) {
            throw new SerializationFailedException(e4);
        }
    }

    private static List<CameraCapabilities> a() throws CaptureException {
        List<CameraCapabilities> capabilities = new ArrayList();
        int last = Camera.getNumberOfCameras();
        for (int id = 0; id < last; id++) {
            capabilities.add(a(id));
        }
        return capabilities;
    }

    private static CameraCapabilities a(int cameraId) throws CaptureException {
        CameraInfo cameraInfo = b(cameraId);
        Camera camera = c(cameraId);
        Parameters cameraParameters = camera.getParameters();
        try {
            CameraCapabilities capabilities = new CameraCapabilities();
            capabilities.setCameraId(cameraId);
            capabilities.setFacing(a(cameraInfo));
            capabilities.setOrientation(b(cameraInfo));
            NavigableSet treeSet = new TreeSet();
            for (Integer num : cameraParameters.getSupportedPreviewFormats()) {
                ImageFormat mapImageFormat = mapImageFormat(num.intValue());
                if (mapImageFormat != null) {
                    treeSet.add(mapImageFormat);
                } else if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Unknown image format " + num + ", skipping");
                }
            }
            capabilities.setSupportedImageFormats(treeSet);
            capabilities.setSupportedResolutions(a(cameraParameters));
            capabilities.setSupportedFpsRanges(b(cameraParameters));
            capabilities.setSupportedFocusModes(c(cameraParameters));
            Size size = (Size) Collections.max(cameraParameters.getSupportedPictureSizes(), new Comparator<Size>() {
                public final int compare(Size left, Size right) {
                    return Integer.signum((left.width * left.height) - (right.width * right.height));
                }
            });
            capabilities.setNativeAspectRatio(((float) size.width) / ((float) size.height));
            return capabilities;
        } finally {
            camera.release();
        }
    }

    private static NavigableSet<Resolution> a(Parameters cameraParameters) throws CaptureException {
        NavigableSet<Resolution> resolutions = new TreeSet();
        for (Size size : cameraParameters.getSupportedPreviewSizes()) {
            resolutions.add(mapResolution(size));
        }
        return resolutions;
    }

    private static NavigableSet<FpsRange> b(Parameters cameraParameters) throws CaptureException {
        NavigableSet<FpsRange> fpsRanges = new TreeSet();
        for (int[] range : cameraParameters.getSupportedPreviewFpsRange()) {
            fpsRanges.add(mapFpsRange(range));
        }
        return fpsRanges;
    }

    private static NavigableSet<String> c(Parameters cameraParameters) {
        NavigableSet<String> focusModes = new TreeSet();
        for (String focusMode : cameraParameters.getSupportedFocusModes()) {
            focusModes.add(focusMode);
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "focusModes: " + focusMode);
            }
        }
        return focusModes;
    }

    private static Facing a(CameraInfo cameraInfo) {
        switch (cameraInfo.facing) {
            case 0:
                return Facing.BACK;
            case 1:
                return Facing.FRONT;
            default:
                return Facing.OTHER;
        }
    }

    private static int b(CameraInfo cameraInfo) {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "orientation:" + (360 - cameraInfo.orientation));
        }
        return 360 - cameraInfo.orientation;
    }

    private static CameraInfo b(int cameraId) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "getCameraInfo starts");
        }
        CameraInfo cameraInfo = new CameraInfo();
        try {
            Camera.getCameraInfo(cameraId, cameraInfo);
            return cameraInfo;
        } catch (Throwable e) {
            throw new CaptureException("Could not get CameraInfo for the camera " + cameraId, e, ErrorCode.ANDROID_CAMERA_GET_INFO_FAILED);
        }
    }

    private static Camera c(int cameraId) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "openCamera Id: " + cameraId);
        }
        try {
            Camera camera = Camera.open(cameraId);
            if (camera != null) {
                return camera;
            }
            throw new CaptureException("Could not open camera " + cameraId, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        } catch (Throwable e) {
            throw new CaptureException("Could not open camera " + cameraId, e, ErrorCode.ANDROID_CAMERA_OPEN_FAILED);
        }
    }
}
