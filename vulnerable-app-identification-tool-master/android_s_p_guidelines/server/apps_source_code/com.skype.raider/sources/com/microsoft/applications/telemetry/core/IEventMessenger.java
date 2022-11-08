package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.EventBase;
import java.util.ArrayList;

interface IEventMessenger {
    void addRecordsBackToStorage(DataPackageCollection dataPackageCollection);

    void backoffTPM();

    void clearTPMBackoff();

    EventsHandler getEventsHandler();

    long getFirstLaunchTimeInMillis();

    String getSdkUID();

    void removeRecordsFromStorage(ArrayList<Long> arrayList);

    void sendRecord(EventBase eventBase, EventPriority eventPriority, String str);
}
