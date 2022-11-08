package com.microsoft.dl.audio;

import com.microsoft.dl.audio.AudioPlatform.clientCallback;
import com.microsoft.dl.utils.Log;

public class RouteController {
    private final boolean a = true;
    private final boolean b = false;
    private boolean c;
    private String d;
    private String e;
    private boolean f = false;
    private boolean g = false;
    private clientCallback h = null;
    private long i = 0;

    private static native void onRouteChange(long j, boolean z);

    public RouteController(boolean clientRouting, clientCallback callback) {
        this.c = clientRouting;
        this.h = callback;
        this.d = "Earpiece";
        if (Log.isLoggable(PackageInfo.TAG, 4)) {
            Log.i(PackageInfo.TAG, "RouteController ctor: isRouteSetFromClientSide=" + (this.c ? "TRUE" : "FALSE") + " thisClientCallback=" + (this.h == null ? "NULL" : "NOT NULL"));
        }
    }

    public synchronized void setClientCallback(clientCallback callback, boolean clientRouting) {
        if (!(callback == this.h || callback == null)) {
            this.h = callback;
        }
        this.c = clientRouting;
    }

    public synchronized void registerNativeInstance(long nativeInstance) {
        this.i = nativeInstance;
    }

    public synchronized void unregisterNativeInstance() {
        this.i = 0;
    }

    public synchronized void startingDevice(boolean onStart) {
        this.g = onStart;
        if (this.f && this.g) {
            this.f = false;
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "device restarted: complete route change CB to UI (oldRoute=" + this.d + ", newRoute=" + this.e + ")");
            }
            this.d = this.e;
            a(this.d);
        }
    }

    public synchronized void onNativeCallback(String route, boolean onRestartDevice) {
        if (this.i != 0) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "onRouteChangeCB for DL/LMS: newRoute=" + route + " onRestartDevice=" + onRestartDevice);
            }
            onRouteChange(this.i, onRestartDevice);
        }
    }

    public synchronized boolean setRouteChange(String newRoute) {
        boolean z = false;
        synchronized (this) {
            boolean z2;
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "setRouteChange: defaultRoute=" + this.d + " newRoute=" + newRoute + " savedRoute=" + this.e + " waitForRestart=" + this.f + " isDeviceRunning=" + this.g);
            }
            if (newRoute.equals("Earpiece") || newRoute.equals("Speaker") || newRoute.equals("Bluetooth") || newRoute.equals("Non_speaker") || newRoute.equals("Headset_with_mic") || newRoute.equals("Headset_without_mic")) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                b(newRoute);
            } else if (newRoute == this.d && !this.f) {
                a(newRoute);
                z = true;
            } else if (!(newRoute == this.e && this.f)) {
                if (!this.g && !this.f) {
                    this.d = newRoute;
                    onNativeCallback(newRoute, false);
                    a(newRoute);
                    z = true;
                } else if (newRoute.equals("Bluetooth") || this.d == "Bluetooth") {
                    this.f = true;
                    this.e = newRoute;
                    onNativeCallback(newRoute, true);
                } else {
                    this.f = false;
                    this.e = newRoute;
                    this.d = newRoute;
                    onNativeCallback(newRoute, false);
                    a(newRoute);
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized String getDefaultRoute() {
        return this.f ? this.e : this.d;
    }

    private synchronized void a(String route) {
        if (this.c) {
            this.h.callbackWithoutError(route);
        }
    }

    private synchronized void b(String route) {
        if (this.c) {
            this.h.callbackWithError(route);
        }
    }
}
