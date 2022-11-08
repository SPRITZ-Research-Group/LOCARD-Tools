package com.skype.react.activationExperiment.models;

import java.util.List;

public class OEMEcsConfig {
    private boolean sendAppUpgradeTelemetry;
    private UpgradeNotification upgradeNotification;

    public static class AppParameters {
        private List<String> appStateList;
        private int maxWakeAttempts;
        private int minHoursSinceLaunch;
        private int wakeIntervalSecs;

        public final List<String> a() {
            return this.appStateList;
        }

        public final int b() {
            return this.minHoursSinceLaunch;
        }

        final int c() {
            return this.maxWakeAttempts;
        }
    }

    public static class UpgradeNotification {
        private AppParameters appParameters;
        private boolean enabled;
        private List<String> enabledExperiments;
        private String notificationStyle;
        private NotificationTimingInfo notificationTimingInfo;
        private a retargetInfo;

        public final boolean a() {
            return this.enabled;
        }

        public final NotificationTimingInfo b() {
            return this.notificationTimingInfo;
        }

        public final AppParameters c() {
            return this.appParameters;
        }

        public final List<String> d() {
            return this.enabledExperiments;
        }

        public final a e() {
            return this.retargetInfo;
        }
    }

    static class a {
        String lastExperimentDisplayDate;
        long maxRetargetAllowed;
    }

    public final UpgradeNotification a() {
        return this.upgradeNotification;
    }

    public final int b() {
        AppParameters appParameters = f();
        return appParameters == null ? -1 : appParameters.wakeIntervalSecs;
    }

    final int c() {
        AppParameters appParameters = f();
        return appParameters == null ? 0 : appParameters.c();
    }

    private AppParameters f() {
        return this.upgradeNotification != null ? this.upgradeNotification.appParameters : null;
    }

    public final boolean d() {
        return this.upgradeNotification != null ? this.upgradeNotification.enabled : false;
    }

    public final DeviceExperimentConfig e() {
        if (this.upgradeNotification == null) {
            return null;
        }
        return DeviceExperimentConfig.a(this.upgradeNotification.d());
    }
}
