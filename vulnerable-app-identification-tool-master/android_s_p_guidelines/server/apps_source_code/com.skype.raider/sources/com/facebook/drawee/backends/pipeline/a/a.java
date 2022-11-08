package com.facebook.drawee.backends.pipeline.a;

import com.facebook.drawee.backends.pipeline.info.b;

public final class a implements b {
    private int a = 1;

    public final void a(String controllerId, int imageOrigin, boolean successful) {
        this.a = imageOrigin;
    }

    public final String a() {
        switch (this.a) {
            case 2:
                return "network";
            case 3:
                return "disk";
            case 4:
                return "memory_encoded";
            case 5:
                return "memory_bitmap";
            case 6:
                return "local";
            default:
                return "unknown";
        }
    }
}
