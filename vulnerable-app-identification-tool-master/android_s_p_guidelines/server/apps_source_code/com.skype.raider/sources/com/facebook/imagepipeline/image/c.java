package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;
import java.io.Closeable;

public abstract class c implements ImageInfo, Closeable {
    public abstract boolean c();

    public abstract void close();

    public abstract int d();

    public g g() {
        return f.a;
    }

    public boolean e() {
        return false;
    }

    protected void finalize() throws Throwable {
        if (!c()) {
            FLog.w("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
