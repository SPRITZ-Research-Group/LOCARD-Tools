package com.skypecam.obscura.c;

import android.view.Surface;

public final class m extends c {
    private Surface b;
    private boolean c;

    public m(b eglCore, Object surface) {
        super(eglCore);
        a(surface);
    }

    public final void e() {
        a();
        if (this.b != null) {
            if (this.c) {
                this.b.release();
            }
            this.b = null;
        }
    }
}
