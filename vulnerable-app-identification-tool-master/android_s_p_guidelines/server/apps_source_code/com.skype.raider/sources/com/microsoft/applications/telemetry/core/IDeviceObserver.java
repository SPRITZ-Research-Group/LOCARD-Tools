package com.microsoft.applications.telemetry.core;

public interface IDeviceObserver {
    void onNetworkStateChanged();

    void onPowerStateChanged();
}
