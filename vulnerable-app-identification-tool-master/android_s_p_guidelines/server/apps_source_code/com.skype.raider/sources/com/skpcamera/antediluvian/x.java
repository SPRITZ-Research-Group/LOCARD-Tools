package com.skpcamera.antediluvian;

import android.graphics.SurfaceTexture;
import android.view.Surface;

public final class x extends n {
    private Surface b;
    private boolean c;

    public x(m eglCore, Surface surface) {
        super(eglCore);
        a((Object) surface);
        this.b = surface;
        this.c = true;
    }

    public x(m eglCore, SurfaceTexture surfaceTexture) {
        super(eglCore);
        a((Object) surfaceTexture);
    }

    public final void f() {
        c();
        if (this.b != null) {
            if (this.c) {
                this.b.release();
            }
            this.b = null;
        }
    }
}
