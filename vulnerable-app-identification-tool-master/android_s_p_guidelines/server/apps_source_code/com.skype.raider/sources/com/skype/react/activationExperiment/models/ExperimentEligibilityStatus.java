package com.skype.react.activationExperiment.models;

public enum ExperimentEligibilityStatus {
    Eligible,
    EcsNotLoaded,
    CancelledUserSignedIn,
    EcsNotificationNotEnabled,
    EcsAppParametersNotSet,
    EcsAppLaunchStateNoMatch,
    EcsTimeDiffBelowThreshold,
    DeviceNotInExperiment,
    ActivationNotEnabled,
    NotificationBarredTimeWindow,
    NotificationTimeWindowNotStarted,
    NotificationTimeWindowExpired,
    FirstTimeNeverLaunched,
    Unknown
}
