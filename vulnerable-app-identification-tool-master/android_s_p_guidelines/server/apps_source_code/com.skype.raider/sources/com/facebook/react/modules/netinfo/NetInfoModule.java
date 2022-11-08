package com.facebook.react.modules.netinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "NetInfo")
public class NetInfoModule extends ReactContextBaseJavaModule implements v {
    private static final String CONNECTION_TYPE_2G = "MOBILE_2G";
    private static final String CONNECTION_TYPE_3G = "MOBILE_3G";
    private static final String CONNECTION_TYPE_4G = "MOBILE_4G";
    private static final String CONNECTION_TYPE_NONE = "NONE";
    private static final String CONNECTION_TYPE_UNKNOWN = "UNKNOWN";
    private static final String CONNECTION_TYPE_WIFI = "WIFI";
    private static final String ERROR_MISSING_PERMISSION = "E_MISSING_PERMISSION";
    private static final String MISSING_PERMISSION_MESSAGE = "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />";
    private String mConnectivity = CONNECTION_TYPE_UNKNOWN;
    private final a mConnectivityBroadcastReceiver;
    private final ConnectivityManager mConnectivityManager;
    private boolean mNoNetworkPermission = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ NetInfoModule a;
        private boolean b;

        private a(NetInfoModule netInfoModule) {
            this.a = netInfoModule;
            this.b = false;
        }

        /* synthetic */ a(NetInfoModule x0, byte b) {
            this(x0);
        }

        public final void a(boolean registered) {
            this.b = registered;
        }

        public final boolean a() {
            return this.b;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                this.a.updateAndSendConnectionType();
            }
        }
    }

    public NetInfoModule(ag reactContext) {
        super(reactContext);
        this.mConnectivityManager = (ConnectivityManager) reactContext.getSystemService("connectivity");
        this.mConnectivityBroadcastReceiver = new a();
    }

    public void onHostResume() {
        registerReceiver();
    }

    public void onHostPause() {
        unregisterReceiver();
    }

    public void onHostDestroy() {
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
    }

    public String getName() {
        return "NetInfo";
    }

    @ReactMethod
    public void getCurrentConnectivity(ae promise) {
        if (this.mNoNetworkPermission) {
            promise.a(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE, null);
        } else {
            promise.a(createConnectivityEventMap());
        }
    }

    @ReactMethod
    public void isConnectionMetered(ae promise) {
        if (this.mNoNetworkPermission) {
            promise.a(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE, null);
        } else {
            promise.a(Boolean.valueOf(ConnectivityManagerCompat.a(this.mConnectivityManager)));
        }
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getReactApplicationContext().registerReceiver(this.mConnectivityBroadcastReceiver, filter);
        this.mConnectivityBroadcastReceiver.a(true);
        updateAndSendConnectionType();
    }

    private void unregisterReceiver() {
        if (this.mConnectivityBroadcastReceiver.a()) {
            getReactApplicationContext().unregisterReceiver(this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.a(false);
        }
    }

    private void updateAndSendConnectionType() {
        String currentConnectivity = getCurrentConnectionType();
        if (!currentConnectivity.equalsIgnoreCase(this.mConnectivity)) {
            this.mConnectivity = currentConnectivity;
            sendConnectivityChangedEvent();
        }
    }

    private String getCurrentConnectionType() {
        try {
            NetworkInfo networkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                return CONNECTION_TYPE_NONE;
            }
            if (!ConnectivityManager.isNetworkTypeValid(networkInfo.getType())) {
                return CONNECTION_TYPE_UNKNOWN;
            }
            if (networkInfo.getType() == 1) {
                return CONNECTION_TYPE_WIFI;
            }
            switch (networkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return CONNECTION_TYPE_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                    return CONNECTION_TYPE_3G;
                case 13:
                case 14:
                case 15:
                    return CONNECTION_TYPE_4G;
                default:
                    return CONNECTION_TYPE_UNKNOWN;
            }
            this.mNoNetworkPermission = true;
            return CONNECTION_TYPE_UNKNOWN;
        } catch (SecurityException e) {
            this.mNoNetworkPermission = true;
            return CONNECTION_TYPE_UNKNOWN;
        }
    }

    private void sendConnectivityChangedEvent() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("networkStatusDidChange", createConnectivityEventMap());
    }

    private ar createConnectivityEventMap() {
        ar event = new WritableNativeMap();
        event.putString("network_info", this.mConnectivity);
        return event;
    }
}
