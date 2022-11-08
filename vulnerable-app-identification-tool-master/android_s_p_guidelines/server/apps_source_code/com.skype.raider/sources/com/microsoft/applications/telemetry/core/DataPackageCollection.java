package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.EventPriority;
import com.microsoft.applications.telemetry.datamodels.DataPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

class DataPackageCollection {
    private String clockSkewHeaderValue = "";
    private final String id = UUID.randomUUID().toString();
    private boolean isFirstRequest = false;
    private boolean isImmediateRequest = false;
    private int retryCount = 0;
    private long size = 0;
    private HashMap<String, HashMap<DataPackage, EventPriority>> tokenToDataPackages = new HashMap();
    private HashMap<String, ArrayList<Long>> tokenToRowIds = new HashMap();

    DataPackageCollection(boolean isImmediateRequest) {
        this.isImmediateRequest = isImmediateRequest;
    }

    void add(DataPackage dataPackage, ArrayList<Long> rowIds, long size, EventPriority priority, String token) {
        if (!this.tokenToDataPackages.containsKey(token)) {
            this.tokenToDataPackages.put(token, new HashMap());
            this.tokenToRowIds.put(token, new ArrayList());
        }
        ((HashMap) this.tokenToDataPackages.get(token)).put(dataPackage, priority);
        ((ArrayList) this.tokenToRowIds.get(token)).addAll(rowIds);
        this.size += size;
    }

    void removeDataPackages(String token) {
        this.tokenToDataPackages.remove(token);
        this.tokenToRowIds.remove(token);
    }

    String getId() {
        return this.id;
    }

    long getSize() {
        return this.size;
    }

    public HashMap<String, HashMap<DataPackage, EventPriority>> getTokenToDataPackages() {
        return this.tokenToDataPackages;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public boolean isFirstRequest() {
        return this.isFirstRequest;
    }

    public void setFirstRequest(boolean isFirstRequest) {
        this.isFirstRequest = isFirstRequest;
    }

    public void setClockSkewHeaderValue(String clockSkewHeaderValue) {
        this.clockSkewHeaderValue = clockSkewHeaderValue;
    }

    public String getClockSkewHeaderValue() {
        return this.clockSkewHeaderValue;
    }

    public boolean isImmediateRequest() {
        return this.isImmediateRequest;
    }

    public HashMap<String, ArrayList<Long>> getTokenToRowIds() {
        return this.tokenToRowIds;
    }
}
