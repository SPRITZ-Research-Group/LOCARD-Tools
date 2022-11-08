package com.skype.react.activationExperiment.models;

public enum ExperimentState {
    Started,
    DisplayedToUser,
    DisplaySkippedNetworkError,
    DisplaySkippedNetworkTimeout,
    DisplaySkippedInvalidImage,
    DisplaySkippedInvalidNotifyMeta,
    NotificationClicked,
    UserSignedIn,
    CancelledUserSignedIn,
    RetriesExhausted,
    SkippedForRetargeting,
    Unknown;

    public static ExperimentState a(NotificationError error) {
        switch (error) {
            case NetworkTimeout:
                return DisplaySkippedNetworkTimeout;
            case InvalidImageUri:
                return DisplaySkippedInvalidImage;
            case InvalidNotificationMeta:
                return DisplaySkippedInvalidNotifyMeta;
            default:
                return DisplaySkippedNetworkError;
        }
    }
}
