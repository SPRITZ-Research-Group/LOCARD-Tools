package com.skype.react.activationExperiment.models;

import com.facebook.react.bridge.am;
import com.skype.react.activationExperiment.Utils;
import java.lang.reflect.Type;

public class ActivationOptions {
    public static String NOTIFICATION_PLACEHOLDER_IMAGE_URL = "https://secure.skypeassets.com/content/dam/scom/app/notifications/ensure_network_image.png";
    public final OEMEcsConfig ecsConfig;
    public final boolean isInsidersBuild;
    public final long lastLaunchTime;
    public final AppLaunchState launchState;
    public final NotificationMeta notificationMeta;
    public final NotificationState notificationState;
    public final String notificationStyle;
    public final String partnerPreInstallId;
    public final long scheduleWakeUpTime;

    public ActivationOptions(am map) {
        OEMEcsConfig oEMEcsConfig;
        NotificationMeta notificationMeta;
        this.launchState = a(map);
        this.lastLaunchTime = Utils.a(map, "lastLaunchTime", Double.valueOf(0.0d)).longValue();
        this.scheduleWakeUpTime = Long.valueOf(Utils.a(map, "scheduledWakeUpTime", Double.valueOf(-1.0d)).longValue()).longValue();
        this.notificationState = b(map);
        this.notificationStyle = Utils.a(map, "notificationStyle", "");
        String a = Utils.a(map, "oemConfig", null);
        if (a == null) {
            oEMEcsConfig = null;
        } else {
            oEMEcsConfig = (OEMEcsConfig) Utils.a(a, OEMEcsConfig.class);
        }
        this.ecsConfig = oEMEcsConfig;
        a = Utils.a(map, "notificationMeta", null);
        if (a == null) {
            notificationMeta = null;
        } else {
            notificationMeta = (NotificationMeta) Utils.a(a, NotificationMeta.class);
            if (notificationMeta != null) {
                notificationMeta.b();
            }
        }
        this.notificationMeta = notificationMeta;
        this.partnerPreInstallId = Utils.a(map, "partnerPreInstallId", null);
        this.isInsidersBuild = Utils.a(map, "isInsidersBuild");
    }

    public String toString() {
        return Utils.a((Object) this, (Type) ActivationOptions.class);
    }

    private static AppLaunchState a(am data) {
        try {
            return AppLaunchState.valueOf(Utils.a(data, "launchState", null));
        } catch (IllegalArgumentException e) {
            return AppLaunchState.UNKNOWN;
        }
    }

    private static NotificationState b(am data) {
        try {
            return NotificationState.valueOf(Utils.a(data, "notificationState", null));
        } catch (IllegalArgumentException e) {
            return NotificationState.UNKNOWN;
        }
    }

    public final int a() {
        return this.ecsConfig == null ? 0 : this.ecsConfig.c();
    }
}
