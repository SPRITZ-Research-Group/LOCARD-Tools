package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.utils.Resolution;
import java.io.Serializable;

public class CameraParameters implements Serializable, Cloneable {
    private Resolution a;
    private ImageFormat b;
    private FpsRange c;
    private String d;

    public final Resolution getResolution() {
        return this.a;
    }

    public final void setResolution(Resolution resolution) {
        this.a = resolution;
    }

    public final ImageFormat getImageFormat() {
        return this.b;
    }

    public final void setImageFormat(ImageFormat imageFormat) {
        this.b = imageFormat;
    }

    public final FpsRange getFpsRange() {
        return this.c;
    }

    public final void setFpsRange(FpsRange fpsRange) {
        this.c = fpsRange;
    }

    public final String getFocusMode() {
        return this.d;
    }

    public final void setFocusMode(String focusMode) {
        this.d = focusMode;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.c == null ? 0 : this.c.hashCode()) + 31) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + (this.a == null ? 0 : this.a.hashCode())) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CameraParameters other = (CameraParameters) obj;
        if (this.a != null ? !this.a.equals(other.a) : other.a != null) {
            return false;
        }
        if (this.b != other.b) {
            return false;
        }
        if (this.c != null ? !this.c.equals(other.c) : other.c != null) {
            return false;
        }
        if (this.d == null) {
            if (other.d == null) {
                return true;
            }
        } else if (this.d.equals(other.d)) {
            return true;
        }
        return false;
    }

    public final CameraParameters clone() {
        try {
            CameraParameters clone = (CameraParameters) super.clone();
            clone.a = this.a;
            clone.b = this.b;
            clone.c = this.c;
            clone.d = this.d;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final String toString() {
        return this.a + " " + this.b + " " + this.c + " fps " + this.d;
    }
}
