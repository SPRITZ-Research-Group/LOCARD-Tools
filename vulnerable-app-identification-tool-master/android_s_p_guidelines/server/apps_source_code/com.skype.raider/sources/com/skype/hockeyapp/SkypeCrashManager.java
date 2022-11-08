package com.skype.hockeyapp;

import android.app.Application;
import com.facebook.common.logging.FLog;
import com.skype.android.breakpad.BreakpadWrapper;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import net.hockeyapp.android.a;

public class SkypeCrashManager {
    private static volatile boolean a = false;
    private static volatile CrashFormatter b;
    private static volatile DumpUploader c;
    private static volatile SkypeCrashListener d;
    private static volatile SkypeExceptionHandler e;

    public static void a(Application context) {
        if (!a) {
            a = true;
            a.a(context);
            d = SkypeCrashListener.a(context);
            File crashesDirectory = context.getFilesDir();
            UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            try {
                BreakpadWrapper.a();
                BreakpadWrapper.a(crashesDirectory.getAbsolutePath(), a.b, a.a);
            } catch (Throwable e) {
                FLog.e("CrashManager", "Breakpad initialization failed.", e);
            }
            b = new CrashFormatter(crashesDirectory, d);
            UncaughtExceptionHandler skypeExceptionHandler = new SkypeExceptionHandler(b, defaultHandler);
            e = skypeExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(skypeExceptionHandler);
            c = new DumpUploader(context, crashesDirectory, b);
        }
    }

    public static void a() {
        SkypeExceptionHandler skypeExceptionHandler = e;
    }

    public static void a(String appId) {
        FLog.i("React", "Check for hockeyapp crashes");
        c.a(appId);
    }

    public static boolean b() {
        FLog.i("React", "Check for new app crashes");
        return c.a();
    }

    public static CrashFormatter c() {
        if (b == null) {
            b("crashFormatter");
        }
        return b;
    }

    public static SkypeCrashListener d() {
        if (d == null) {
            b("crashListener");
        }
        return d;
    }

    private static void b(String component) {
        throw new IllegalStateException(component + " is not initialized. You need to call RNHockeyAppPackage.init method in your Application.onCreate as a first step to avoid it");
    }
}
