package com.facebook.internal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.view.autofill.AutofillManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.af;
import com.facebook.ag;
import com.facebook.n;
import com.facebook.s;
import com.facebook.x;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.apache.cordova.networkinformation.NetworkManager;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class bj {
    private static int a = 0;
    private static long b = -1;
    private static long c = -1;
    private static long d = -1;
    private static String e = "";
    private static String f = "";
    private static String g = "NoCarrier";

    public static <T> boolean a(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static <T> Collection<T> a(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    public static String a(byte[] bArr) {
        return a("SHA-1", bArr);
    }

    private static java.lang.String a(java.lang.String r5, byte[] r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(java.lang.String, byte[]):java.lang.String. bs: []
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
        r5 = java.security.MessageDigest.getInstance(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0034 }
        r5.update(r6);
        r5 = r5.digest();
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r0 = r5.length;
        r1 = 0;
        r2 = 0;
    L_0x0013:
        if (r2 >= r0) goto L_0x002f;
    L_0x0015:
        r3 = r5[r2];
        r4 = r3 >> 4;
        r4 = r4 & 15;
        r4 = java.lang.Integer.toHexString(r4);
        r6.append(r4);
        r3 = r3 >> r1;
        r3 = r3 & 15;
        r3 = java.lang.Integer.toHexString(r3);
        r6.append(r3);
        r2 = r2 + 1;
        goto L_0x0013;
    L_0x002f:
        r5 = r6.toString();
        return r5;
    L_0x0034:
        r5 = 0;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(java.lang.String, byte[]):java.lang.String");
    }

    public static Uri a(String str, String str2, Bundle bundle) {
        Builder builder = new Builder();
        builder.scheme("https");
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str22 : bundle.keySet()) {
                Object obj = bundle.get(str22);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str22, (String) obj);
                }
            }
        }
        return builder.build();
    }

    public static Bundle c(String str) {
        Bundle bundle = new Bundle();
        if (!a(str)) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                } catch (Exception e) {
                    a("FacebookSDK", e);
                }
            }
        }
        return bundle;
    }

    public static void a(Bundle bundle, String str, String str2) {
        if (!a(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static void a(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            a(bundle, str, uri.toString());
        }
    }

    public static boolean a(Bundle bundle, String str, Object obj) {
        if (obj == null) {
            bundle.remove(str);
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) obj);
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) obj);
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            bundle.putLongArray(str, (long[]) obj);
        } else if (obj instanceof String) {
            bundle.putString(str, (String) obj);
        } else if (obj instanceof JSONArray) {
            bundle.putString(str, obj.toString());
        } else if (!(obj instanceof JSONObject)) {
            return false;
        } else {
            bundle.putString(str, obj.toString());
        }
        return true;
    }

    public static void a(java.io.Closeable r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(java.io.Closeable):void. bs: []
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
        if (r0 == 0) goto L_0x0007;
    L_0x0002:
        r0.close();	 Catch:{ IOException -> 0x0006 }
        goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(java.io.Closeable):void");
    }

    public static void a(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static String a(Context context) {
        bn.a((Object) context, "context");
        s.a(context);
        return s.j();
    }

    public static Object a(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object opt = jSONObject.opt(str);
        if (opt != null && (opt instanceof String)) {
            opt = new JSONTokener((String) opt).nextValue();
        }
        if (opt == null || (opt instanceof JSONObject) || (opt instanceof JSONArray)) {
            return opt;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, opt);
            return jSONObject2;
        }
        throw new n("Got an unexpected non-JSON object.");
    }

    public static String a(InputStream inputStream) throws IOException {
        Throwable th;
        Closeable bufferedInputStream;
        Closeable inputStreamReader;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            String stringBuilder2 = stringBuilder.toString();
                            a(bufferedInputStream);
                            a(inputStreamReader);
                            return stringBuilder2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a(bufferedInputStream);
                    a(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                a(bufferedInputStream);
                a(inputStreamReader);
                throw th;
            }
        } catch (Throwable th32) {
            bufferedInputStream = null;
            th = th32;
            inputStreamReader = bufferedInputStream;
            a(bufferedInputStream);
            a(inputStreamReader);
            throw th;
        }
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[8192];
                int i = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    i += read;
                }
                bufferedInputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private static void a(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split : cookie.split(";")) {
                String[] split2 = split.split("=");
                if (split2.length > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(split2[0].trim());
                    stringBuilder.append("=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                    instance.setCookie(str, stringBuilder.toString());
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static void b(Context context) {
        a(context, "facebook.com");
        a(context, ".facebook.com");
        a(context, "https://facebook.com");
        a(context, "https://.facebook.com");
    }

    public static void a(String str, Exception exception) {
        if (s.b() && str != null && exception != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(exception.getClass().getSimpleName());
            stringBuilder.append(": ");
            stringBuilder.append(exception.getMessage());
        }
    }

    public static void a() {
        s.b();
    }

    public static void d(String str) {
        if (s.b()) {
            a(str);
        }
    }

    public static <T> boolean a(T t, T t2) {
        if (t == null) {
            return t2 == null;
        } else {
            return t.equals(t2);
        }
    }

    public static String a(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, "") : "";
    }

    public static JSONObject b(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONObject(str) : null;
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONArray(str) : null;
    }

    public static void a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File a : listFiles) {
                        a(a);
                    }
                }
            }
            file.delete();
        }
    }

    public static <T> List<T> b(T... tArr) {
        List arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            Object obj = tArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static List<String> a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static Set<String> b(JSONArray jSONArray) throws JSONException {
        Set<String> hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }

    public static void a(JSONObject jSONObject, b bVar, String str, boolean z) throws JSONException {
        if (!(bVar == null || bVar.a() == null)) {
            jSONObject.put("attribution", bVar.a());
        }
        if (!(bVar == null || bVar.b() == null)) {
            jSONObject.put("advertiser_id", bVar.b());
            jSONObject.put("advertiser_tracking_enabled", bVar.d() ^ 1);
        }
        if (!(bVar == null || bVar.c() == null)) {
            jSONObject.put("installer_package", bVar.c());
        }
        jSONObject.put("anon_id", str);
        jSONObject.put("application_tracking_enabled", z ^ 1);
    }

    public static void a(org.json.JSONObject r7, android.content.Context r8) throws org.json.JSONException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(org.json.JSONObject, android.content.Context):void. bs: []
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
        r0 = new org.json.JSONArray;
        r0.<init>();
        r1 = "a2";
        r0.put(r1);
        r1 = b;
        r3 = 0;
        r4 = -1;
        r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x0021;
    L_0x0013:
        r1 = java.lang.System.currentTimeMillis();
        r4 = b;
        r1 = r1 - r4;
        r4 = 1800000; // 0x1b7740 float:2.522337E-39 double:8.89318E-318;
        r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r6 < 0) goto L_0x00ac;
    L_0x0021:
        r1 = java.lang.System.currentTimeMillis();
        b = r1;
        r1 = java.util.TimeZone.getDefault();	 Catch:{ Exception -> 0x0040 }
        r2 = new java.util.Date;	 Catch:{ Exception -> 0x0040 }
        r2.<init>();	 Catch:{ Exception -> 0x0040 }
        r2 = r1.inDaylightTime(r2);	 Catch:{ Exception -> 0x0040 }
        r2 = r1.getDisplayName(r2, r3);	 Catch:{ Exception -> 0x0040 }
        e = r2;	 Catch:{ Exception -> 0x0040 }
        r1 = r1.getID();	 Catch:{ Exception -> 0x0040 }
        f = r1;	 Catch:{ Exception -> 0x0040 }
    L_0x0040:
        r1 = g;
        r2 = "NoCarrier";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0058;
    L_0x004a:
        r1 = "phone";	 Catch:{ Exception -> 0x0058 }
        r1 = r8.getSystemService(r1);	 Catch:{ Exception -> 0x0058 }
        r1 = (android.telephony.TelephonyManager) r1;	 Catch:{ Exception -> 0x0058 }
        r1 = r1.getNetworkOperatorName();	 Catch:{ Exception -> 0x0058 }
        g = r1;	 Catch:{ Exception -> 0x0058 }
    L_0x0058:
        r1 = e();	 Catch:{ Exception -> 0x0082 }
        if (r1 == 0) goto L_0x0079;	 Catch:{ Exception -> 0x0082 }
    L_0x005e:
        r1 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0082 }
        r2 = new android.os.StatFs;	 Catch:{ Exception -> 0x0082 }
        r1 = r1.getPath();	 Catch:{ Exception -> 0x0082 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0082 }
        r1 = r2.getBlockCount();	 Catch:{ Exception -> 0x0082 }
        r4 = (long) r1;	 Catch:{ Exception -> 0x0082 }
        r1 = r2.getBlockSize();	 Catch:{ Exception -> 0x0082 }
        r1 = (long) r1;	 Catch:{ Exception -> 0x0082 }
        r4 = r4 * r1;	 Catch:{ Exception -> 0x0082 }
        c = r4;	 Catch:{ Exception -> 0x0082 }
    L_0x0079:
        r1 = c;	 Catch:{ Exception -> 0x0082 }
        r1 = (double) r1;	 Catch:{ Exception -> 0x0082 }
        r1 = a(r1);	 Catch:{ Exception -> 0x0082 }
        c = r1;	 Catch:{ Exception -> 0x0082 }
    L_0x0082:
        r1 = e();	 Catch:{ Exception -> 0x00ac }
        if (r1 == 0) goto L_0x00a3;	 Catch:{ Exception -> 0x00ac }
    L_0x0088:
        r1 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x00ac }
        r2 = new android.os.StatFs;	 Catch:{ Exception -> 0x00ac }
        r1 = r1.getPath();	 Catch:{ Exception -> 0x00ac }
        r2.<init>(r1);	 Catch:{ Exception -> 0x00ac }
        r1 = r2.getAvailableBlocks();	 Catch:{ Exception -> 0x00ac }
        r4 = (long) r1;	 Catch:{ Exception -> 0x00ac }
        r1 = r2.getBlockSize();	 Catch:{ Exception -> 0x00ac }
        r1 = (long) r1;	 Catch:{ Exception -> 0x00ac }
        r4 = r4 * r1;	 Catch:{ Exception -> 0x00ac }
        d = r4;	 Catch:{ Exception -> 0x00ac }
    L_0x00a3:
        r1 = d;	 Catch:{ Exception -> 0x00ac }
        r1 = (double) r1;	 Catch:{ Exception -> 0x00ac }
        r1 = a(r1);	 Catch:{ Exception -> 0x00ac }
        d = r1;	 Catch:{ Exception -> 0x00ac }
    L_0x00ac:
        r1 = r8.getPackageName();
        r2 = -1;
        r4 = "";
        r5 = r8.getPackageManager();	 Catch:{ NameNotFoundException -> 0x00c0 }
        r5 = r5.getPackageInfo(r1, r3);	 Catch:{ NameNotFoundException -> 0x00c0 }
        r6 = r5.versionCode;	 Catch:{ NameNotFoundException -> 0x00c0 }
        r2 = r5.versionName;	 Catch:{ NameNotFoundException -> 0x00c1 }
        goto L_0x00c2;
    L_0x00c0:
        r6 = -1;
    L_0x00c1:
        r2 = r4;
    L_0x00c2:
        r0.put(r1);
        r0.put(r6);
        r0.put(r2);
        r1 = android.os.Build.VERSION.RELEASE;
        r0.put(r1);
        r1 = android.os.Build.MODEL;
        r0.put(r1);
        r1 = r8.getResources();	 Catch:{ Exception -> 0x00e0 }
        r1 = r1.getConfiguration();	 Catch:{ Exception -> 0x00e0 }
        r1 = r1.locale;	 Catch:{ Exception -> 0x00e0 }
        goto L_0x00e4;
    L_0x00e0:
        r1 = java.util.Locale.getDefault();
    L_0x00e4:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r1.getLanguage();
        r2.append(r4);
        r4 = "_";
        r2.append(r4);
        r1 = r1.getCountry();
        r2.append(r1);
        r1 = r2.toString();
        r0.put(r1);
        r1 = e;
        r0.put(r1);
        r1 = g;
        r0.put(r1);
        r1 = 0;
        r4 = "window";	 Catch:{ Exception -> 0x012d }
        r8 = r8.getSystemService(r4);	 Catch:{ Exception -> 0x012d }
        r8 = (android.view.WindowManager) r8;	 Catch:{ Exception -> 0x012d }
        if (r8 == 0) goto L_0x012d;	 Catch:{ Exception -> 0x012d }
    L_0x0119:
        r8 = r8.getDefaultDisplay();	 Catch:{ Exception -> 0x012d }
        r4 = new android.util.DisplayMetrics;	 Catch:{ Exception -> 0x012d }
        r4.<init>();	 Catch:{ Exception -> 0x012d }
        r8.getMetrics(r4);	 Catch:{ Exception -> 0x012d }
        r8 = r4.widthPixels;	 Catch:{ Exception -> 0x012d }
        r5 = r4.heightPixels;	 Catch:{ Exception -> 0x012e }
        r4 = r4.density;	 Catch:{ Exception -> 0x012f }
        r1 = (double) r4;
        goto L_0x012f;
    L_0x012d:
        r8 = 0;
    L_0x012e:
        r5 = 0;
    L_0x012f:
        r0.put(r8);
        r0.put(r5);
        r8 = "%.2f";
        r4 = 1;
        r4 = new java.lang.Object[r4];
        r1 = java.lang.Double.valueOf(r1);
        r4[r3] = r1;
        r8 = java.lang.String.format(r8, r4);
        r0.put(r8);
        r8 = d();
        r0.put(r8);
        r1 = c;
        r0.put(r1);
        r1 = d;
        r0.put(r1);
        r8 = f;
        r0.put(r8);
        r8 = "extinfo";
        r0 = r0.toString();
        r7.put(r8, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(org.json.JSONObject, android.content.Context):void");
    }

    public static java.lang.reflect.Method a(java.lang.Class<?> r0, java.lang.String r1, java.lang.Class<?>... r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method. bs: []
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
        r0 = r0.getMethod(r1, r2);	 Catch:{ NoSuchMethodException -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static java.lang.reflect.Method a(java.lang.String r0, java.lang.String r1, java.lang.Class<?>... r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(java.lang.String, java.lang.String, java.lang.Class[]):java.lang.reflect.Method. bs: []
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
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0009 }
        r0 = a(r0, r1, r2);	 Catch:{ ClassNotFoundException -> 0x0009 }
        return r0;
    L_0x0009:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(java.lang.String, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static java.lang.Object a(java.lang.Object r1, java.lang.reflect.Method r2, java.lang.Object... r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object. bs: []
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
        r0 = 0;
        r1 = r2.invoke(r1, r3);	 Catch:{ IllegalAccessException -> 0x0007, InvocationTargetException -> 0x0006 }
        return r1;
    L_0x0006:
        return r0;
    L_0x0007:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }

    public static String c(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return NetworkManager.TYPE_UNKNOWN;
        }
        return context.getClass().getSimpleName();
    }

    public static <T, K> List<K> a(List<T> list, bl<T, K> blVar) {
        if (list == null) {
            return null;
        }
        List<K> arrayList = new ArrayList();
        for (T a : list) {
            Object a2 = blVar.a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public static String a(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    public static boolean b(Uri uri) {
        return uri != null && (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()) || "fbstaging".equalsIgnoreCase(uri.getScheme()));
    }

    public static boolean c(Uri uri) {
        return uri != null && AppLovinEventTypes.USER_VIEWED_CONTENT.equalsIgnoreCase(uri.getScheme());
    }

    public static boolean d(Uri uri) {
        return uri != null && "file".equalsIgnoreCase(uri.getScheme());
    }

    public static long e(Uri uri) {
        Throwable th;
        Cursor query;
        try {
            query = s.f().getContentResolver().query(uri, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("_size");
                query.moveToFirst();
                long j = query.getLong(columnIndex);
                if (query != null) {
                    query.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static java.util.Date a(android.os.Bundle r5, java.lang.String r6, java.util.Date r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.a(android.os.Bundle, java.lang.String, java.util.Date):java.util.Date. bs: []
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
        r0 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r5 = r5.get(r6);
        r6 = r5 instanceof java.lang.Long;
        if (r6 == 0) goto L_0x0013;
    L_0x000c:
        r5 = (java.lang.Long) r5;
        r5 = r5.longValue();
        goto L_0x001d;
    L_0x0013:
        r6 = r5 instanceof java.lang.String;
        if (r6 == 0) goto L_0x003e;
    L_0x0017:
        r5 = (java.lang.String) r5;	 Catch:{ NumberFormatException -> 0x003d }
        r5 = java.lang.Long.parseLong(r5);	 Catch:{ NumberFormatException -> 0x003d }
    L_0x001d:
        r0 = 0;
        r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x002e;
    L_0x0023:
        r5 = new java.util.Date;
        r6 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r5.<init>(r6);
        return r5;
    L_0x002e:
        r0 = new java.util.Date;
        r1 = r7.getTime();
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r3;
        r1 = r1 + r5;
        r0.<init>(r1);
        return r0;
    L_0x003d:
        return r0;
    L_0x003e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.a(android.os.Bundle, java.lang.String, java.util.Date):java.util.Date");
    }

    public static void a(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static Map<String, String> a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static boolean a(AccessToken accessToken) {
        return accessToken != null && accessToken.equals(AccessToken.a());
    }

    public static void a(final String str, final bk bkVar) {
        JSONObject a = bf.a(str);
        if (a != null) {
            bkVar.a(a);
            return;
        }
        x anonymousClass1 = new x() {
            public final void a(af afVar) {
                if (afVar.a() != null) {
                    bkVar.a(afVar.a().g());
                    return;
                }
                bf.a(str, afVar.b());
                bkVar.a(afVar.b());
            }
        };
        GraphRequest f = f(str);
        f.a(anonymousClass1);
        f.g();
    }

    public static JSONObject e(String str) {
        JSONObject a = bf.a(str);
        if (a != null) {
            return a;
        }
        af a2 = GraphRequest.a(f(str));
        if (a2.a() != null) {
            return null;
        }
        return a2.b();
    }

    private static GraphRequest f(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", str);
        return new GraphRequest(null, "me", bundle, ag.GET, null);
    }

    private static int d() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.d():int. bs: []
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
        r0 = a;
        if (r0 <= 0) goto L_0x0007;
    L_0x0004:
        r0 = a;
        return r0;
    L_0x0007:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x001c }
        r1 = "/sys/devices/system/cpu/";	 Catch:{ Exception -> 0x001c }
        r0.<init>(r1);	 Catch:{ Exception -> 0x001c }
        r1 = new com.facebook.internal.bj$2;	 Catch:{ Exception -> 0x001c }
        r1.<init>();	 Catch:{ Exception -> 0x001c }
        r0 = r0.listFiles(r1);	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x001c }
    L_0x0019:
        r0 = r0.length;	 Catch:{ Exception -> 0x001c }
        a = r0;	 Catch:{ Exception -> 0x001c }
    L_0x001c:
        r0 = a;
        if (r0 > 0) goto L_0x002f;
    L_0x0020:
        r0 = java.lang.Runtime.getRuntime();
        r0 = r0.availableProcessors();
        r1 = 1;
        r0 = java.lang.Math.max(r0, r1);
        a = r0;
    L_0x002f:
        r0 = a;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.d():int");
    }

    private static boolean e() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static long a(double d) {
        return Math.round(d / 1.073741824E9d);
    }

    public static bm a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject("permissions").getJSONArray("data");
        List arrayList = new ArrayList(jSONArray.length());
        List arrayList2 = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("permission");
            if (!(optString == null || optString.equals("installed"))) {
                String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        arrayList.add(optString);
                    } else if (optString2.equals("declined")) {
                        arrayList2.add(optString);
                    }
                }
            }
        }
        return new bm(arrayList, arrayList2);
    }

    public static String b() {
        return new BigInteger(100, new Random()).toString(32);
    }

    public static boolean d(Context context) {
        return e(context);
    }

    public static boolean e(Context context) {
        if (VERSION.SDK_INT < 26) {
            return false;
        }
        AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
        if (autofillManager != null && autofillManager.isAutofillSupported() && autofillManager.isEnabled()) {
            return true;
        }
        return false;
    }

    public static boolean f(Context context) {
        if (VERSION.SDK_INT >= 27) {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.pc");
        }
        return Build.DEVICE != null && Build.DEVICE.matches(".+_cheets|cheets_.+");
    }

    public static java.util.Locale c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bj.c():java.util.Locale. bs: []
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
        r0 = com.facebook.s.f();	 Catch:{ Exception -> 0x000f }
        r0 = r0.getResources();	 Catch:{ Exception -> 0x000f }
        r0 = r0.getConfiguration();	 Catch:{ Exception -> 0x000f }
        r0 = r0.locale;	 Catch:{ Exception -> 0x000f }
        goto L_0x0013;
    L_0x000f:
        r0 = java.util.Locale.getDefault();
    L_0x0013:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bj.c():java.util.Locale");
    }

    public static String b(String str) {
        return a("MD5", str.getBytes());
    }
}
