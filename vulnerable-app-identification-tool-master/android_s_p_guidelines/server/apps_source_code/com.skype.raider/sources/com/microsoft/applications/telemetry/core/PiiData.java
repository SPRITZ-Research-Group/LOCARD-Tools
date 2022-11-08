package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.PiiKind;

public class PiiData {
    PiiKind piiKind;
    String value;

    public PiiData(String value, PiiKind piiKind) {
        this.value = value;
        this.piiKind = piiKind;
    }
}
