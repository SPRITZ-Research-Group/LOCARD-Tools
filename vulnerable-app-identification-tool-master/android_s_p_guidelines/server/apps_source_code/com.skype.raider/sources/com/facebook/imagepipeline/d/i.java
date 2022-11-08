package com.facebook.imagepipeline.d;

import android.app.ActivityManager;
import android.os.Build.VERSION;
import com.facebook.common.internal.j;
import com.skype.Defines;

public final class i implements j<q> {
    private final ActivityManager a;

    public final /* synthetic */ Object a() {
        int min = Math.min(this.a.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            min = 4194304;
        } else if (min < 67108864) {
            min = 6291456;
        } else if (VERSION.SDK_INT < 11) {
            min = 8388608;
        } else {
            min /= 4;
        }
        return new q(min, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public i(ActivityManager activityManager) {
        this.a = activityManager;
    }
}
