package com.skype.slimcore.skylib;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.microsoft.media.NGCPcmHost;
import com.skype.GIImpl;
import com.skype.SkyLib;
import com.skype.SkyLibImpl;
import com.skype.Utility;
import com.skype.slimcore.logging.MethodTrace;
import java.io.File;
import java.util.Locale;

public class SkyLibWrapper {
    private static SkyLibWrapper a;
    private static boolean b = false;
    private SkyLib c;
    private SkyLibState d = SkyLibState.TERMINATED;
    private NGCPcmHost e;
    private PcmHostCallback f;
    private VideoHostInitializer g;
    private String h;

    public enum SkyLibState {
        INITIALIZED,
        STARTED,
        TERMINATED
    }

    public static synchronized SkyLibWrapper a() {
        SkyLibWrapper skyLibWrapper;
        synchronized (SkyLibWrapper.class) {
            if (a == null) {
                a = new SkyLibWrapper();
            }
            skyLibWrapper = a;
        }
        return skyLibWrapper;
    }

    private SkyLibWrapper() {
    }

    public final SkyLibState b() {
        return this.d;
    }

    public final synchronized void a(Context context) {
        if (this.e == null) {
            c(context);
            PackageManager packageManager = context.getPackageManager();
            this.f = new PcmHostCallback();
            this.e = new NGCPcmHost(context, packageManager.hasSystemFeature("android.hardware.telephony"), false, true, this.f);
        }
    }

    public final synchronized void a(Context context, InitializerConfiguration configuration) {
        if (this.d != SkyLibState.INITIALIZED) {
            if (context == null) {
                throw new IllegalArgumentException("null context");
            }
            c(context);
            String version = configuration.a();
            String dbPath = configuration.b();
            VideoHostInitializer videoHostInitializer = configuration.c();
            boolean saveLogs = configuration.d();
            boolean encryptLogs = configuration.e();
            if (version == null) {
                throw new IllegalArgumentException("null version");
            }
            if (dbPath == null) {
                dbPath = context.getFilesDir().getAbsolutePath();
            }
            if (videoHostInitializer == null) {
                throw new IllegalArgumentException("null videoHostInitializer");
            }
            String logfile;
            MethodTrace trace = new MethodTrace("SkyLibWrapper", "initialize");
            File logDir = context.getExternalCacheDir();
            if (!saveLogs || logDir == null) {
                logfile = "";
            } else {
                logfile = String.format(Locale.US, "%s/slimcore_%d.log", new Object[]{logDir.getAbsolutePath(), Long.valueOf(SystemClock.elapsedRealtime())});
            }
            try {
                Utility.initMedia();
                this.g = videoHostInitializer;
            } catch (Throwable e) {
                FLog.e("SkyLibWrapper", "Error initializing video host", e);
            }
            GIImpl.initPlatform(logfile, encryptLogs, true);
            this.c = new SkyLibImpl(version, dbPath, false, false);
            this.c.getSetup().setStr("*Lib/Media/MediaLibraryLocation", context.getApplicationInfo().nativeLibraryDir);
            a(context);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.c.setAndroidId(Secure.getString(context.getContentResolver(), "android_id"));
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                Object deviceId = telephonyManager.getDeviceId();
                if (!TextUtils.isEmpty(deviceId)) {
                    this.c.setIMEI(deviceId);
                }
            }
            this.d = SkyLibState.INITIALIZED;
            trace.a();
        }
    }

    public final synchronized SkyLib b(Context context) {
        SkyLib skyLib;
        if (this.d == SkyLibState.TERMINATED) {
            FLog.e("SkyLibWrapper", "Failed to start SkyLib - not initialized");
            skyLib = null;
        } else {
            MethodTrace traceStart = new MethodTrace("SkyLibWrapper", "start");
            this.g.a(context);
            this.c.start(true);
            traceStart.a();
            String language = context.getResources().getConfiguration().locale.getLanguage();
            if (!(language == null || language.equals(this.h))) {
                this.h = language;
                this.c.getSetup().setStr("*Lib/Call/NG/LanguageId", this.h);
            }
            this.d = SkyLibState.STARTED;
            skyLib = this.c;
        }
        return skyLib;
    }

    public final synchronized void c() {
        MethodTrace trace = new MethodTrace("SkyLibWrapper", "stop");
        try {
            this.c.stop(true);
            GIImpl.uninitPlatform();
            this.d = SkyLibState.TERMINATED;
            trace.a();
            if (this.g != null) {
                this.c = null;
            } else {
                this.c = null;
            }
        } catch (Throwable th) {
            this.d = SkyLibState.TERMINATED;
            trace.a();
        }
    }

    private static void c(Context context) {
        if (!b) {
            Utility.initialize(context, null, context.getCacheDir().getAbsolutePath(), context.getFilesDir().getAbsolutePath());
            System.loadLibrary("rt-java-bindings");
            b = true;
        }
    }

    public final SkyLib d() {
        return this.c;
    }

    public final NGCPcmHost e() {
        return this.e;
    }

    public final PcmHostCallback f() {
        return this.f;
    }
}
