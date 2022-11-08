package com.skypecam.obscura.b;

import android.hardware.Camera.CameraInfo;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.j;

public final class f {
    final int a;
    final CameraInfo b;
    private int c = 0;
    private j d;

    f(CameraInfo info, int index) {
        this.b = info;
        this.a = index;
    }

    final void a(int rotation) {
        if (rotation == 0 || rotation == 90 || rotation == 180 || rotation == 270) {
            this.c = rotation;
        } else {
            g.a().d("CameraInfoWrapper", "Rotation was not set to a valid value : " + rotation);
        }
    }

    final void a(int width, int height) {
        if (this.c == 0 || this.c == 180) {
            this.d = new j(width, height);
        } else {
            this.d = new j(height, width);
        }
    }

    final int a() {
        return this.c;
    }

    final j b() {
        return this.d;
    }
}
