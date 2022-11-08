package com.skype.react.activationExperiment.models;

public enum WakeResultStatus {
    NotEligibleToRun,
    NextWakeUpScheduled,
    WakeUpError,
    WakeUpRetriesExhausted,
    DisplaySuccess,
    DisplayFailure,
    NotificationClickedNetworkSuccess,
    NotificationClickedNetworkFailure,
    UserSignedInSuccess,
    ExperimentCancelledUserSignedIn,
    NotificationClickExperimentMissing,
    NotificationClickUserAlreadySignedIn,
    ExperimentAlreadyFinished,
    ExperimentAlreadyFinishedBefore817,
    PendingSendTrySuccess,
    PendingSendTryFailed,
    WakeUpPending,
    RescheduledOnBoot,
    DidNothing,
    Unknown;

    public static WakeResultStatus a(String value) {
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            return Unknown;
        }
    }
}
