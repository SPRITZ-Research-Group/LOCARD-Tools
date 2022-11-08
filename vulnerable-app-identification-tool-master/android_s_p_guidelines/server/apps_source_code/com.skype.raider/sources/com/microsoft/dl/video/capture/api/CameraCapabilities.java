package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.utils.Resolution;
import java.util.NavigableSet;
import java.util.TreeSet;

public class CameraCapabilities extends StaticCameraCapabilities {
    private NavigableSet<ImageFormat> d;
    private NavigableSet<Resolution> e;
    private NavigableSet<FpsRange> f;
    private NavigableSet<String> g;
    private float h;

    public enum Facing {
        BACK,
        FRONT,
        OTHER
    }

    public final NavigableSet<ImageFormat> getSupportedImageFormats() {
        return this.d;
    }

    public final void setSupportedImageFormats(NavigableSet<ImageFormat> supportedImageFormats) {
        this.d = supportedImageFormats;
    }

    public final NavigableSet<Resolution> getSupportedResolutions() {
        return this.e;
    }

    public final void setSupportedResolutions(NavigableSet<Resolution> supportedResolutions) {
        this.e = supportedResolutions;
    }

    public final NavigableSet<FpsRange> getSupportedFpsRanges() {
        return this.f;
    }

    public final void setSupportedFpsRanges(NavigableSet<FpsRange> supportedFpsRanges) {
        this.f = supportedFpsRanges;
    }

    public final NavigableSet<String> getSupportedFocusModes() {
        return this.g;
    }

    public final void setSupportedFocusModes(NavigableSet<String> supportedFocusModes) {
        this.g = supportedFocusModes;
    }

    public final float getNativeAspectRatio() {
        return this.h;
    }

    public final void setNativeAspectRatio(float nativeAspectRatio) {
        this.h = nativeAspectRatio;
    }

    public final CameraCapabilities clone() {
        CameraCapabilities clone = (CameraCapabilities) super.clone();
        clone.d = new TreeSet(this.d);
        clone.e = new TreeSet(this.e);
        clone.f = new TreeSet(this.f);
        clone.g = new TreeSet(this.g);
        return clone;
    }

    public final String toString() {
        return getClass().getSimpleName() + " [cameraId=" + this.a + ", facing=" + this.b + ", orientation=" + this.c + ", supportedImageFormats=" + this.d + ", supportedResolutions=" + this.e + ", supportedFpsRanges=" + this.f + ", supportedFocusModes=" + this.g + ", nativeAspectRatio=" + this.h + "]";
    }
}
