package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.datamodels.PII;
import java.util.ArrayList;
import java.util.HashMap;

public interface IEvent {
    String getEventType();

    HashMap<String, String> getExtension();

    String getId();

    HashMap<String, PII> getPIIExtensions();

    long getTimestamp();

    String getType();

    HashMap<String, Boolean> getTypedExtensionBoolean();

    HashMap<String, Long> getTypedExtensionDateTime();

    HashMap<String, Double> getTypedExtensionDouble();

    HashMap<String, ArrayList<Byte>> getTypedExtensionGuid();

    HashMap<String, Long> getTypedExtensionInt64();
}
