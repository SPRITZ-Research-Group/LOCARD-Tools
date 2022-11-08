package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;

interface IRecordClassifier {
    boolean processForPriorityAndAbove(EventPriority eventPriority);
}
