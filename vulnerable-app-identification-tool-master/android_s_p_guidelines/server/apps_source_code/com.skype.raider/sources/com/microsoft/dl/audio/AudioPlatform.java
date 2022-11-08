package com.microsoft.dl.audio;

import android.content.Context;
import android.media.AudioManager;
import android.os.Environment;
import com.microsoft.dl.utils.Log;
import java.io.File;

public class AudioPlatform {
    private static AudioManager a = null;
    private static Context b = null;
    private static RouteController c = null;
    private static VolumeController d = null;
    private static AudioHwOffload e = null;
    private static boolean f = false;
    private static clientCallback g = null;

    public static class clientCallback {
        public void callbackWithoutError(String audioRoute) {
        }

        public void callbackWithError(String audioRoute) {
        }

        public void callbackStopRingoutTone() {
        }

        public void callbackBeforeCreatingAudioRecorder() {
        }

        public void callbackAfterReleasingAudioRecorder() {
        }
    }

    private AudioPlatform() {
    }

    public static synchronized void setMode(int mode) {
        synchronized (AudioPlatform.class) {
            if (a != null) {
                a.setMode(mode);
            } else {
                Log.log(6, PackageInfo.TAG, "AudioPlatform: audioManager is null");
            }
        }
    }

    public static synchronized void setAudioContext(Context ctx) {
        synchronized (AudioPlatform.class) {
            if (ctx != null) {
                b = ctx;
                a = (AudioManager) ctx.getSystemService("audio");
            }
        }
    }

    public static synchronized void initialize() {
        synchronized (AudioPlatform.class) {
            initialize(f, g);
        }
    }

    public static synchronized void updateClientCallback(boolean clientRouting, clientCallback callback) {
        synchronized (AudioPlatform.class) {
            f = clientRouting;
            g = callback;
            if (c != null) {
                c.setClientCallback(g, f);
            }
        }
    }

    public static boolean initialize(boolean clientRouting, clientCallback callback) {
        if (callback == null) {
            g = new clientCallback();
        } else {
            g = callback;
        }
        if (c == null) {
            clientCallback routeClientCallback;
            if (!clientRouting || (clientRouting && callback == null)) {
                routeClientCallback = new clientCallback();
            } else {
                routeClientCallback = callback;
            }
            c = new RouteController(clientRouting, routeClientCallback);
        }
        if (d == null) {
            d = new VolumeController();
        }
        if (e == null) {
            e = new AudioHwOffload();
        }
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "AudioPlatform initialized");
        }
        return true;
    }

    public static String readWhitelistFromSdcard() {
        File whitelistFile = new File(Environment.getExternalStorageDirectory(), "audio.cfg");
        if (whitelistFile.exists()) {
            return whitelistFile.getAbsolutePath();
        }
        if (Log.isLoggable(PackageInfo.TAG, 3)) {
            Log.d(PackageInfo.TAG, "AudioPlatform: " + whitelistFile.getAbsolutePath() + " doesn't exist");
        }
        return "";
    }

    public static synchronized VolumeController getJavaVolumeInstance() {
        VolumeController volumeController;
        synchronized (AudioPlatform.class) {
            if (d == null) {
                Log.log(5, PackageInfo.TAG, "AudioPlatform: No instance found of VolumeController class");
                volumeController = null;
            } else {
                volumeController = d;
            }
        }
        return volumeController;
    }

    public static synchronized RouteController getJavaRouteInstance() {
        RouteController routeController;
        synchronized (AudioPlatform.class) {
            if (c == null) {
                Log.log(5, PackageInfo.TAG, "AudioPlatform: No instance found of RouteController class");
                routeController = null;
            } else {
                routeController = c;
            }
        }
        return routeController;
    }

    public static synchronized AudioHwOffload getJavaHwOffloadInstance() {
        AudioHwOffload audioHwOffload;
        synchronized (AudioPlatform.class) {
            if (e == null) {
                Log.log(5, PackageInfo.TAG, "AudioPlatform: No instance found of AudioHwOffload class");
                audioHwOffload = null;
            } else {
                audioHwOffload = e;
            }
        }
        return audioHwOffload;
    }

    public static synchronized void registerNativeInstance(long nativeInstance) {
        synchronized (AudioPlatform.class) {
            if (nativeInstance == 0) {
                Log.log(5, PackageInfo.TAG, "AudioPlatform: The native instance is null, no callback possible");
            } else {
                if (d != null) {
                    d.registerNativeInstance(nativeInstance);
                    d.registerVolumeIntentReceiver();
                }
                if (c != null) {
                    c.registerNativeInstance(nativeInstance);
                }
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "native instance registered for CallBacks");
                }
            }
        }
    }

    public static synchronized void unregisterNativeInstance() {
        synchronized (AudioPlatform.class) {
            if (d != null) {
                d.unregisterVolumeIntentReceiver();
                d.unregisterNativeInstance();
            }
            if (c != null) {
                c.unregisterNativeInstance();
            }
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "native instance unregistered for CallBacks");
            }
        }
    }

    public static synchronized void uninitialize() {
        synchronized (AudioPlatform.class) {
            c = null;
            d = null;
            e = null;
            if (Log.isLoggable(PackageInfo.TAG, 3)) {
                Log.d(PackageInfo.TAG, "AudioPlatform uninitialized");
            }
        }
    }

    public static synchronized boolean setRoute(String route) {
        boolean z;
        synchronized (AudioPlatform.class) {
            if (c == null) {
                Log.log(6, PackageInfo.TAG, "setRoute() from UI: No instance found of RouteController class");
                z = false;
            } else {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "setRoute() from UI: " + route);
                }
                z = c.setRouteChange(route);
            }
        }
        return z;
    }

    public static synchronized String getDefaultRoute() {
        String str;
        synchronized (AudioPlatform.class) {
            if (c == null) {
                Log.log(6, PackageInfo.TAG, "getDefaultRoute(): No instance found of RouteController class");
                str = "";
            } else {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "getDefaultRoute()");
                }
                str = c.getDefaultRoute();
            }
        }
        return str;
    }

    public static synchronized void setVolumeChange() {
        synchronized (AudioPlatform.class) {
            if (d == null) {
                Log.log(6, PackageInfo.TAG, "setVolumeChange(): No instance found of VolumeController class");
            } else {
                if (Log.isLoggable(PackageInfo.TAG, 4)) {
                    Log.i(PackageInfo.TAG, "setVolumeChange()");
                }
                d.setVolumeChange();
            }
        }
    }

    public static synchronized void audioRecorderAvailable(boolean isAvailable) {
        synchronized (AudioPlatform.class) {
            if (Log.isLoggable(PackageInfo.TAG, 4)) {
                Log.i(PackageInfo.TAG, "audioRecorderAvailabe()");
            }
            if (isAvailable) {
                g.callbackAfterReleasingAudioRecorder();
            } else {
                g.callbackBeforeCreatingAudioRecorder();
            }
        }
    }
}
