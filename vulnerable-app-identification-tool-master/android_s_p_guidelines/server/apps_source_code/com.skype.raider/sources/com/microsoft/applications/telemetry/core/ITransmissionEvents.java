package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import java.util.HashMap;

public interface ITransmissionEvents {
    void eventAdded(IEvent iEvent, EventPriority eventPriority, String str);

    void eventDropped(IEvent iEvent, EventPriority eventPriority, String str, EventDropReason eventDropReason);

    void eventRejected(IEvent iEvent, EventPriority eventPriority, String str, EventRejectedReason eventRejectedReason);

    void requestSendAttempted(HashMap<DataPackage, EventPriority> hashMap, String str);

    void requestSendFailed(HashMap<DataPackage, EventPriority> hashMap, String str, int i);

    void requestSendRetrying(HashMap<DataPackage, EventPriority> hashMap, String str);

    void requestSent(HashMap<DataPackage, EventPriority> hashMap, String str);
}
