package com.microsoft.applications.telemetry.pal.hardware;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.microsoft.applications.telemetry.core.TraceHelper;
import com.microsoft.applications.telemetry.datamodels.NetworkCost;
import com.microsoft.applications.telemetry.datamodels.NetworkType;

public class NetworkInformation {
    private static final String ACCESS_NETWORK_STATE_PERMISSION_STRING = "android.permission.ACCESS_NETWORK_STATE";
    private static final String LOG_TAG = ("[ACT]:" + NetworkInformation.class.getSimpleName().toUpperCase());
    private static boolean hasNetworkAccessPermission = false;
    private static boolean isEthernetAvailable = false;
    private static boolean isWifiAvailable = false;
    private static boolean isWwanAvailable = false;
    private static NetworkCost networkCost = NetworkCost.UNKNOWN;
    private static String networkProvider = "";
    private static NetworkType networkType = NetworkType.UNKNOWN;

    public static synchronized boolean isEthernetAvailable() {
        boolean z;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("isEthernetAvailable|value:%s", new Object[]{Boolean.valueOf(isEthernetAvailable)}));
            z = isEthernetAvailable;
        }
        return z;
    }

    public static synchronized boolean isWifiAvailable() {
        boolean z;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("isWifiAvailable|value:%s", new Object[]{Boolean.valueOf(isWifiAvailable)}));
            z = isWifiAvailable;
        }
        return z;
    }

    public static synchronized boolean isWwanAvailable() {
        boolean z;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("isWwanAvailable|value:%s", new Object[]{Boolean.valueOf(isWwanAvailable)}));
            z = isWwanAvailable;
        }
        return z;
    }

    public static synchronized NetworkCost getNetworkCost() {
        NetworkCost networkCost;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getNetworkCost|value:%s", new Object[]{networkCost}));
            networkCost = networkCost;
        }
        return networkCost;
    }

    public static synchronized String getNetworkProvider() {
        String str;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getNetworkProvider|value:%s", new Object[]{networkProvider}));
            str = networkProvider;
        }
        return str;
    }

    public static synchronized NetworkType getNetworkType() {
        NetworkType networkType;
        synchronized (NetworkInformation.class) {
            TraceHelper.TraceVerbose(LOG_TAG, String.format("getNetworkType|value:%s", new Object[]{networkType}));
            networkType = networkType;
        }
        return networkType;
    }

    public static synchronized void update(Context context) {
        synchronized (NetworkInformation.class) {
            determineNetwork(context);
        }
    }

    public static boolean hasNetworkAccessPermission() {
        return hasNetworkAccessPermission;
    }

    public static void checkForNetworkAccess(Context context) {
        hasNetworkAccessPermission = context.checkCallingOrSelfPermission(ACCESS_NETWORK_STATE_PERMISSION_STRING) == 0;
    }

    private static synchronized void determineNetwork(Context context) {
        synchronized (NetworkInformation.class) {
            try {
                if (hasNetworkAccessPermission) {
                    ConnectivityManager conn = (ConnectivityManager) context.getSystemService("connectivity");
                    TelephonyManager telephony = (TelephonyManager) context.getSystemService("phone");
                    NetworkCost prevCost = networkCost;
                    NetworkType prevType = networkType;
                    String prevProvider = networkProvider;
                    networkCost = determineNetworkCost(conn);
                    networkType = determineNetworkType(conn, telephony);
                    networkProvider = determineNetworkProvider(telephony);
                    TraceHelper.TraceDebug(LOG_TAG, String.format("Cost(prev,now): (%s,%s)|Type(prev,now): (%s, %s)|Provider(prev,now): (%s,%s)", new Object[]{prevCost, networkCost, prevType, networkType, prevProvider, networkProvider}));
                }
            } catch (Exception e) {
                TraceHelper.TraceError(LOG_TAG, "Exception when trying to get network information.", e);
            }
        }
        return;
    }

    private static synchronized void determineNetworkAvailability(ConnectivityManager conn) {
        boolean z = true;
        synchronized (NetworkInformation.class) {
            boolean z2;
            NetworkInfo ethernet = conn.getNetworkInfo(9);
            if (ethernet == null || !ethernet.isAvailable()) {
                z2 = false;
            } else {
                z2 = true;
            }
            isEthernetAvailable = z2;
            NetworkInfo wifi = conn.getNetworkInfo(1);
            if (wifi == null || !wifi.isAvailable()) {
                z2 = false;
            } else {
                z2 = true;
            }
            isWifiAvailable = z2;
            NetworkInfo wwan = conn.getNetworkInfo(6);
            if (wwan == null || !wwan.isAvailable()) {
                z = false;
            }
            isWwanAvailable = z;
        }
    }

    private static synchronized NetworkCost determineNetworkCost(ConnectivityManager conn) {
        NetworkCost cost;
        synchronized (NetworkInformation.class) {
            cost = NetworkCost.UNKNOWN;
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.isRoaming()) {
                cost = NetworkCost.OVER_DATA_LIMIT;
            }
        }
        return cost;
    }

    private static synchronized NetworkType determineNetworkType(ConnectivityManager conn, TelephonyManager telephony) {
        NetworkType network;
        synchronized (NetworkInformation.class) {
            network = NetworkType.UNKNOWN;
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();
            if (networkInfo != null) {
                switch (networkInfo.getType()) {
                    case 0:
                    case 3:
                    case 4:
                    case 5:
                        network = NetworkType.WWAN;
                        break;
                    case 1:
                        network = NetworkType.WIFI;
                        break;
                    case 9:
                        network = NetworkType.WIRED;
                        break;
                    default:
                        network = NetworkType.UNKNOWN;
                        break;
                }
            }
        }
        return network;
    }

    private static synchronized NetworkType determineNetworkClass(TelephonyManager telephony) {
        NetworkType network;
        synchronized (NetworkInformation.class) {
            NetworkType networkType = NetworkType.UNKNOWN;
            switch (telephony.getNetworkType()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    network = NetworkType.WWAN;
                    break;
                default:
                    network = NetworkType.UNKNOWN;
                    break;
            }
        }
        return network;
    }

    private static synchronized String determineNetworkProvider(TelephonyManager telephony) {
        String provider;
        synchronized (NetworkInformation.class) {
            provider = telephony.getNetworkOperatorName();
            telephony.getPhoneType();
        }
        return provider;
    }
}
