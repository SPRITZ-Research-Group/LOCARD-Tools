package com.skype.slimcore.skylib;

import com.microsoft.dl.audio.AudioPlatform.clientCallback;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PcmHostCallback extends clientCallback {
    private Set<Listener> a = new CopyOnWriteArraySet();

    public interface Listener {
        void a();

        void a(boolean z, String str);
    }

    public final boolean a(Listener listener) {
        if (listener != null) {
            return this.a.add(listener);
        }
        return false;
    }

    public final boolean b(Listener listener) {
        if (listener != null) {
            return this.a.remove(listener);
        }
        return false;
    }

    private void a(boolean success, String audioRoute) {
        for (Listener a : this.a) {
            a.a(success, audioRoute);
        }
    }

    public void callbackWithError(String audioRoute) {
        super.callbackWithError(audioRoute);
        a(false, audioRoute);
    }

    public void callbackWithoutError(String audioRoute) {
        super.callbackWithoutError(audioRoute);
        a(true, audioRoute);
    }

    public void callbackStopRingoutTone() {
        super.callbackStopRingoutTone();
        for (Listener a : this.a) {
            a.a();
        }
    }
}
