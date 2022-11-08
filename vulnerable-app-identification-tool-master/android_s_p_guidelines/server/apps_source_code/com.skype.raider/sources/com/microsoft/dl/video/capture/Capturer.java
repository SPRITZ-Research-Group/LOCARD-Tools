package com.microsoft.dl.video.capture;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.ErrorCodeException;
import com.microsoft.dl.video.Failure;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.CapturerConfiguration.ResolutionParameters;
import com.microsoft.dl.video.capture.CapturerMode.Orientation;
import com.microsoft.dl.video.capture.api.CameraCapabilities;
import com.microsoft.dl.video.capture.api.CameraCapabilities.Facing;
import com.microsoft.dl.video.capture.api.CameraManagerSingleton;
import com.microsoft.dl.video.capture.api.CameraParameters;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.capture.api.ImageFormat;
import com.microsoft.dl.video.capture.impl.CaptureWorker;
import com.microsoft.dl.video.capture.impl.CaptureWorker.CallbackType;
import com.microsoft.dl.video.capture.impl.FpsRangeComparator;
import com.microsoft.dl.video.capture.impl.ResolutionMatcher;
import com.microsoft.dl.video.capture.impl.ResolutionMatcher.ResolutionTransformation;
import com.microsoft.dl.video.capture.impl.ResolutionMatcher.TransformationAllowed;
import com.microsoft.dl.video.graphics.GraphicsException;
import com.microsoft.dl.video.utils.Resolution;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Capturer {
    private final String a;
    private final CameraCapabilities b;
    private final List<CapturerMode> c;
    private final CaptureWorker d;
    private final Thread e;
    private State f = State.STOPPED;

    public enum State {
        STOPPED,
        START_REQUESTED_MISSING_PARAM,
        STARTED
    }

    private Capturer(int cameraId, long nativeContext, CapturerConfiguration parameters, String debugName) throws CaptureException {
        this.a = debugName;
        this.b = CameraManagerSingleton.getInstance().getCameraCapabilities(cameraId);
        this.c = a(this.b, parameters, debugName);
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            int i = 0;
            for (CapturerMode mode : this.c) {
                int i2 = i + 1;
                Log.i(PackageInfo.TAG, "Capturing mode " + i + ": " + mode + " (" + debugName + ")");
                i = i2;
            }
        }
        this.d = new CaptureWorker(this.b.getCameraId(), parameters.getNumBuffers(), nativeContext, parameters.isUseDummyPreviewSurface(), debugName);
        this.e = new Thread(this.d);
    }

    public static Object create(int cameraId, long nativeContext, CapturerConfiguration parameters, String debugName, long timeoutMs) {
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Creating the capturer (" + debugName + ")");
        }
        try {
            Capturer capturer = new Capturer(cameraId, nativeContext, parameters, debugName);
            capturer.e.start();
            if (!capturer.d.isOpen(timeoutMs)) {
                throw new CaptureException("Could not open the camera in time", ErrorCode.ANDROID_CAMERA_OPEN_TIMEOUT);
            } else if (!Log.isLoggable(PackageInfo.TAG, 4)) {
                return capturer;
            } else {
                Log.i(PackageInfo.TAG, "Capturer created and initialized successfully (" + debugName + ")");
                return capturer;
            }
        } catch (Throwable e) {
            throw new CaptureException(e, ErrorCode.ANDROID_CAMERA_OPEN_INTERRUPTED);
        } catch (ErrorCodeException e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ( (" + debugName + ")", e2);
            }
            return Long.valueOf(new Failure(e2).getNativeContext());
        } catch (RuntimeException e3) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught ( (" + debugName + ")", e3);
            }
            return Long.valueOf(new Failure(e3).getNativeContext());
        }
    }

    public final synchronized long shutdown(long timeoutMs) {
        long j;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Shutting down the capturer (" + this.a + ")");
        }
        try {
            if (this.d != null) {
                this.d.close();
                if (this.e != null && this.e.isAlive()) {
                    if (Log.isLoggable(PackageInfo.TAG, 4)) {
                        Log.i(PackageInfo.TAG, "Waiting until worker thread exit (" + this.a + ")");
                    }
                    this.e.join(timeoutMs);
                    if (this.e.isAlive()) {
                        throw new CaptureException("Could not close the camera in time", ErrorCode.ANDROID_CAMERA_CLOSE_TIMEOUT);
                    }
                }
            }
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "The capturer shut down successfully (" + this.a + ")");
            }
            j = 0;
        } catch (Throwable e) {
            throw new CaptureException(e, ErrorCode.ANDROID_CAMERA_CLOSE_INTERRUPTED);
        } catch (ErrorCodeException e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e2);
            }
            j = new Failure(e2).getNativeContext();
        } catch (RuntimeException e3) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e3);
            }
            j = new Failure(e3).getNativeContext();
        }
        return j;
    }

    public final synchronized int getNumModes() {
        return this.c.size();
    }

    public final synchronized CapturerMode getMode(int i) {
        CapturerMode capturerMode;
        if (i >= 0) {
            if (i < this.c.size()) {
                capturerMode = (CapturerMode) this.c.get(i);
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Thre is no mode #" + i + " (" + this.a + ")");
        }
        capturerMode = null;
        return capturerMode;
    }

    public final synchronized boolean isRunning() {
        boolean z;
        z = this.e.isAlive() && this.f == State.STARTED;
        return z;
    }

    public final synchronized long startMode(int modeId, int fps) {
        ErrorCodeException e;
        long j = 0;
        synchronized (this) {
            CapturerMode modeResult = getMode(modeId);
            if (modeResult instanceof Failure) {
                j = ((Failure) modeResult).getNativeContext();
            } else {
                CapturerMode mode = modeResult;
                CameraParameters cameraParameters = new CameraParameters();
                cameraParameters.setImageFormat(mode.getFormat());
                cameraParameters.setResolution(mode.getResolutionTransformation().getFrom());
                cameraParameters.setFpsRange(findFpsRange(fps, mode.getFrameRateRanges()));
                if (this.b.getSupportedFocusModes().contains("continuous-video")) {
                    cameraParameters.setFocusMode("continuous-video");
                } else if (this.b.getSupportedFocusModes().contains("continuous-picture")) {
                    cameraParameters.setFocusMode("continuous-picture");
                }
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "Starting capturing mode " + modeId + " [" + mode + "], " + (((float) fps) / 1000.0f) + " fps, CameraParameters: " + cameraParameters + " (" + this.a + ")");
                }
                try {
                    if (this.f == State.STARTED) {
                        if (this.d.shouldUpdateParameters(cameraParameters, modeId)) {
                            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                                Log.i(PackageInfo.TAG, "Restarting the capturer in order to apply capturing mode change (" + this.a + ")");
                            }
                            this.d.stop();
                        }
                    }
                    this.d.setParameters(cameraParameters, modeId);
                    this.d.setFramerate(fps);
                    this.f = this.d.start() ? State.STARTED : State.START_REQUESTED_MISSING_PARAM;
                } catch (ErrorCodeException e2) {
                    e = e2;
                } catch (ErrorCodeException e22) {
                    e = e22;
                } catch (RuntimeException e3) {
                    if (Log.isLoggable(PackageInfo.TAG, 6)) {
                        Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e3);
                    }
                    j = new Failure(e3).getNativeContext();
                }
            }
        }
        return j;
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e);
        }
        j = new Failure(e).getNativeContext();
        return j;
    }

    public final synchronized long stop() {
        long j;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Stopping capturing (" + this.a + ")");
        }
        try {
            if (this.f == State.STARTED) {
                this.d.stop();
            } else if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Capturing is already stopped (" + this.a + ")");
            }
            this.f = State.STOPPED;
            j = 0;
        } catch (ErrorCodeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e);
            }
            j = new Failure(e).getNativeContext();
        } catch (RuntimeException e2) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e2);
            }
            j = new Failure(e2).getNativeContext();
        }
        return j;
    }

    public final synchronized long setPreview(Object previewDisplay) {
        ErrorCodeException e;
        long j = 0;
        synchronized (this) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting preview display to " + previewDisplay);
            }
            try {
                if (this.f == State.STARTED) {
                    if (this.d.shouldUpdatePreviewDisplay(previewDisplay)) {
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "Restarting the capturer in order to apply preview display change (" + this.a + ")");
                        }
                        this.d.stop();
                    }
                }
                this.d.setPreviewDisplay(previewDisplay);
                if (this.f != State.STOPPED) {
                    this.f = this.d.start() ? State.STARTED : State.START_REQUESTED_MISSING_PARAM;
                }
            } catch (ErrorCodeException e2) {
                e = e2;
            } catch (ErrorCodeException e22) {
                e = e22;
            } catch (RuntimeException e3) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e3);
                }
                j = new Failure(e3).getNativeContext();
            }
        }
        return j;
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e);
        }
        j = new Failure(e).getNativeContext();
        return j;
    }

    public final synchronized long setOffScreenPreview() throws GraphicsException {
        long preview;
        try {
            preview = setPreview(this.d.getOffscreenPreviewSurface());
        } catch (ErrorCodeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e);
            }
            preview = new Failure(e).getNativeContext();
        }
        return preview;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long setPreviewOrientation(int angle) {
        long j;
        ErrorCodeException e;
        int orientationAngle;
        switch (this.b.getFacing()) {
            case FRONT:
                orientationAngle = angle % 360;
            default:
                orientationAngle = (360 - angle) % 360;
        }
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Setting preview display orientation to " + orientationAngle + "/" + angle + " degrees (" + this.a + ")");
        }
        try {
            this.d.setOrientationAngle(orientationAngle);
            if (this.f == State.START_REQUESTED_MISSING_PARAM) {
                this.f = this.d.start() ? State.STARTED : State.START_REQUESTED_MISSING_PARAM;
            }
            j = 0;
        } catch (ErrorCodeException e2) {
            e = e2;
        } catch (ErrorCodeException e22) {
            e = e22;
        } catch (RuntimeException e3) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e3);
            }
            j = new Failure(e3).getNativeContext();
        }
        return j;
        if (Log.isLoggable(PackageInfo.TAG, 6)) {
            Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e);
        }
        j = new Failure(e).getNativeContext();
        return j;
    }

    public final synchronized long setUseGpuCallback(boolean useGpuCallback) {
        ErrorCodeException e;
        long j = 0;
        synchronized (this) {
            CallbackType callbackType = useGpuCallback ? CallbackType.GPU : CallbackType.CPU;
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "Setting callback type to " + callbackType.name() + " (" + this.a + ")");
            }
            try {
                if (this.f == State.STARTED) {
                    if (this.d.shouldUpdateCallbackType(callbackType)) {
                        if (Log.isLoggable(PackageInfo.TAG, 4)) {
                            Log.i(PackageInfo.TAG, "Restarting the capturer in order to apply callback type change (" + this.a + ")");
                        }
                        this.d.stop();
                    }
                }
                this.d.setCallbackType(callbackType);
                if (this.f != State.STOPPED) {
                    this.f = this.d.start() ? State.STARTED : State.START_REQUESTED_MISSING_PARAM;
                }
            } catch (ErrorCodeException e2) {
                e = e2;
            } catch (ErrorCodeException e22) {
                e = e22;
            } catch (RuntimeException e3) {
                if (Log.isLoggable(PackageInfo.TAG, 6)) {
                    Log.e(PackageInfo.TAG, "Exception caught (" + this.a + ")", e3);
                }
                j = new Failure(e3).getNativeContext();
            }
        }
        return j;
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Restarting the capturer in order to apply callback type change (" + this.a + ")");
        }
        j = new Failure(e).getNativeContext();
        return j;
    }

    public FpsRange findFpsRange(int fps, Collection<FpsRange> candidates) {
        FpsRange bestRange = (FpsRange) Collections.min(candidates, new FpsRangeComparator(fps));
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "Frame rate matching " + (((float) fps) / 1000.0f) + " found: " + bestRange + " fps, candidates: " + candidates + " (" + this.a + ")");
        }
        return bestRange;
    }

    private static List<CapturerMode> a(CameraCapabilities capabilities, CapturerConfiguration parameters, String debugName) throws CaptureException {
        EnumSet<Orientation> orientation;
        Set<Resolution> cameraResolutions = new HashSet(capabilities.getSupportedResolutions());
        cameraResolutions.removeAll(parameters.getBannedCameraResolution());
        ResolutionMatcher resolutionMatcher = new ResolutionMatcher(cameraResolutions, capabilities.getNativeAspectRatio());
        Facing facing = capabilities.getFacing();
        int orientation2 = capabilities.getOrientation() % 360;
        switch (facing) {
            case FRONT:
                switch (orientation2) {
                    case 0:
                        orientation = EnumSet.of(Orientation.FlippedHorizontally);
                        break;
                    case 90:
                        orientation = EnumSet.of(Orientation.FlippedVertically, Orientation.FlippedHorizontally, Orientation.Transposed);
                        break;
                    case 180:
                        orientation = EnumSet.of(Orientation.FlippedVertically);
                        break;
                    case 270:
                        orientation = EnumSet.of(Orientation.Transposed);
                        break;
                    default:
                        throw new CaptureException("unsupported mountingAngle=" + orientation2, ErrorCode.ANDROID_CAPTURER_INVALID_ORIENTATION);
                }
            case BACK:
            case OTHER:
                switch (orientation2) {
                    case 0:
                        orientation = EnumSet.noneOf(Orientation.class);
                        break;
                    case 90:
                        orientation = EnumSet.of(Orientation.FlippedVertically, Orientation.Transposed);
                        break;
                    case 180:
                        orientation = EnumSet.of(Orientation.FlippedVertically, Orientation.FlippedHorizontally);
                        break;
                    case 270:
                        orientation = EnumSet.of(Orientation.FlippedVertically, Orientation.Transposed);
                        break;
                    default:
                        throw new CaptureException("unsupported mountingAngle=" + orientation2, ErrorCode.ANDROID_CAPTURER_INVALID_ORIENTATION);
                }
            default:
                throw new CaptureException("unsupported facing=" + facing, ErrorCode.ANDROID_CAPTURER_INVALID_FACING);
        }
        NavigableSet<FpsRange> fpsRanges = a(parameters.getAbsFpsRange(), capabilities.getSupportedFpsRanges());
        if (fpsRanges.isEmpty()) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "None of the camera fps ranges " + capabilities.getSupportedFpsRanges() + " meets requirements of " + parameters.getAbsFpsRange());
            }
            return Collections.emptyList();
        }
        FpsRange absFpsRange = new FpsRange(((FpsRange) Collections.min(fpsRanges, new Comparator<FpsRange>() {
            public final int compare(FpsRange left, FpsRange right) {
                if (left.getMin() < right.getMin()) {
                    return -1;
                }
                return left.getMin() > right.getMin() ? 1 : 0;
            }
        })).getMin(), ((FpsRange) Collections.max(fpsRanges, new Comparator<FpsRange>() {
            public final int compare(FpsRange left, FpsRange right) {
                if (left.getMax() < right.getMax()) {
                    return -1;
                }
                return left.getMax() > right.getMax() ? 1 : 0;
            }
        })).getMax());
        List<CapturerMode> modes = new ArrayList();
        for (Entry<Resolution, ResolutionParameters> outputResolution : parameters.getOutputResolutions()) {
            EnumSet<TransformationAllowed> allowedTransformations;
            Set transformationOptions = parameters.getTransformationOptions();
            boolean isMandatory = ((ResolutionParameters) outputResolution.getValue()).isMandatory();
            Collection arrayList = new ArrayList(2);
            if ((isMandatory && transformationOptions.contains(TransformationOptions.AllowCroppingMandatoryResolutions)) || transformationOptions.contains(TransformationOptions.AllowCroppingAnyResolution)) {
                arrayList.add(TransformationAllowed.Cropping);
            }
            if ((isMandatory && transformationOptions.contains(TransformationOptions.AllowScalingMandatoryResolutions)) || transformationOptions.contains(TransformationOptions.AllowScalingAnyResolution)) {
                arrayList.add(TransformationAllowed.AllScaling);
            } else if ((isMandatory && transformationOptions.contains(TransformationOptions.AllowMultipleScalingMandatoryResolutions)) || transformationOptions.contains(TransformationOptions.AllowMultipleScalingAnyResolution)) {
                arrayList.add(TransformationAllowed.MultipleScaling);
            }
            if (arrayList.isEmpty()) {
                allowedTransformations = EnumSet.noneOf(TransformationAllowed.class);
            } else {
                allowedTransformations = EnumSet.copyOf(arrayList);
            }
            ResolutionTransformation resolutionTransformation = resolutionMatcher.findBest((Resolution) outputResolution.getKey(), ((ResolutionParameters) outputResolution.getValue()).isMandatory() ? Float.POSITIVE_INFINITY : ((float) parameters.getMaxTransformationZoom()) / 100.0f, ((ResolutionParameters) outputResolution.getValue()).isMandatory() ? Float.POSITIVE_INFINITY : ((float) parameters.getMaxTransformationCrop()) / 100.0f, allowedTransformations);
            if (resolutionTransformation != null) {
                for (ImageFormat imageFormat : capabilities.getSupportedImageFormats()) {
                    modes.add(new CapturerMode(resolutionTransformation, imageFormat, orientation, fpsRanges, absFpsRange));
                }
            } else if (((ResolutionParameters) outputResolution.getValue()).isMandatory()) {
                throw new CaptureException("Coud not support mandaroty resolution " + outputResolution.getKey() + " (" + debugName + ")", ErrorCode.ANDROID_CAPTURER_MISSING_MANDATORY_RESOLUTION);
            }
        }
        return modes;
    }

    private static NavigableSet<FpsRange> a(FpsRange absRange, Iterable<FpsRange> candidates) {
        NavigableSet<FpsRange> result = new TreeSet();
        for (FpsRange candidate : candidates) {
            if (candidate.getMin() >= absRange.getMin() && candidate.getMax() <= absRange.getMax()) {
                result.add(candidate);
            }
        }
        return result;
    }
}
