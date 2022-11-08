package com.skpcamera.antediluvian;

import android.hardware.Camera.CameraInfo;
import com.facebook.common.logging.FLog;

final class h {
    public final CameraInfo a;
    public int b = -1;
    public int c = -1;
    private int d = 0;

    public h(CameraInfo info) {
        this.a = info;
    }

    public final void a(int rotation) {
        if (rotation == 0 || rotation == 90 || rotation == 180 || rotation == 270) {
            this.d = rotation;
        } else {
            FLog.e("SkypeCameraInfoWrapper", "Rotation was not set to a valid value : " + rotation);
        }
    }

    public final int a() {
        return this.d;
    }
}
