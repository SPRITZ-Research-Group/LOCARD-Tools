package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class a implements b {
    private final List<b> a = new ArrayList(2);

    public a(b... imageOriginListeners) {
        Collections.addAll(this.a, imageOriginListeners);
    }

    public final synchronized void a(b listener) {
        this.a.add(listener);
    }

    public final synchronized void b(b listener) {
        this.a.remove(listener);
    }

    public final synchronized void a(String controllerId, int imageOrigin, boolean successful) {
        int numberOfListeners = this.a.size();
        for (int i = 0; i < numberOfListeners; i++) {
            b listener = (b) this.a.get(i);
            if (listener != null) {
                try {
                    listener.a(controllerId, imageOrigin, successful);
                } catch (Throwable e) {
                    FLog.e("ForwardingImageOriginListener", "InternalListener exception in onImageLoaded", e);
                }
            }
        }
    }
}
