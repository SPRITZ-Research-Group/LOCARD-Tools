package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.Record;

public interface IStatsEvents {
    void logCorruptEvent(Record record, String str);

    void logException(Throwable th);

    void logTransmitProfile(String str, int i, int i2, int i3, int i4);

    void transition(EventTransition eventTransition, int i, EventPriority eventPriority, String str);
}
