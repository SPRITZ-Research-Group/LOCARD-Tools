package com.applovin.impl.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinLogger;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.apache.cordova.networkinformation.NetworkManager;

class ah {
    private static String e;
    private static String f;
    private static int g;
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final Map<Class, Object> d;

    ah(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl != null) {
            this.a = appLovinSdkImpl;
            this.b = appLovinSdkImpl.getLogger();
            this.c = appLovinSdkImpl.getApplicationContext();
            this.d = Collections.synchronizedMap(new HashMap());
            return;
        }
        throw new IllegalArgumentException("No sdk specified");
    }

    private com.applovin.impl.sdk.am a(com.applovin.impl.sdk.am r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ah.a(com.applovin.impl.sdk.am):com.applovin.impl.sdk.am. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        if (r5 != 0) goto L_0x0007;
    L_0x0002:
        r5 = new com.applovin.impl.sdk.am;
        r5.<init>();
    L_0x0007:
        r0 = r4.c;
        r0 = com.applovin.impl.sdk.ac.a(r0);
        r5.w = r0;
        r0 = r4.c;
        r0 = com.applovin.impl.sdk.ac.b(r0);
        r5.x = r0;
        r0 = r4.a;
        r1 = com.applovin.impl.sdk.ea.cp;
        r0 = r0.get(r1);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x002e;
    L_0x0027:
        r0 = r4.i();
    L_0x002b:
        r5.r = r0;
        goto L_0x0030;
    L_0x002e:
        r0 = 0;
        goto L_0x002b;
    L_0x0030:
        r0 = r4.a;
        r1 = com.applovin.impl.sdk.ea.co;
        r0 = r0.get(r1);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0046;
    L_0x0040:
        r0 = r4.k();
        r5.q = r0;
    L_0x0046:
        r0 = r4.c;	 Catch:{ Throwable -> 0x006c }
        r1 = "audio";	 Catch:{ Throwable -> 0x006c }
        r0 = r0.getSystemService(r1);	 Catch:{ Throwable -> 0x006c }
        r0 = (android.media.AudioManager) r0;	 Catch:{ Throwable -> 0x006c }
        if (r0 == 0) goto L_0x0076;	 Catch:{ Throwable -> 0x006c }
    L_0x0052:
        r1 = r4.a;	 Catch:{ Throwable -> 0x006c }
        r2 = com.applovin.impl.sdk.ea.cv;	 Catch:{ Throwable -> 0x006c }
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x006c }
        r1 = (java.lang.Float) r1;	 Catch:{ Throwable -> 0x006c }
        r1 = r1.floatValue();	 Catch:{ Throwable -> 0x006c }
        r2 = 3;	 Catch:{ Throwable -> 0x006c }
        r0 = r0.getStreamVolume(r2);	 Catch:{ Throwable -> 0x006c }
        r0 = (float) r0;	 Catch:{ Throwable -> 0x006c }
        r0 = r0 * r1;	 Catch:{ Throwable -> 0x006c }
        r0 = (int) r0;	 Catch:{ Throwable -> 0x006c }
        r5.s = r0;	 Catch:{ Throwable -> 0x006c }
        goto L_0x0076;
    L_0x006c:
        r0 = move-exception;
        r1 = r4.b;
        r2 = "DataCollector";
        r3 = "Unable to collect volume";
        r1.e(r2, r3, r0);
    L_0x0076:
        r0 = r4.a;
        r1 = com.applovin.impl.sdk.ea.cy;
        r0 = r0.get(r1);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x00a6;
    L_0x0086:
        r0 = e;
        if (r0 != 0) goto L_0x009a;
    L_0x008a:
        r0 = r4.o();
        r1 = com.applovin.sdk.AppLovinSdkUtils.isValidString(r0);
        if (r1 == 0) goto L_0x0097;
    L_0x0094:
        e = r0;
        goto L_0x009a;
    L_0x0097:
        r0 = "";
        goto L_0x0094;
    L_0x009a:
        r0 = e;
        r0 = com.applovin.sdk.AppLovinSdkUtils.isValidString(r0);
        if (r0 == 0) goto L_0x00a6;
    L_0x00a2:
        r0 = e;
        r5.t = r0;
    L_0x00a6:
        r0 = r4.a;
        r0 = r0.getSettingsManager();
        r1 = com.applovin.impl.sdk.ea.cm;
        r0 = r0.a(r1);
        r0 = (java.lang.String) r0;
        r1 = f;
        if (r1 == 0) goto L_0x00c6;
    L_0x00b8:
        r1 = f;
        r1 = r0.equalsIgnoreCase(r1);
        if (r1 != 0) goto L_0x00c1;
    L_0x00c0:
        goto L_0x00c6;
    L_0x00c1:
        r0 = g;
        r5.p = r0;
        goto L_0x00de;
    L_0x00c6:
        r1 = 0;
        f = r0;	 Catch:{ Throwable -> 0x00dc }
        r2 = r4.c;	 Catch:{ Throwable -> 0x00dc }
        r2 = r2.getPackageManager();	 Catch:{ Throwable -> 0x00dc }
        r0 = r2.getPackageInfo(r0, r1);	 Catch:{ Throwable -> 0x00dc }
        r2 = r0.versionCode;	 Catch:{ Throwable -> 0x00dc }
        r5.p = r2;	 Catch:{ Throwable -> 0x00dc }
        r0 = r0.versionCode;	 Catch:{ Throwable -> 0x00dc }
        g = r0;	 Catch:{ Throwable -> 0x00dc }
        goto L_0x00de;
    L_0x00dc:
        g = r1;
    L_0x00de:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ah.a(com.applovin.impl.sdk.am):com.applovin.impl.sdk.am");
    }

    static boolean a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        } else if (context != null) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    private boolean a(String str, ec<String> ecVar) {
        for (String startsWith : aa.a((String) this.a.get((ec) ecVar))) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    private String b(String str) {
        int length = str.length();
        int[] iArr = new int[]{11, 12, 10, 3, 2, 1, 15, 10, 15, 14};
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = str.charAt(i);
            for (int i2 = 9; i2 >= 0; i2--) {
                cArr[i] = (char) (cArr[i] ^ iArr[i2]);
            }
        }
        return new String(cArr);
    }

    private String f() {
        String str = NetworkManager.TYPE_NONE;
        try {
            String str2;
            int a = gd.a(this.c);
            if (a == 1) {
                str2 = "portrait";
            } else if (a != 2) {
                return str;
            } else {
                str2 = "landscape";
            }
            return str2;
        } catch (Throwable th) {
            this.a.getLogger().e("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return str;
        }
    }

    private aj g() {
        Throwable th;
        AppLovinLogger appLovinLogger;
        String str;
        String str2;
        try {
            ContentResolver contentResolver = this.c.getContentResolver();
            String string = Secure.getString(contentResolver, "advertising_id");
            aj ajVar = new aj();
            if (string == null) {
                string = "";
            }
            ajVar.b = string;
            ajVar.a = Secure.getInt(contentResolver, "limit_ad_tracking") != 0;
            return ajVar;
        } catch (SettingNotFoundException e) {
            th = e;
            appLovinLogger = this.b;
            str = "DataCollector";
            str2 = "Unable to determine if FireOS limited ad tracking is turned on";
            appLovinLogger.e(str, str2, th);
            return null;
        } catch (Throwable th2) {
            th = th2;
            appLovinLogger = this.b;
            str = "DataCollector";
            str2 = "Unable to collect FireOS IDFA";
            appLovinLogger.e(str, str2, th);
            return null;
        }
    }

    private aj h() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null) {
                Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.c});
                if (invoke != null) {
                    Class cls2 = invoke.getClass();
                    Object invoke2 = cls2.getMethod("isLimitAdTrackingEnabled", null).invoke(invoke, null);
                    String str = (String) cls2.getMethod("getId", null).invoke(invoke, null);
                    if (str == null) {
                        str = "";
                    }
                    aj ajVar = new aj();
                    ajVar.a = ((Boolean) invoke2).booleanValue();
                    ajVar.b = str;
                    return ajVar;
                }
            }
        } catch (Throwable e) {
            this.b.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e);
        } catch (Throwable e2) {
            this.b.e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        }
        return new aj();
    }

    private al i() {
        try {
            al alVar = new al();
            Intent registerReceiver = this.c.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int i = -1;
            int intExtra = registerReceiver != null ? registerReceiver.getIntExtra(AppLovinEventTypes.USER_COMPLETED_LEVEL, -1) : -1;
            int intExtra2 = registerReceiver != null ? registerReceiver.getIntExtra("scale", -1) : -1;
            if (intExtra <= 0 || intExtra2 <= 0) {
                alVar.b = -1;
            } else {
                alVar.b = (int) ((((float) intExtra) / ((float) intExtra2)) * 100.0f);
            }
            if (registerReceiver != null) {
                i = registerReceiver.getIntExtra("status", -1);
            }
            alVar.a = i;
            return alVar;
        } catch (Throwable th) {
            this.b.e("DataCollector", "Unable to collect battery info", th);
            return null;
        }
    }

    private double j() {
        double offset = (double) TimeZone.getDefault().getOffset(new Date().getTime());
        Double.isNaN(offset);
        offset = (double) Math.round((offset * 10.0d) / 3600000.0d);
        Double.isNaN(offset);
        return offset / 10.0d;
    }

    private boolean k() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ah.k():boolean. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r2 = this;
        r0 = 0;
        r1 = r2.l();	 Catch:{ Throwable -> 0x0010 }
        if (r1 != 0) goto L_0x000f;	 Catch:{ Throwable -> 0x0010 }
    L_0x0007:
        r1 = r2.m();	 Catch:{ Throwable -> 0x0010 }
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        goto L_0x000f;
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = 1;
    L_0x0010:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ah.k():boolean");
    }

    private boolean l() {
        String str = Build.TAGS;
        return str != null && str.contains(b("lz}$blpz"));
    }

    private boolean m() {
        String[] strArr = new String[]{"&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|"};
        for (int i = 0; i < 9; i++) {
            if (new File(b(strArr[i])).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean n() {
        return a(Build.DEVICE, ea.cr) || a(Build.HARDWARE, ea.cq) || a(Build.MANUFACTURER, ea.cs) || a(Build.MODEL, ea.ct);
    }

    private java.lang.String o() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ah.o():java.lang.String. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = new java.util.concurrent.atomic.AtomicReference;
        r0.<init>();
        r1 = new java.util.concurrent.CountDownLatch;
        r2 = 1;
        r1.<init>(r2);
        r2 = new android.os.Handler;
        r3 = r5.c;
        r3 = r3.getMainLooper();
        r2.<init>(r3);
        r3 = new com.applovin.impl.sdk.ai;
        r3.<init>(r5, r0, r1);
        r2.post(r3);
        r2 = r5.a;	 Catch:{ Throwable -> 0x0031 }
        r3 = com.applovin.impl.sdk.ea.cz;	 Catch:{ Throwable -> 0x0031 }
        r2 = r2.get(r3);	 Catch:{ Throwable -> 0x0031 }
        r2 = (java.lang.Long) r2;	 Catch:{ Throwable -> 0x0031 }
        r2 = r2.longValue();	 Catch:{ Throwable -> 0x0031 }
        r4 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ Throwable -> 0x0031 }
        r1.await(r2, r4);	 Catch:{ Throwable -> 0x0031 }
    L_0x0031:
        r0 = r0.get();
        r0 = (java.lang.String) r0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ah.o():java.lang.String");
    }

    com.applovin.impl.sdk.am a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ah.a():com.applovin.impl.sdk.am. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = r5.d;
        r1 = com.applovin.impl.sdk.am.class;
        r0 = r0.get(r1);
        if (r0 == 0) goto L_0x0011;
    L_0x000a:
        r0 = (com.applovin.impl.sdk.am) r0;
    L_0x000c:
        r0 = r5.a(r0);
        return r0;
    L_0x0011:
        r0 = new com.applovin.impl.sdk.am;
        r0.<init>();
        r1 = java.util.Locale.getDefault();
        r0.k = r1;
        r1 = android.os.Build.MODEL;
        r0.a = r1;
        r1 = android.os.Build.VERSION.RELEASE;
        r0.b = r1;
        r1 = r5.b();
        r0.c = r1;
        r1 = android.os.Build.MANUFACTURER;
        r0.d = r1;
        r1 = android.os.Build.BRAND;
        r0.e = r1;
        r1 = android.os.Build.HARDWARE;
        r0.f = r1;
        r1 = android.os.Build.VERSION.SDK_INT;
        r0.h = r1;
        r1 = android.os.Build.DEVICE;
        r0.g = r1;
        r1 = r5.f();
        r0.l = r1;
        r1 = r5.j();
        r0.o = r1;
        r1 = r5.n();
        r0.u = r1;
        r1 = r5.c;	 Catch:{ Throwable -> 0x0067 }
        r2 = "sensor";	 Catch:{ Throwable -> 0x0067 }
        r1 = r1.getSystemService(r2);	 Catch:{ Throwable -> 0x0067 }
        r1 = (android.hardware.SensorManager) r1;	 Catch:{ Throwable -> 0x0067 }
        r2 = 4;	 Catch:{ Throwable -> 0x0067 }
        r1 = r1.getDefaultSensor(r2);	 Catch:{ Throwable -> 0x0067 }
        if (r1 == 0) goto L_0x0063;	 Catch:{ Throwable -> 0x0067 }
    L_0x0061:
        r1 = 1;	 Catch:{ Throwable -> 0x0067 }
        goto L_0x0064;	 Catch:{ Throwable -> 0x0067 }
    L_0x0063:
        r1 = 0;	 Catch:{ Throwable -> 0x0067 }
    L_0x0064:
        r0.v = r1;	 Catch:{ Throwable -> 0x0067 }
        goto L_0x0071;
    L_0x0067:
        r1 = move-exception;
        r2 = r5.b;
        r3 = "DataCollector";
        r4 = "Unable to retrieve gyroscope availability";
        r2.e(r3, r4, r1);
    L_0x0071:
        r1 = "android.permission.READ_PHONE_STATE";
        r1 = r5.a(r1);
        if (r1 == 0) goto L_0x00a0;
    L_0x0079:
        r1 = r5.c;
        r2 = "phone";
        r1 = r1.getSystemService(r2);
        r1 = (android.telephony.TelephonyManager) r1;
        if (r1 == 0) goto L_0x00a0;
    L_0x0085:
        r2 = r1.getSimCountryIso();
        r3 = java.util.Locale.ENGLISH;
        r2 = r2.toUpperCase(r3);
        r0.i = r2;
        r1 = r1.getNetworkOperatorName();
        r2 = "UTF-8";	 Catch:{ UnsupportedEncodingException -> 0x009e }
        r2 = java.net.URLEncoder.encode(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x009e }
        r0.j = r2;	 Catch:{ UnsupportedEncodingException -> 0x009e }
        goto L_0x00a0;
    L_0x009e:
        r0.j = r1;
    L_0x00a0:
        r1 = r5.c;	 Catch:{ Throwable -> 0x00b2 }
        r1 = r1.getResources();	 Catch:{ Throwable -> 0x00b2 }
        r1 = r1.getDisplayMetrics();	 Catch:{ Throwable -> 0x00b2 }
        r2 = r1.density;	 Catch:{ Throwable -> 0x00b2 }
        r0.m = r2;	 Catch:{ Throwable -> 0x00b2 }
        r1 = r1.densityDpi;	 Catch:{ Throwable -> 0x00b2 }
        r0.n = r1;	 Catch:{ Throwable -> 0x00b2 }
    L_0x00b2:
        r1 = r5.d;
        r2 = com.applovin.impl.sdk.am.class;
        r1.put(r2, r0);
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ah.a():com.applovin.impl.sdk.am");
    }

    boolean a(String str) {
        return a(str, this.c);
    }

    String b() {
        return this.a.isFireOS() ? "fireos" : "android";
    }

    am c() {
        return a(null);
    }

    com.applovin.impl.sdk.ak d() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ah.d():com.applovin.impl.sdk.ak. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r0 = r8.d;
        r1 = com.applovin.impl.sdk.ak.class;
        r0 = r0.get(r1);
        if (r0 == 0) goto L_0x000d;
    L_0x000a:
        r0 = (com.applovin.impl.sdk.ak) r0;
        return r0;
    L_0x000d:
        r0 = r8.c;
        r0 = r0.getApplicationInfo();
        r1 = new java.io.File;
        r2 = r0.sourceDir;
        r1.<init>(r2);
        r1 = r1.lastModified();
        r3 = r8.c;
        r3 = r3.getPackageManager();
        r4 = 0;
        r5 = r8.c;	 Catch:{ NameNotFoundException -> 0x0038 }
        r5 = r5.getPackageName();	 Catch:{ NameNotFoundException -> 0x0038 }
        r6 = 0;	 Catch:{ NameNotFoundException -> 0x0038 }
        r5 = r3.getPackageInfo(r5, r6);	 Catch:{ NameNotFoundException -> 0x0038 }
        r6 = r0.packageName;	 Catch:{ NameNotFoundException -> 0x0039 }
        r6 = r3.getInstallerPackageName(r6);	 Catch:{ NameNotFoundException -> 0x0039 }
        r4 = r6;
        goto L_0x0039;
    L_0x0038:
        r5 = r4;
    L_0x0039:
        r6 = new com.applovin.impl.sdk.ak;
        r6.<init>();
        r7 = r0.packageName;
        r6.c = r7;
        if (r4 == 0) goto L_0x0045;
    L_0x0044:
        goto L_0x0047;
    L_0x0045:
        r4 = "";
    L_0x0047:
        r6.d = r4;
        r6.e = r1;
        r0 = r3.getApplicationLabel(r0);
        r0 = java.lang.String.valueOf(r0);
        r6.a = r0;
        if (r5 == 0) goto L_0x005a;
    L_0x0057:
        r0 = r5.versionName;
        goto L_0x005c;
    L_0x005a:
        r0 = "";
    L_0x005c:
        r6.b = r0;
        r0 = r8.d;
        r1 = com.applovin.impl.sdk.ak.class;
        r0.put(r1, r6);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ah.d():com.applovin.impl.sdk.ak");
    }

    aj e() {
        aj g;
        if (this.a.isFireOS()) {
            g = g();
            if (g == null) {
                if (!((Boolean) this.a.get(ea.ch)).booleanValue()) {
                    g = new aj();
                }
            }
            if (!((Boolean) this.a.get(ea.bL)).booleanValue()) {
                if (!g.a && !((Boolean) this.a.get(ea.bK)).booleanValue()) {
                    g.b = "";
                    return g;
                }
            }
        }
        g = h();
        return !((Boolean) this.a.get(ea.bL)).booleanValue() ? new aj() : !g.a ? g : g;
    }
}
