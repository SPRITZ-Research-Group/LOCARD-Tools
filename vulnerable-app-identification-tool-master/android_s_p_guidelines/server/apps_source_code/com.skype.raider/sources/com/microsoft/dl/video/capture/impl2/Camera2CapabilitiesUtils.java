package com.microsoft.dl.video.capture.impl2;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Size;
import com.adjust.sdk.Constants;
import com.microsoft.dl.Platform;
import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraCapabilities.Facing;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.capture.api.StaticCameraCapabilities;
import com.microsoft.dl.video.capture.impl.real.impl.CameraCapabilitiesUtils;
import com.microsoft.dl.video.capture.impl.real.impl.CameraCapabilitiesUtils.SerializationFailedException;
import com.microsoft.dl.video.utils.Resolution;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public final class Camera2CapabilitiesUtils extends CameraCapabilitiesUtils {
    private Camera2CapabilitiesUtils() {
    }

    public static List<StaticCameraCapabilities> obtainStatic() throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "obtainStatic start");
        }
        List<StaticCameraCapabilities> staticCapabilities = new ArrayList();
        for (String id : a()) {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "getStaticCameraCapabilities starts");
            }
            CameraCharacteristics a = a(id);
            StaticCameraCapabilities staticCameraCapabilities = new StaticCameraCapabilities();
            staticCameraCapabilities.setCameraId(Integer.parseInt(id));
            staticCameraCapabilities.setFacing(d(a));
            staticCameraCapabilities.setOrientation(e(a));
            staticCapabilities.add(staticCameraCapabilities);
        }
        return staticCapabilities;
    }

    private static String[] a() throws CaptureException {
        CameraManager cameraManager = (CameraManager) Platform.getInfo().getAppContext().getSystemService("camera");
        if (cameraManager == null) {
            Log.e(PackageInfo.TAG, "ERROR: cameraManager is null");
            throw new CaptureException("android.hardware.camera2.CameraManager NULL", ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE);
        }
        try {
            String[] cameraIds = cameraManager.getCameraIdList();
            for (String cameraId : cameraManager.getCameraIdList()) {
                if (Log.isLoggable(PackageInfo.TAG, 3)) {
                    Log.d(PackageInfo.TAG, "Camera Name: " + cameraId);
                }
            }
            return cameraIds;
        } catch (CameraAccessException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ", e);
            }
            throw new CaptureException("android.hardware.camera2.CameraAccessException", ErrorCode.ANDROID_CAMERA_RUNTIME_FAILURE);
        }
    }

    public static FpsRange mapFpsRange(Range<Integer> range) throws CaptureException {
        int min = ((Integer) range.getLower()).intValue();
        int max = ((Integer) range.getUpper()).intValue();
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "FpsRange, min:" + min + "  max:" + max);
            Log.d(PackageInfo.TAG, "converting as per camera1 expectation , min:" + (min * Constants.ONE_SECOND) + "  max:" + (max * Constants.ONE_SECOND));
        }
        return new FpsRange(min * Constants.ONE_SECOND, max * Constants.ONE_SECOND);
    }

    public static List<CameraCapabilities> obtain() throws CaptureException {
        List<CameraCapabilities> capabilities;
        boolean isLoaded = false;
        boolean isCollected = false;
        boolean isSaved = false;
        try {
            capabilities = CameraCapabilitiesUtils.load();
            isLoaded = true;
        } catch (SerializationFailedException e) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Could not load camera capabilities from file: " + e.getMessage());
            }
            capabilities = b();
            isCollected = true;
            try {
                CameraCapabilitiesUtils.save(capabilities);
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

    private static List<CameraCapabilities> b() throws CaptureException {
        List<CameraCapabilities> capabilities = new ArrayList();
        for (String id : a()) {
            CameraCharacteristics a = a(id);
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "getCameraCapabilities for cameraId: " + id);
            }
            CameraCapabilities cameraCapabilities = new CameraCapabilities();
            cameraCapabilities.setCameraId(Integer.parseInt(id));
            cameraCapabilities.setFacing(d(a));
            cameraCapabilities.setOrientation(e(a));
            NavigableSet treeSet = new TreeSet();
            treeSet.add(CameraCapabilitiesUtils.mapImageFormat(17));
            cameraCapabilities.setSupportedImageFormats(treeSet);
            cameraCapabilities.setSupportedResolutions(a(a));
            cameraCapabilities.setSupportedFpsRanges(b(a));
            cameraCapabilities.setSupportedFocusModes(c(a));
            Size[] outputSizes = ((StreamConfigurationMap) a.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class);
            cameraCapabilities.setNativeAspectRatio(((float) outputSizes[0].getWidth()) / ((float) outputSizes[0].getHeight()));
            capabilities.add(cameraCapabilities);
        }
        return capabilities;
    }

    private static NavigableSet<Resolution> a(CameraCharacteristics cameraInfo) throws CaptureException {
        NavigableSet<Resolution> resolutions = new TreeSet();
        Size[] sizes = ((StreamConfigurationMap) cameraInfo.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class);
        for (int i = 0; i < sizes.length; i++) {
            resolutions.add(new Resolution(sizes[i].getWidth(), sizes[i].getHeight()));
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "Resolutions, width: " + sizes[i].getWidth() + "height: " + sizes[i].getHeight());
            }
        }
        return resolutions;
    }

    private static NavigableSet<FpsRange> b(CameraCharacteristics cameraInfo) throws CaptureException {
        NavigableSet<FpsRange> fpsRanges = new TreeSet();
        for (Range<Integer> range : (Range[]) cameraInfo.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES)) {
            fpsRanges.add(mapFpsRange(range));
        }
        return fpsRanges;
    }

    private static NavigableSet<String> c(CameraCharacteristics cameraInfo) {
        NavigableSet<String> focusModes = new TreeSet();
        for (int focusMode : (int[]) cameraInfo.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES)) {
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "focusModes: " + String.valueOf(focusMode));
            }
            focusModes.add(String.valueOf(focusMode));
        }
        return focusModes;
    }

    private static Facing d(CameraCharacteristics cameraInfo) {
        switch (((Integer) cameraInfo.get(CameraCharacteristics.LENS_FACING)).intValue()) {
            case 0:
                return Facing.FRONT;
            case 1:
                return Facing.BACK;
            default:
                return Facing.OTHER;
        }
    }

    private static int e(CameraCharacteristics cameraInfo) {
        Integer orientation = (Integer) cameraInfo.get(CameraCharacteristics.SENSOR_ORIENTATION);
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "orientation:" + (360 - orientation.intValue()));
        }
        return 360 - orientation.intValue();
    }

    private static CameraCharacteristics a(String cameraId) throws CaptureException {
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "getCameraInfo starts");
        }
        try {
            return ((CameraManager) Platform.getInfo().getAppContext().getSystemService("camera")).getCameraCharacteristics(cameraId);
        } catch (Throwable e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ", e);
            }
            throw new CaptureException("Could not get CameraInfo for the camera " + cameraId, e, ErrorCode.ANDROID_CAMERA_GET_INFO_FAILED);
        } catch (Throwable e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ", e2);
            }
            throw new CaptureException("Could not get CameraInfo for the camera " + cameraId, e2, ErrorCode.ANDROID_CAMERA_GET_INFO_FAILED);
        }
    }
}
