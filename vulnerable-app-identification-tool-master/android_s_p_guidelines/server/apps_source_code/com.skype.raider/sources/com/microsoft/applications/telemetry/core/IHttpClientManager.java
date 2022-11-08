package com.microsoft.applications.telemetry.core;

interface IHttpClientManager {
    boolean canAcceptRequests();

    void sendRequest(DataPackageCollection dataPackageCollection);

    void transmissionPaused();

    void transmissionResumed();
}
