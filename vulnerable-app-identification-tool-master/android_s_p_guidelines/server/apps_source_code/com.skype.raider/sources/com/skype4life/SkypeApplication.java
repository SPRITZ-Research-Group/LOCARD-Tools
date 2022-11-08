package com.skype4life;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Debug;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import com.b.a.a;
import com.facebook.common.e.b;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ak;
import com.facebook.react.j;
import com.facebook.react.l;
import com.facebook.react.modules.network.e;
import com.facebook.react.p;
import com.facebook.react.uimanager.y;
import com.facebook.soloader.SoLoader;
import com.skype.hockeyapp.SkypeCrashManager;
import com.skype4life.b.c;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Protocol;

public class SkypeApplication extends MultiDexApplication implements j {
    private static long a;
    private final e b = new e(this);
    private final Handler c = new Handler();
    private boolean d;
    private String e;

    public final p a() {
        return this.b;
    }

    public void onCreate() {
        super.onCreate();
        a = System.currentTimeMillis();
        a("configureLogging", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                c.a(new File(this.a.getExternalCacheDir(), "com.skype.raider.%g.log"));
            }
        });
        a("configureHockeyApp", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                SkypeCrashManager.a(this.a);
            }
        });
        a("parsingProcessName", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                SkypeApplication.a(this.a);
            }
        });
        ak.a();
        FLog.i("ReactApp", "onCreate start");
        if (this.d) {
            FLog.i("ReactApp", "Ignore app init. surrogate process: " + this.e);
            return;
        }
        SoLoader.b((Context) this);
        a("leakCanaryInstall", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                a aVar = a.a;
            }
        });
        a("configureOkHttp", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                e.a(new Builder().connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).retryOnConnectionFailure(false).followRedirects(true).followSslRedirects(true).cookieJar(new com.facebook.react.modules.network.j()).cache(new Cache(new File(this.a.getFilesDir(), "http-cache"), 10485760)).addInterceptor(new a()).protocols(Arrays.asList(new Protocol[]{Protocol.SPDY_3, Protocol.HTTP_2, Protocol.HTTP_1_1})).build());
            }
        });
        a("configureStetho", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
            }
        });
        a("configureYoga", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                y.a().setUseLegacyStretchBehaviour(false);
            }
        });
        a("mReactNativeHost.getReactInstanceManager", new Runnable(this) {
            final /* synthetic */ SkypeApplication a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b.a();
            }
        });
        FLog.i("ReactApp", "onCreate complete in %dms", Integer.valueOf((int) (System.currentTimeMillis() - a)));
    }

    public void onTrimMemory(int level) {
        FLog.i("ReactApp", "onTrimMemory level: " + level);
        c("BeforeOnTrim");
        super.onTrimMemory(level);
        List<com.facebook.common.e.c> trimmables = this.b.f();
        switch (level) {
            case 5:
            case 10:
            case 15:
                FLog.i("ReactApp", "onTrimMemory flush Fresco in-memory cache FG mode. level=%d", Integer.valueOf(level));
                for (com.facebook.common.e.c a : trimmables) {
                    a.a(b.OnSystemLowMemoryWhileAppInForeground);
                }
                break;
            case 20:
            case 40:
            case 60:
            case 80:
                FLog.i("ReactApp", "onTrimMemory flush Fresco in-memory cache BG mode. level=%d", Integer.valueOf(level));
                for (com.facebook.common.e.c a2 : trimmables) {
                    a2.a(b.OnSystemLowMemoryWhileAppInBackground);
                }
                break;
            default:
                FLog.i("ReactApp", "onTrimMemory level=%d", Integer.valueOf(level));
                break;
        }
        a("AfterOnTrim");
    }

    public void onLowMemory() {
        FLog.w("ReactApp", "onLowMemory");
        c("BeforeOnLowMemory");
        Runtime.getRuntime().gc();
        super.onLowMemory();
        a("AfterOnLowMemory");
    }

    public final void a(l.b listener) {
        this.b.a(listener);
    }

    public static long b() {
        return a;
    }

    public static void c() {
        a = System.currentTimeMillis();
    }

    private void a(final String info) {
        this.c.postDelayed(new Runnable(this) {
            final /* synthetic */ SkypeApplication b;

            public final void run() {
                this.b.c(info);
            }
        }, 1000);
    }

    private static String a(long value) {
        try {
            return new DecimalFormat("###,###,###,###,###").format(value);
        } catch (Exception e) {
            return Long.toString(value);
        }
    }

    private static String b(String value) {
        try {
            return a((long) Integer.parseInt(value));
        } catch (Exception e) {
            return value;
        }
    }

    private void c(String info) {
        Runtime runtime = Runtime.getRuntime();
        long usedMiB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        long maxHeapMib = runtime.maxMemory() / 1048576;
        long availHeapMib = maxHeapMib - usedMiB;
        MemoryInfo systemMemInfo = new MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        activityManager.getMemoryInfo(systemMemInfo);
        Debug.MemoryInfo[] processMemInfo = activityManager.getProcessMemoryInfo(new int[]{Process.myPid()});
        if (processMemInfo.length == 0) {
            processMemInfo[0] = new Debug.MemoryInfo();
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("dumpMemoryUsage [").append(info).append("]");
        strBuilder.append(" JavaUsedMemInMB=").append(a(usedMiB)).append(" MB JavaMaxHeapSizeInMB=").append(a(maxHeapMib)).append(" MB JavaAvailHeapSizeInMB=").append(a(availHeapMib)).append(" MB\nSysTotalMem=").append(a(systemMemInfo.totalMem)).append(" SysAvailMem=").append(a(systemMemInfo.availMem)).append(" Threshold=").append(a(systemMemInfo.threshold)).append(" InLowMem=").append(systemMemInfo.lowMemory).append("\ndalvikPss=").append(a((long) processMemInfo[0].dalvikPss)).append(" KB dalvikPrivateDirty=").append(a((long) processMemInfo[0].dalvikPrivateDirty)).append(" KB dalvikSharedDirty=").append(a((long) processMemInfo[0].dalvikSharedDirty)).append(" KB\nnativePss=").append(a((long) processMemInfo[0].nativePss)).append(" KB nativePrivateDirty=").append(a((long) processMemInfo[0].nativePrivateDirty)).append(" KB nativeSharedDirty=").append(a((long) processMemInfo[0].nativeSharedDirty)).append(" KB\notherPss=").append(a((long) processMemInfo[0].otherPss)).append(" KB otherPrivateDirty=").append(a((long) processMemInfo[0].otherPrivateDirty)).append(" KB otherSharedDirty=").append(a((long) processMemInfo[0].otherSharedDirty)).append(" KB\nsummary.java-heap=").append(b(processMemInfo[0].getMemoryStat("summary.java-heap"))).append(" KB summary.native-heap=").append(b(processMemInfo[0].getMemoryStat("summary.native-heap"))).append(" KB summary.code=").append(b(processMemInfo[0].getMemoryStat("summary.code"))).append(" KB summary.stack=").append(b(processMemInfo[0].getMemoryStat("summary.stack"))).append(" KB summary.graphics=").append(b(processMemInfo[0].getMemoryStat("summary.graphics"))).append(" KB summary.private-other=").append(b(processMemInfo[0].getMemoryStat("summary.private-other"))).append(" KB summary.system=").append(b(processMemInfo[0].getMemoryStat("summary.system"))).append(" KB summary.total-pss=").append(b(processMemInfo[0].getMemoryStat("summary.total-pss"))).append(" KB summary.total-swap=").append(b(processMemInfo[0].getMemoryStat("summary.total-swap"))).append(" KB");
        FLog.i("ReactApp", strBuilder.toString());
    }

    private static void a(String tag, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        FLog.i("ReactApp", "%s took %dms", (Object) tag, Integer.valueOf((int) (System.currentTimeMillis() - start)));
    }

    static /* synthetic */ void a(SkypeApplication x0) {
        Throwable e;
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
            try {
                inputStream = new BufferedInputStream(fileInputStream);
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = inputStream.read();
                    if (read <= 0) {
                        break;
                    }
                    stringBuilder.append((char) read);
                }
                x0.e = stringBuilder.toString();
                if (!x0.getApplicationInfo().packageName.equals(x0.e)) {
                    x0.d = true;
                }
                try {
                    inputStream.close();
                } catch (Exception e2) {
                }
            } catch (Exception e3) {
                e = e3;
                inputStream = fileInputStream;
                try {
                    FLog.i("ReactApp", "Failed to parse process name", e);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                inputStream = fileInputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw e;
            }
        } catch (Exception e6) {
            e = e6;
            FLog.i("ReactApp", "Failed to parse process name", e);
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
