package com.microsoft.dl.video.capture.api;

import com.microsoft.dl.video.capture.api.CameraCapabilities.Facing;
import java.io.Serializable;

public class StaticCameraCapabilities implements Serializable, Cloneable {
    protected int a;
    protected Facing b;
    protected int c;

    public final int getCameraId() {
        return this.a;
    }

    public final void setCameraId(int cameraId) {
        this.a = cameraId;
    }

    public final Facing getFacing() {
        return this.b;
    }

    public final void setFacing(Facing facing) {
        this.b = facing;
    }

    public final int getOrientation() {
        return this.c;
    }

    public final void setOrientation(int orientation) {
        this.c = orientation;
    }

    public StaticCameraCapabilities clone() {
        try {
            return (StaticCameraCapabilities) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [cameraId=" + this.a + ", facing=" + this.b + ", orientation=" + this.c + "]";
    }
}
