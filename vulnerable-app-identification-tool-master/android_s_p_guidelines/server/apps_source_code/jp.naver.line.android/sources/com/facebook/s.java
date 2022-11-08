package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.facebook.internal.aq;
import com.facebook.internal.b;
import com.facebook.internal.bg;
import com.facebook.internal.bj;
import com.facebook.internal.bn;
import defpackage.amm;
import defpackage.anj;
import defpackage.ank;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class s {
    private static final String a = s.class.getCanonicalName();
    private static final HashSet<ai> b = new HashSet(Arrays.asList(new ai[]{ai.DEVELOPER_ERRORS}));
    private static Executor c;
    private static volatile String d;
    private static volatile String e;
    private static volatile String f;
    private static volatile Boolean g;
    private static volatile String h = "facebook.com";
    private static AtomicLong i = new AtomicLong(65536);
    private static volatile boolean j = false;
    private static boolean k = false;
    private static aq<File> l;
    private static Context m;
    private static int n = 64206;
    private static final Object o = new Object();
    private static String p = bg.d();
    private static final BlockingQueue<Runnable> q = new LinkedBlockingQueue(10);
    private static final ThreadFactory r = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(0);

        public final Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder("FacebookSdk #");
            stringBuilder.append(this.a.incrementAndGet());
            return new Thread(runnable, stringBuilder.toString());
        }
    };
    private static Boolean s = Boolean.FALSE;

    public static String h() {
        return "4.34.0";
    }

    @Deprecated
    public static synchronized void a(Context context) {
        synchronized (s.class) {
            a(context, null);
        }
    }

    @java.lang.Deprecated
    public static synchronized void a(final android.content.Context r5, final com.facebook.t r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.s.a(android.content.Context, com.facebook.t):void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = com.facebook.s.class;
        monitor-enter(r0);
        r1 = s;	 Catch:{ all -> 0x0109 }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x0109 }
        if (r1 == 0) goto L_0x0012;	 Catch:{ all -> 0x0109 }
    L_0x000b:
        if (r6 == 0) goto L_0x0010;	 Catch:{ all -> 0x0109 }
    L_0x000d:
        r6.a();	 Catch:{ all -> 0x0109 }
    L_0x0010:
        monitor-exit(r0);
        return;
    L_0x0012:
        r1 = "applicationContext";	 Catch:{ all -> 0x0109 }
        com.facebook.internal.bn.a(r5, r1);	 Catch:{ all -> 0x0109 }
        r1 = 0;	 Catch:{ all -> 0x0109 }
        com.facebook.internal.bn.b(r5, r1);	 Catch:{ all -> 0x0109 }
        com.facebook.internal.bn.a(r5, r1);	 Catch:{ all -> 0x0109 }
        r1 = r5.getApplicationContext();	 Catch:{ all -> 0x0109 }
        m = r1;	 Catch:{ all -> 0x0109 }
        if (r1 == 0) goto L_0x00b4;
    L_0x0026:
        r2 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x00b4 }
        r1 = r1.getPackageName();	 Catch:{ NameNotFoundException -> 0x00b4 }
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x00b4 }
        r1 = r2.getApplicationInfo(r1, r3);	 Catch:{ NameNotFoundException -> 0x00b4 }
        if (r1 == 0) goto L_0x00b4;
    L_0x0036:
        r2 = r1.metaData;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x003c;	 Catch:{ all -> 0x0109 }
    L_0x003a:
        goto L_0x00b4;	 Catch:{ all -> 0x0109 }
    L_0x003c:
        r2 = d;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x0074;	 Catch:{ all -> 0x0109 }
    L_0x0040:
        r2 = r1.metaData;	 Catch:{ all -> 0x0109 }
        r3 = "com.facebook.sdk.ApplicationId";	 Catch:{ all -> 0x0109 }
        r2 = r2.get(r3);	 Catch:{ all -> 0x0109 }
        r3 = r2 instanceof java.lang.String;	 Catch:{ all -> 0x0109 }
        if (r3 == 0) goto L_0x0067;	 Catch:{ all -> 0x0109 }
    L_0x004c:
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x0109 }
        r3 = java.util.Locale.ROOT;	 Catch:{ all -> 0x0109 }
        r3 = r2.toLowerCase(r3);	 Catch:{ all -> 0x0109 }
        r4 = "fb";	 Catch:{ all -> 0x0109 }
        r3 = r3.startsWith(r4);	 Catch:{ all -> 0x0109 }
        if (r3 == 0) goto L_0x0064;	 Catch:{ all -> 0x0109 }
    L_0x005c:
        r3 = 2;	 Catch:{ all -> 0x0109 }
        r2 = r2.substring(r3);	 Catch:{ all -> 0x0109 }
        d = r2;	 Catch:{ all -> 0x0109 }
        goto L_0x0074;	 Catch:{ all -> 0x0109 }
    L_0x0064:
        d = r2;	 Catch:{ all -> 0x0109 }
        goto L_0x0074;	 Catch:{ all -> 0x0109 }
    L_0x0067:
        r2 = r2 instanceof java.lang.Integer;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x006c;	 Catch:{ all -> 0x0109 }
    L_0x006b:
        goto L_0x0074;	 Catch:{ all -> 0x0109 }
    L_0x006c:
        r5 = new com.facebook.n;	 Catch:{ all -> 0x0109 }
        r6 = "App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.";	 Catch:{ all -> 0x0109 }
        r5.<init>(r6);	 Catch:{ all -> 0x0109 }
        throw r5;	 Catch:{ all -> 0x0109 }
    L_0x0074:
        r2 = e;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x0082;	 Catch:{ all -> 0x0109 }
    L_0x0078:
        r2 = r1.metaData;	 Catch:{ all -> 0x0109 }
        r3 = "com.facebook.sdk.ApplicationName";	 Catch:{ all -> 0x0109 }
        r2 = r2.getString(r3);	 Catch:{ all -> 0x0109 }
        e = r2;	 Catch:{ all -> 0x0109 }
    L_0x0082:
        r2 = f;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x0090;	 Catch:{ all -> 0x0109 }
    L_0x0086:
        r2 = r1.metaData;	 Catch:{ all -> 0x0109 }
        r3 = "com.facebook.sdk.ClientToken";	 Catch:{ all -> 0x0109 }
        r2 = r2.getString(r3);	 Catch:{ all -> 0x0109 }
        f = r2;	 Catch:{ all -> 0x0109 }
    L_0x0090:
        r2 = n;	 Catch:{ all -> 0x0109 }
        r3 = 64206; // 0xface float:8.9972E-41 double:3.1722E-319;	 Catch:{ all -> 0x0109 }
        if (r2 != r3) goto L_0x00a1;	 Catch:{ all -> 0x0109 }
    L_0x0097:
        r2 = r1.metaData;	 Catch:{ all -> 0x0109 }
        r4 = "com.facebook.sdk.CallbackOffset";	 Catch:{ all -> 0x0109 }
        r2 = r2.getInt(r4, r3);	 Catch:{ all -> 0x0109 }
        n = r2;	 Catch:{ all -> 0x0109 }
    L_0x00a1:
        r2 = g;	 Catch:{ all -> 0x0109 }
        if (r2 != 0) goto L_0x00b4;	 Catch:{ all -> 0x0109 }
    L_0x00a5:
        r1 = r1.metaData;	 Catch:{ all -> 0x0109 }
        r2 = "com.facebook.sdk.AutoLogAppEventsEnabled";	 Catch:{ all -> 0x0109 }
        r3 = 1;	 Catch:{ all -> 0x0109 }
        r1 = r1.getBoolean(r2, r3);	 Catch:{ all -> 0x0109 }
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ all -> 0x0109 }
        g = r1;	 Catch:{ all -> 0x0109 }
    L_0x00b4:
        r1 = d;	 Catch:{ all -> 0x0109 }
        r1 = com.facebook.internal.bj.a(r1);	 Catch:{ all -> 0x0109 }
        if (r1 != 0) goto L_0x0101;	 Catch:{ all -> 0x0109 }
    L_0x00bc:
        r1 = m;	 Catch:{ all -> 0x0109 }
        r1 = r1 instanceof android.app.Application;	 Catch:{ all -> 0x0109 }
        if (r1 == 0) goto L_0x00d3;	 Catch:{ all -> 0x0109 }
    L_0x00c2:
        r1 = g;	 Catch:{ all -> 0x0109 }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x0109 }
        if (r1 == 0) goto L_0x00d3;	 Catch:{ all -> 0x0109 }
    L_0x00ca:
        r1 = m;	 Catch:{ all -> 0x0109 }
        r1 = (android.app.Application) r1;	 Catch:{ all -> 0x0109 }
        r2 = d;	 Catch:{ all -> 0x0109 }
        defpackage.anh.a(r1, r2);	 Catch:{ all -> 0x0109 }
    L_0x00d3:
        r1 = java.lang.Boolean.TRUE;	 Catch:{ all -> 0x0109 }
        s = r1;	 Catch:{ all -> 0x0109 }
        com.facebook.internal.u.a();	 Catch:{ all -> 0x0109 }
        com.facebook.internal.av.b();	 Catch:{ all -> 0x0109 }
        r1 = m;	 Catch:{ all -> 0x0109 }
        com.facebook.internal.BoltsMeasurementEventListener.a(r1);	 Catch:{ all -> 0x0109 }
        r1 = new com.facebook.internal.aq;	 Catch:{ all -> 0x0109 }
        r2 = new com.facebook.s$2;	 Catch:{ all -> 0x0109 }
        r2.<init>();	 Catch:{ all -> 0x0109 }
        r1.<init>(r2);	 Catch:{ all -> 0x0109 }
        l = r1;	 Catch:{ all -> 0x0109 }
        r1 = new java.util.concurrent.FutureTask;	 Catch:{ all -> 0x0109 }
        r2 = new com.facebook.s$3;	 Catch:{ all -> 0x0109 }
        r2.<init>(r6, r5);	 Catch:{ all -> 0x0109 }
        r1.<init>(r2);	 Catch:{ all -> 0x0109 }
        r5 = d();	 Catch:{ all -> 0x0109 }
        r5.execute(r1);	 Catch:{ all -> 0x0109 }
        monitor-exit(r0);
        return;
    L_0x0101:
        r5 = new com.facebook.n;	 Catch:{ all -> 0x0109 }
        r6 = "A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.";	 Catch:{ all -> 0x0109 }
        r5.<init>(r6);	 Catch:{ all -> 0x0109 }
        throw r5;	 Catch:{ all -> 0x0109 }
    L_0x0109:
        r5 = move-exception;
        monitor-exit(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.s.a(android.content.Context, com.facebook.t):void");
    }

    public static synchronized boolean a() {
        boolean booleanValue;
        synchronized (s.class) {
            booleanValue = s.booleanValue();
        }
        return booleanValue;
    }

    public static boolean a(ai aiVar) {
        boolean z;
        synchronized (b) {
            z = j && b.contains(aiVar);
        }
        return z;
    }

    public static boolean b() {
        return j;
    }

    public static boolean c() {
        return k;
    }

    public static Executor d() {
        synchronized (o) {
            if (c == null) {
                c = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return c;
    }

    public static String e() {
        return h;
    }

    public static Context f() {
        bn.a();
        return m;
    }

    public static String g() {
        String.format("getGraphApiVersion: %s", new Object[]{p});
        return p;
    }

    public static void a(Context context, final String str) {
        context = context.getApplicationContext();
        d().execute(new Runnable() {
            public final void run() {
                s.b(context, str);
            }
        });
    }

    static void b(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        try {
            b a = b.a(context);
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("ping");
            String stringBuilder2 = stringBuilder.toString();
            long j = sharedPreferences.getLong(stringBuilder2, 0);
            GraphRequest a2 = GraphRequest.a(null, String.format("%s/activities", new Object[]{str}), anj.a(ank.MOBILE_INSTALL_EVENT, a, amm.b(context), b(context), context));
            if (j == 0) {
                GraphRequest.a(a2);
                Editor edit = sharedPreferences.edit();
                edit.putLong(stringBuilder2, System.currentTimeMillis());
                edit.apply();
            }
        } catch (Throwable e) {
            throw new n("An error occurred while publishing install.", e);
        } catch (Exception e2) {
            bj.a("Facebook-publish", e2);
        }
    }

    public static boolean b(Context context) {
        bn.a();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static long i() {
        bn.a();
        return i.get();
    }

    public static java.lang.String c(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.s.c(android.content.Context):java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        com.facebook.internal.bn.a();
        r0 = 0;
        if (r3 != 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r1 = r3.getPackageManager();
        if (r1 != 0) goto L_0x000e;
    L_0x000d:
        return r0;
    L_0x000e:
        r3 = r3.getPackageName();
        r2 = 64;
        r3 = r1.getPackageInfo(r3, r2);	 Catch:{ NameNotFoundException -> 0x003f }
        r1 = r3.signatures;
        if (r1 == 0) goto L_0x003e;
    L_0x001c:
        r1 = r1.length;
        if (r1 != 0) goto L_0x0020;
    L_0x001f:
        goto L_0x003e;
    L_0x0020:
        r1 = "SHA-1";	 Catch:{ NoSuchAlgorithmException -> 0x003d }
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x003d }
        r3 = r3.signatures;
        r0 = 0;
        r3 = r3[r0];
        r3 = r3.toByteArray();
        r1.update(r3);
        r3 = r1.digest();
        r0 = 9;
        r3 = android.util.Base64.encodeToString(r3, r0);
        return r3;
    L_0x003d:
        return r0;
    L_0x003e:
        return r0;
    L_0x003f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.s.c(android.content.Context):java.lang.String");
    }

    public static String j() {
        bn.a();
        return d;
    }

    public static String k() {
        bn.a();
        return e;
    }

    public static String l() {
        bn.a();
        return f;
    }

    public static boolean m() {
        bn.a();
        return g.booleanValue();
    }

    public static File n() {
        bn.a();
        return (File) l.a();
    }

    public static int o() {
        bn.a();
        return n;
    }
}
