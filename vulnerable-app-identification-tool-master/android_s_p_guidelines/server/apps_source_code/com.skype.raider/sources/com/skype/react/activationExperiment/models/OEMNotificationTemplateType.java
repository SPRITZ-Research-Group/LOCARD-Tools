package com.skype.react.activationExperiment.models;

public enum OEMNotificationTemplateType {
    Default,
    CustomBackground,
    Unknown;

    public static OEMNotificationTemplateType a(String type) {
        if (type == null) {
            return Unknown;
        }
        try {
            return valueOf(type);
        } catch (IllegalArgumentException e) {
            return Unknown;
        }
    }
}
