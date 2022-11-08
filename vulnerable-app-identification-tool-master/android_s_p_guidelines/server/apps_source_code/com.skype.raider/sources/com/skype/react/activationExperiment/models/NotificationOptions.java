package com.skype.react.activationExperiment.models;

import com.facebook.react.bridge.am;
import com.skype.react.activationExperiment.Utils;
import java.lang.reflect.Type;

public class NotificationOptions {
    public final AppLaunchState launchState;
    public final long notificationId;

    public NotificationOptions(am map) {
        this.launchState = a(map);
        this.notificationId = Long.valueOf(Utils.a(map, "notificationId", Double.valueOf(-1.0d)).longValue()).longValue();
    }

    public String toString() {
        return Utils.a((Object) this, (Type) NotificationOptions.class);
    }

    private static AppLaunchState a(am data) {
        try {
            return AppLaunchState.valueOf(Utils.a(data, "launchState", null));
        } catch (Exception e) {
            return AppLaunchState.UNKNOWN;
        }
    }
}
