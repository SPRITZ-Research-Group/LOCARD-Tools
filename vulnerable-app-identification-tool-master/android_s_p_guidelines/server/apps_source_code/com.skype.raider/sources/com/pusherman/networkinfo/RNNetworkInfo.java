package com.pusherman.networkinfo;

import android.net.wifi.WifiManager;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class RNNetworkInfo extends ReactContextBaseJavaModule {
    public static final String TAG = "RNNetworkInfo";
    WifiManager wifi;

    public RNNetworkInfo(ag reactContext) {
        super(reactContext);
        this.wifi = (WifiManager) reactContext.getApplicationContext().getSystemService("wifi");
    }

    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getSSID(ae promise) {
        Object ssid = this.wifi.getConnectionInfo().getSSID();
        if (ssid == null) {
            ssid = "";
        }
        if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        promise.a(ssid);
    }

    @ReactMethod
    public void getBSSID(ae promise) {
        promise.a(this.wifi.getConnectionInfo().getBSSID());
    }

    @ReactMethod
    public void getIPAddress(ae promise) {
        Object ipAddress = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                Enumeration<InetAddress> enumIpAddr = ((NetworkInterface) en.nextElement()).getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                    }
                }
            }
            if (ipAddress != null) {
                promise.a(ipAddress);
            } else {
                promise.a("Undefined", "IpAddress is undefined");
            }
        } catch (Throwable ex) {
            ex.toString();
            promise.a("Failure", ex);
        }
    }

    @ReactMethod
    public void getIPV4Address(ae promise) {
        Object ipAddress = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                Enumeration<InetAddress> enumIpAddr = ((NetworkInterface) en.nextElement()).getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        ipAddress = inetAddress.getHostAddress().toString();
                    }
                }
            }
            if (ipAddress != null) {
                promise.a(ipAddress);
            } else {
                promise.a("Undefined", "IpAddress is undefined");
            }
        } catch (Throwable ex) {
            ex.toString();
            promise.a("Failure", ex);
        }
    }
}
