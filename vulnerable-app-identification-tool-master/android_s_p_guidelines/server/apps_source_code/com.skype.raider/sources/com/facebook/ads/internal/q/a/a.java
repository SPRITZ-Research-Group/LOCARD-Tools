package com.facebook.ads.internal.q.a;

import android.app.Activity;
import android.support.annotation.Nullable;
import com.brentvatne.react.ReactVideoViewManager;
import java.lang.reflect.Field;
import java.util.Map;

public final class a {
    @Nullable
    public static Activity a() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object invoke2 : map.values()) {
                Class cls2 = invoke2.getClass();
                Field declaredField2 = cls2.getDeclaredField(ReactVideoViewManager.PROP_PAUSED);
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(invoke2)) {
                    declaredField = cls2.getDeclaredField("activity");
                    declaredField.setAccessible(true);
                    return (Activity) declaredField.get(invoke2);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
