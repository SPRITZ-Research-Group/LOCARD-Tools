package com.skype.callmonitor;

import com.skype.slimcore.video.VideoViewManagerProvider;
import java.lang.ref.WeakReference;

public class CallMonitorStorage {
    private static CallMonitorStorage a = null;
    private WeakReference<VideoViewManagerProvider> b;

    public final WeakReference<VideoViewManagerProvider> a() {
        return this.b;
    }

    public final void a(WeakReference<VideoViewManagerProvider> videoViewManagerProvider) {
        this.b = videoViewManagerProvider;
    }

    protected CallMonitorStorage() {
    }

    public static CallMonitorStorage b() {
        if (a == null) {
            a = new CallMonitorStorage();
        }
        return a;
    }
}
