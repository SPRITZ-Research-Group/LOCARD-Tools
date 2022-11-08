package com.skype.react.activationExperiment.models;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;
import com.skype.react.activationExperiment.Utils;
import java.lang.reflect.Type;

public class EventOptions {
    private long notificationId;
    private long scheduledWakeUpTime;
    private final int taskId;

    public EventOptions(int taskId) {
        this.taskId = taskId;
    }

    public String toString() {
        return Utils.a((Object) this, (Type) EventOptions.class);
    }

    public final void a(long scheduledWakeUpTime) {
        this.scheduledWakeUpTime = scheduledWakeUpTime;
    }

    public final void b(long notificationId) {
        this.notificationId = notificationId;
    }

    public final ar a() {
        ar map = new WritableNativeMap();
        map.putInt("taskId", this.taskId);
        map.putDouble("scheduledWakeUpTime", (double) this.scheduledWakeUpTime);
        map.putDouble("notificationId", (double) this.notificationId);
        return map;
    }
}
