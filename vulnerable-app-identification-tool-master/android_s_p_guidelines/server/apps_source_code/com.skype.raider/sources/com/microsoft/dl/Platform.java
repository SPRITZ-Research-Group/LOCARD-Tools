package com.microsoft.dl;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public final class Platform {
    private static final File a = new File("/sdcard");
    private static PlatformInfo b;

    public static class PlatformInfo {
        private final File a;
        private final File b;
        private final Context c;

        public PlatformInfo(Context context) {
            this.a = context != null ? context.getCacheDir() : Platform.a;
            this.b = Environment.getExternalStorageDirectory();
            this.c = context;
        }

        public final File getCacheDir() {
            return this.a;
        }

        public final File getStorageDir() {
            return this.b;
        }

        public final Context getAppContext() {
            return this.c;
        }

        public final String toString() {
            return getClass().getSimpleName() + " [cacheDir=" + this.a + ", storageDir=" + this.b + "]";
        }
    }

    public static native long getTimestamp();

    private Platform() {
    }

    public static synchronized void initialize(Context context) {
        synchronized (Platform.class) {
            if (b == null) {
                b = new PlatformInfo(context);
            }
        }
    }

    public static synchronized PlatformInfo getInfo() {
        PlatformInfo platformInfo;
        synchronized (Platform.class) {
            platformInfo = b;
        }
        return platformInfo;
    }
}
