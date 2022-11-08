package com.microsoft.applications.telemetry.core;

import com.microsoft.applications.telemetry.datamodels.NetworkCost;
import com.microsoft.applications.telemetry.datamodels.NetworkType;
import com.microsoft.applications.telemetry.datamodels.PowerSource;
import com.microsoft.applications.telemetry.pal.hardware.DeviceInformation;
import com.microsoft.applications.telemetry.pal.hardware.NetworkInformation;

class PalInfo {
    static NetworkType networkType = null;

    PalInfo() {
    }

    public static PowerSource getPowerSrc() {
        return DeviceInformation.getPowerSource();
    }

    public static NetworkCost getNetworkCost() {
        return NetworkInformation.getNetworkCost();
    }

    public static NetworkType getNetworkType() {
        return networkType != null ? networkType : NetworkInformation.getNetworkType();
    }

    public static boolean hasNetworkStateAccess() {
        return NetworkInformation.hasNetworkAccessPermission();
    }

    protected static void setNetworkType(NetworkType type) {
        networkType = type;
    }
}
