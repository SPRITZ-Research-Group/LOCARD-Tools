package com.microsoft.dl.video.capture;

import com.microsoft.dl.video.capture.api.FpsRange;
import com.microsoft.dl.video.capture.api.ImageFormat;
import com.microsoft.dl.video.capture.impl.ResolutionMatcher.ResolutionTransformation;
import java.util.Collection;
import java.util.EnumSet;

public class CapturerMode {
    private final ResolutionTransformation a;
    private final ImageFormat b;
    private final EnumSet<Orientation> c;
    private final Collection<FpsRange> d;
    private final FpsRange e;

    public enum Orientation {
        FlippedHorizontally,
        FlippedVertically,
        Transposed
    }

    public CapturerMode(ResolutionTransformation resolutionTransformation, ImageFormat format, EnumSet<Orientation> orientation, Collection<FpsRange> frameRateRanges, FpsRange absFrameRateRange) {
        this.a = resolutionTransformation;
        this.b = format;
        this.c = orientation;
        this.d = frameRateRanges;
        this.e = absFrameRateRange;
    }

    public final int getInnerWidth() {
        try {
            return this.a.getFrom().getWidth();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getInnerHeight() {
        try {
            return this.a.getFrom().getHeight();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getOuterWidth() {
        try {
            return this.a.getTo().getWidth();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getOuterHeight() {
        try {
            return this.a.getTo().getHeight();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getTransformation() {
        try {
            return this.a.getTransformation().ordinal();
        } catch (RuntimeException e) {
            return -1;
        }
    }

    public final int getFourCC() {
        try {
            return this.b.getFourCC();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final boolean isFlippedHorizontally() {
        try {
            return this.c.contains(Orientation.FlippedHorizontally);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public final boolean isFlippedVertically() {
        try {
            return this.c.contains(Orientation.FlippedVertically);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public final boolean isTransposed() {
        try {
            return this.c.contains(Orientation.Transposed);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public final int getMinFrameRate() {
        try {
            return this.e.getMin();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getMaxFrameRate() {
        try {
            return this.e.getMax();
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final int getBufferSize() {
        try {
            return this.b.getSampleSize(this.a.getFrom());
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public final ResolutionTransformation getResolutionTransformation() {
        return this.a;
    }

    public final ImageFormat getFormat() {
        return this.b;
    }

    public final EnumSet<Orientation> getOrientation() {
        return this.c;
    }

    public final Collection<FpsRange> getFrameRateRanges() {
        return this.d;
    }

    public final String toString() {
        return this.a + ", " + this.b + ", " + this.c + ", " + this.e + " fps " + (this.e.equals(this.d) ? "" : this.d);
    }
}
