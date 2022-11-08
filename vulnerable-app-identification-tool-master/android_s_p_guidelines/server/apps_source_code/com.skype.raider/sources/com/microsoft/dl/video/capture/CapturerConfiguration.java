package com.microsoft.dl.video.capture;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.utils.Resolution;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class CapturerConfiguration {
    private FpsRange a;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private final Set<Resolution> f = new HashSet();
    private final NavigableMap<Resolution, ResolutionParameters> g = new TreeMap();
    private final Set<TransformationOptions> h = new HashSet();

    public static class ResolutionParameters {
        private final boolean a;

        public ResolutionParameters(boolean isMandatory) {
            this.a = isMandatory;
        }

        public final boolean isMandatory() {
            return this.a;
        }

        public final String toString() {
            return this.a ? "mandatory" : "optional";
        }
    }

    enum TransformationOptions {
        AllowCroppingMandatoryResolutions,
        AllowMultipleScalingMandatoryResolutions,
        AllowScalingMandatoryResolutions,
        AllowCroppingAnyResolution,
        AllowMultipleScalingAnyResolution,
        AllowScalingAnyResolution
    }

    public final void setAbsFpsRange(int min, int max) {
        try {
            this.a = new FpsRange(min, max);
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
        }
    }

    public final void setMaxTransformationZoom(int maxTransformationZoom) {
        this.b = maxTransformationZoom;
    }

    public final void setMaxTransformationCrop(int maxTransformationCrop) {
        this.c = maxTransformationCrop;
    }

    public final void setNumBuffers(int numBuffers) {
        this.d = numBuffers;
    }

    public final void setUseDummyPreviewSurface(boolean useDummyPreviewSurface) {
        this.e = useDummyPreviewSurface;
    }

    public final boolean addOutputResolution(int width, int height, boolean isMandatory) {
        try {
            this.g.put(new Resolution(width, height), new ResolutionParameters(isMandatory));
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return false;
        }
    }

    public final boolean banCameraResolution(int width, int height) {
        try {
            this.f.add(new Resolution(width, height));
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return false;
        }
    }

    public final boolean addTransformationOption(int option) {
        try {
            this.h.add(TransformationOptions.values()[option]);
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Exception caught", e);
            }
            return false;
        }
    }

    public final FpsRange getAbsFpsRange() {
        return this.a;
    }

    public final int getMaxTransformationZoom() {
        return this.b;
    }

    public final int getMaxTransformationCrop() {
        return this.c;
    }

    public final int getNumBuffers() {
        return this.d;
    }

    public final boolean isUseDummyPreviewSurface() {
        return this.e;
    }

    public final Iterable<Entry<Resolution, ResolutionParameters>> getOutputResolutions() {
        return this.g.entrySet();
    }

    public final Set<Resolution> getBannedCameraResolution() {
        return this.f;
    }

    public final Set<TransformationOptions> getTransformationOptions() {
        return this.h;
    }

    public final String toString() {
        return getClass().getSimpleName() + " [absFpsRange=" + this.a + ", maxTransformationZoom=" + this.b + ", maxTransformationCrop=" + this.c + ", outputRresolutions=" + this.g + ", numBuffers=" + this.d + ", bannedCameraResolution=" + this.f + ", transformationOptions=" + this.h + "]";
    }
}
