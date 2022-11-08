package com.microsoft.applications.telemetry.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class ClockSkewManager {
    private static String TIME_DELTA_MILLIS = "time-delta-millis";
    private static String USE_COLLECTOR_DELTA = "use-collector-delta";
    private boolean clockSkewEnabled = true;
    private boolean clockSkewObtained = false;
    private String clockSkewValue = "";
    final Queue<DataPackageCollection> clockSkewWaitQueue = new LinkedList();
    private boolean firstRequestReceived = false;

    ClockSkewManager() {
    }

    boolean isClockSkewEnabled() {
        return this.clockSkewEnabled;
    }

    synchronized DataPackageCollection processRequest(DataPackageCollection request) {
        if (!this.firstRequestReceived) {
            this.firstRequestReceived = true;
            request.setFirstRequest(true);
            request.setClockSkewHeaderValue(USE_COLLECTOR_DELTA);
        } else if (this.clockSkewObtained || !this.clockSkewEnabled) {
            request.setClockSkewHeaderValue(this.clockSkewValue);
        } else {
            this.clockSkewWaitQueue.add(request);
            request = null;
        }
        return request;
    }

    Queue<DataPackageCollection> determineClockSkewAndReturnQueuedRequests(Map<String, List<String>> responseHeaders) {
        if (this.clockSkewObtained || !this.clockSkewEnabled) {
            return null;
        }
        if (responseHeaders == null || !responseHeaders.containsKey(TIME_DELTA_MILLIS)) {
            this.clockSkewEnabled = false;
        } else {
            this.clockSkewObtained = true;
            this.clockSkewValue = (String) ((List) responseHeaders.get(TIME_DELTA_MILLIS)).get(0);
            for (DataPackageCollection clockSkewHeaderValue : this.clockSkewWaitQueue) {
                clockSkewHeaderValue.setClockSkewHeaderValue(this.clockSkewValue);
            }
        }
        return this.clockSkewWaitQueue;
    }

    Queue<DataPackageCollection> disableClockSkewAndReturnQueuedRequests() {
        if (!this.clockSkewEnabled) {
            return null;
        }
        this.clockSkewEnabled = false;
        return this.clockSkewWaitQueue;
    }

    boolean areRequestsBlocked() {
        if (this.clockSkewEnabled && this.firstRequestReceived && !this.clockSkewObtained) {
            return true;
        }
        return false;
    }

    Queue<DataPackageCollection> returnQueuedRequests() {
        return this.clockSkewWaitQueue;
    }

    void reset() {
        this.clockSkewObtained = false;
        this.clockSkewEnabled = true;
        this.firstRequestReceived = false;
        this.clockSkewValue = "";
    }
}
