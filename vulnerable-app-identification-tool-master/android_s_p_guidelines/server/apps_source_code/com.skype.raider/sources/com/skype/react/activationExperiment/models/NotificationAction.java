package com.skype.react.activationExperiment.models;

public class NotificationAction {
    private String title;
    private String type;
    private String url;

    public final String a() {
        return this.title;
    }

    public final NotificationActionType b() {
        if (this.type == null) {
            return NotificationActionType.Unknown;
        }
        try {
            return NotificationActionType.valueOf(this.type);
        } catch (IllegalArgumentException e) {
            return NotificationActionType.Unknown;
        }
    }

    public final String c() {
        return this.url;
    }
}
