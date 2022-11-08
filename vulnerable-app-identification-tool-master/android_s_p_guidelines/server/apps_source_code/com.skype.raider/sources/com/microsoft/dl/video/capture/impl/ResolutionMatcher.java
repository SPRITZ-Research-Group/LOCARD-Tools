package com.microsoft.dl.video.capture.impl;

import com.microsoft.dl.utils.Log;
import com.microsoft.dl.video.ErrorCode;
import com.microsoft.dl.video.PackageInfo;
import com.microsoft.dl.video.capture.api.CaptureException;
import com.microsoft.dl.video.utils.Resolution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ResolutionMatcher {
    private final Set<Resolution> a;
    private final float b;

    public static class ResolutionTransformation {
        private final Resolution a;
        private final Resolution b;
        private final Transformation c;

        public ResolutionTransformation(Resolution from, Resolution to, Transformation transformation) {
            this.a = from;
            this.b = to;
            this.c = transformation;
        }

        public Resolution getFrom() {
            return this.a;
        }

        public Resolution getTo() {
            return this.b;
        }

        public Transformation getTransformation() {
            return this.c;
        }

        public String toString() {
            return this.a + "->" + this.b + " " + this.c;
        }
    }

    private static class EstimatedResolutionTransformation extends ResolutionTransformation implements Comparable<EstimatedResolutionTransformation> {
        private final Estimates a;

        public EstimatedResolutionTransformation(Resolution from, Resolution to, Transformation transformation, Estimates estimates) {
            super(from, to, transformation);
            this.a = estimates;
        }

        public Estimates getEstimates() {
            return this.a;
        }

        private static int a(float f) {
            return ((double) Math.abs(f)) < 0.01d ? 0 : (int) Math.signum(f);
        }

        public int compareTo(EstimatedResolutionTransformation another) {
            int zoomSignum = a(getEstimates().getZoom() - another.getEstimates().getZoom());
            if (zoomSignum != 0) {
                return zoomSignum;
            }
            int cropSignum = a(getEstimates().getCrop() - another.getEstimates().getCrop());
            if (cropSignum != 0) {
                return cropSignum;
            }
            return Integer.signum((getFrom().getWidth() * getFrom().getHeight()) - (another.getFrom().getWidth() * another.getFrom().getHeight()));
        }

        public String toString() {
            return super.toString() + " (" + this.a + ")";
        }
    }

    private static class Estimates {
        private final float a;
        private final float b;

        public Estimates() {
            this.a = -1.0f;
            this.b = -1.0f;
        }

        public Estimates(float zoom, float crop) throws CaptureException {
            if (zoom < 0.0f || crop < 0.0f) {
                throw new CaptureException("zoom=" + zoom + ", crop=" + crop + " must not have negative values", ErrorCode.ANDROID_CAPTURER_INVALID_ZOOM_CROP);
            }
            this.a = zoom;
            this.b = crop;
        }

        public boolean isValid() {
            return this.a >= 0.0f && this.a < Float.MAX_VALUE && this.b >= 0.0f && this.b < Float.MAX_VALUE;
        }

        public float getZoom() {
            return this.a;
        }

        public float getCrop() {
            return this.b;
        }

        public String toString() {
            return isValid() ? "zoom=" + ((int) (this.a * 100.0f)) + "%, crop=" + ((int) (this.b * 100.0f)) + "%" : "invalid";
        }
    }

    public enum Transformation {
        Match,
        Crop,
        Scale
    }

    public enum TransformationAllowed {
        Cropping,
        MultipleScaling,
        AllScaling
    }

    public ResolutionMatcher(Set<Resolution> cameraResolutions, float nativeCameraAR) {
        this.a = cameraResolutions;
        this.b = nativeCameraAR;
    }

    public ResolutionTransformation findBest(Resolution resoluton, float maxZoom, float maxCrop, EnumSet<TransformationAllowed> transformationsAllowed) throws CaptureException {
        List<EstimatedResolutionTransformation> candidates = new ArrayList(this.a.size());
        for (Resolution resolution : this.a) {
            if (resoluton.equals(resolution)) {
                candidates.add(new EstimatedResolutionTransformation(resolution, resoluton, Transformation.Match, a(resolution, resoluton)));
            } else if (resolution.getWidth() >= resoluton.getWidth() && resolution.getHeight() >= resoluton.getHeight()) {
                if (transformationsAllowed.contains(TransformationAllowed.Cropping)) {
                    candidates.add(new EstimatedResolutionTransformation(resolution, resoluton, Transformation.Crop, a(resolution, resoluton)));
                }
                if (transformationsAllowed.contains(TransformationAllowed.AllScaling) || transformationsAllowed.contains(TransformationAllowed.MultipleScaling)) {
                    candidates.add(new EstimatedResolutionTransformation(resolution, resoluton, Transformation.Scale, new Estimates(Float.MAX_VALUE, Float.MAX_VALUE)));
                }
            }
        }
        if (candidates.isEmpty()) {
            return null;
        }
        Collections.sort(candidates);
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "Resolution preferences for " + resoluton + ": " + candidates);
        }
        for (EstimatedResolutionTransformation candidate : candidates) {
            if (candidate.getEstimates().getZoom() <= maxZoom && candidate.getEstimates().getCrop() <= maxCrop) {
                return candidate;
            }
        }
        if (Log.isLoggable(PackageInfo.TAG, 5)) {
            Log.w(PackageInfo.TAG, "No matching found for " + resoluton + " with max zoom=" + ((int) (maxZoom * 100.0f)) + "%, crop=" + ((int) (maxCrop * 100.0f)) + "%");
        }
        return null;
    }

    private Estimates a(Resolution inputResolution, Resolution outputResoluton) throws CaptureException {
        int inputPixels;
        float origAspectRatio = ((float) inputResolution.getWidth()) / ((float) inputResolution.getHeight());
        int nativeWidth = origAspectRatio < this.b ? (int) (((float) inputResolution.getHeight()) * this.b) : inputResolution.getWidth();
        int nativeHeight = origAspectRatio > this.b ? (int) (((float) inputResolution.getWidth()) / this.b) : inputResolution.getHeight();
        int cropWidth = nativeWidth - ((outputResoluton.getWidth() * nativeHeight) / outputResoluton.getHeight());
        int cropHeight = nativeHeight - ((outputResoluton.getHeight() * nativeWidth) / outputResoluton.getWidth());
        int outputPixels = outputResoluton.getWidth() * outputResoluton.getHeight();
        float crop = 0.0f;
        if (cropWidth > 0) {
            inputPixels = (nativeWidth - cropWidth) * nativeHeight;
            crop = ((float) cropWidth) / ((float) nativeWidth);
        } else if (cropHeight > 0) {
            inputPixels = (nativeHeight - cropHeight) * nativeWidth;
            crop = ((float) cropHeight) / ((float) nativeHeight);
        } else if (cropWidth == 0 && cropHeight == 0) {
            inputPixels = nativeWidth * nativeHeight;
        } else {
            if (Log.isLoggable(PackageInfo.TAG, 6)) {
                Log.e(PackageInfo.TAG, "Invariant failed: inputResolution=" + inputResolution + ", outputResoluton=" + outputResoluton + ", nativeCameraAR=" + this.b + ", cropWidth=" + cropWidth + ", cropHeight" + cropHeight);
            }
            return new Estimates();
        }
        return new Estimates(inputPixels > 0 ? ((float) (inputPixels - outputPixels)) / ((float) inputPixels) : 0.0f, crop);
    }
}
