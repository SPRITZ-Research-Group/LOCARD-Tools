package com.microsoft.applications.telemetry.pal.hardware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.microsoft.applications.telemetry.core.IDeviceObserver;
import com.microsoft.applications.telemetry.core.TraceHelper;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HardwareInformationReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = ("[ACT]:" + HardwareInformationReceiver.class.getSimpleName().toUpperCase());
    private static Set<IDeviceObserver> deviceObservers = Collections.synchronizedSet(new HashSet());

    public static void addObserver(IDeviceObserver observer) {
        deviceObservers.add(observer);
    }

    public static void removeObserver(IDeviceObserver observer) {
        deviceObservers.remove(observer);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            TraceHelper.TraceDebug(LOG_TAG, "HardwareInformationReceiver onReceive()|action: " + action);
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                TraceHelper.TraceDebug(LOG_TAG, "Received Network Connectivity Change");
                if (context != null && NetworkInformation.hasNetworkAccessPermission()) {
                    NetworkInformation.update(context);
                    for (IDeviceObserver onNetworkStateChanged : deviceObservers) {
                        onNetworkStateChanged.onNetworkStateChanged();
                    }
                }
            } else if (action.equals("android.intent.action.ACTION_POWER_CONNECTED") || action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                TraceHelper.TraceDebug(LOG_TAG, "Received Power Connectivity Change");
                DeviceInformation.updatePowerInfo(intent);
                for (IDeviceObserver onNetworkStateChanged2 : deviceObservers) {
                    onNetworkStateChanged2.onPowerStateChanged();
                }
            }
        }
    }
}
