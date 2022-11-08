package com.microsoft.media;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.microsoft.dl.Platform;
import com.microsoft.dl.audio.AudioPlatform;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RtcPalEnvironment {
    private static final String EXTERNAL_REG = "tmp/rtcpal_registry.reg";
    private static final String INTERNAL_REG = "rtcpal_registry.reg";
    private static String TAG = "RtcPalEnvironment";
    private static Context _context;
    private static boolean _palInited = false;

    public enum AudioEndpoint {
        SPEAKER(1),
        NON_SPEAKER(2),
        BLUETOOTH(4),
        EARPIECE(8),
        HEADSET_WITH_MIC(16),
        HEADSET_WITHOUT_MIC(32);
        
        private int mValue;

        private AudioEndpoint(int value) {
            this.mValue = value;
        }

        public final int getAudioEndpoint() {
            return this.mValue;
        }
    }

    private static native boolean isRelease();

    private static native void setAppPath(String str);

    private static native void setOSName(String str);

    private static native void setTracePath(String str);

    public static void setContext(Context ctx) {
        _context = ctx;
    }

    public static void initialize() {
        if (_context != null) {
            initialize(_context);
        }
    }

    private static void copyRtcPalRegistry(Context ctx) {
        IOException e;
        Throwable th;
        FileOutputStream os = null;
        InputStream is = null;
        try {
            File inputReg = new File(Environment.getExternalStorageDirectory(), EXTERNAL_REG);
            if (inputReg.exists()) {
                os = ctx.openFileOutput(INTERNAL_REG, 0);
                InputStream is2 = new FileInputStream(inputReg);
                try {
                    byte[] buff = new byte[1024];
                    while (true) {
                        int len = is2.read(buff);
                        if (len >= 0) {
                            os.write(buff, 0, len);
                        } else {
                            new StringBuilder("File ").append(inputReg.getAbsolutePath()).append(" was copied into internal memory and deleted");
                            inputReg.delete();
                            closeSilently(is2);
                            closeSilently(os);
                            is = is2;
                            return;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    is = is2;
                    try {
                        e.toString();
                        closeSilently(is);
                        closeSilently(os);
                    } catch (Throwable th2) {
                        th = th2;
                        closeSilently(is);
                        closeSilently(os);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    is = is2;
                    closeSilently(is);
                    closeSilently(os);
                    throw th;
                }
            }
            new StringBuilder().append(inputReg.getAbsolutePath()).append(" doesn't exist");
            closeSilently(null);
            closeSilently(null);
        } catch (IOException e3) {
            e = e3;
            e.toString();
            closeSilently(is);
            closeSilently(os);
        }
    }

    private static void closeSilently(Closeable cs) {
        if (cs != null) {
            try {
                cs.close();
            } catch (IOException e) {
            }
        }
    }

    public static boolean initialize(Context ctx, String path) {
        boolean result = initialize(ctx);
        if (result) {
            setTracePath(path);
        }
        return result;
    }

    public static boolean initialize(Context ctx) {
        if (NetworkPal.initialize(ctx)) {
            if (ctx != null) {
                Platform.initialize(ctx);
            }
            setAppPath(ctx.getFilesDir().getParentFile().toString());
            copyRtcPalRegistry(ctx);
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(ctx.getExternalCacheDir(), "");
                if (file.exists()) {
                    new StringBuilder("file already exists ").append(file.toString());
                    setTracePath(file.toString());
                } else {
                    try {
                        if (file.createNewFile()) {
                            new StringBuilder("createNewFile() succeeded, file exist status: ").append(file.exists());
                            setTracePath(file.toString());
                        } else {
                            new StringBuilder("createNewFile() failed, file exist status: ").append(file.exists());
                        }
                    } catch (IOException ex) {
                        new StringBuilder("createNewFile() IOException: ").append(ex.toString());
                    }
                }
            }
            StringBuffer osName = new StringBuffer();
            osName.append("Android " + parseBuildVersion() + "; Manufacturer: " + Build.MANUFACTURER + "; Product: " + Build.PRODUCT + "; Model: " + Build.MODEL + "; Hardware: " + Build.HARDWARE);
            setOSName(osName.toString());
            _palInited = true;
            return true;
        }
        RtcPalConfig.isLogcatEnabled();
        throw new ExceptionInInitializerError("NetworkPal initialization failed");
    }

    private static String parseBuildVersion() {
        if (TextUtils.isEmpty(VERSION.RELEASE)) {
            return "0.0";
        }
        String[] verArr = VERSION.RELEASE.split("\\.");
        StringBuilder builder = new StringBuilder();
        String dot = "";
        int i = 0;
        while (i < 2) {
            builder.append(dot);
            if (verArr.length <= i || TextUtils.isEmpty(verArr[i]) || !TextUtils.isDigitsOnly(verArr[i])) {
                builder.append("0");
            } else {
                builder.append(verArr[i]);
            }
            dot = ".";
            i++;
        }
        return builder.toString();
    }

    public static void uninitialize() {
        AudioPlatform.uninitialize();
        _palInited = false;
    }

    public static boolean IsPalInitialized() {
        return _palInited;
    }

    public static void setConversationActivity(Activity activity) {
        activity.setVolumeControlStream(0);
    }

    private static String toString(AudioEndpoint endpoint) {
        if (endpoint == AudioEndpoint.SPEAKER) {
            return "Speaker";
        }
        if (endpoint == AudioEndpoint.NON_SPEAKER) {
            return "Non_speaker";
        }
        if (endpoint == AudioEndpoint.BLUETOOTH) {
            return "Bluetooth";
        }
        if (endpoint == AudioEndpoint.EARPIECE) {
            return "Earpiece";
        }
        if (endpoint == AudioEndpoint.HEADSET_WITH_MIC) {
            return "Headset_with_mic";
        }
        return endpoint == AudioEndpoint.HEADSET_WITHOUT_MIC ? "Headset_without_mic" : "Invalid route";
    }

    public static int setActiveEndpoint(AudioEndpoint endpoint) {
        new StringBuilder("setActiveEndpoint(").append(endpoint.getAudioEndpoint()).append(")");
        AudioPlatform.setRoute(toString(endpoint));
        return 1;
    }

    public static void setVolumeChange() {
        AudioPlatform.setVolumeChange();
    }
}
