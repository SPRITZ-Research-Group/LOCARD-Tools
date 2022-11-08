package com.skype.react.common;

import com.skype.react.activationExperiment.models.NotificationError;

public interface NotificationStatusCallback {
    void a(int i);

    void a(NotificationError notificationError);
}
