package defpackage;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.android.exoplayer.hls.HlsChunkSource;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import jp.naver.line.android.BuildConfig;
import org.apache.http.HttpStatus;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: dds */
public abstract class dds {
    private static final byte[] a = new byte[]{(byte) 76, (byte) 96, (byte) 94, (byte) -1, (byte) -33, (byte) 61, (byte) -4, (byte) -95, ClosedCaptionCtrl.BACKSPACE, (byte) 125, (byte) 72, ClosedCaptionCtrl.TAB_OFFSET_CHAN_1, (byte) 64, ClosedCaptionCtrl.RESUME_CAPTION_LOADING, (byte) 86, (byte) -111, Byte.MIN_VALUE, (byte) -36, (byte) 35, (byte) 56, (byte) -91, (byte) 119, (byte) 42, Byte.MIN_VALUE, (byte) -19, (byte) 10, (byte) -86, (byte) 1, PSSSigner.TRAILER_IMPLICIT, (byte) -48, (byte) -96, (byte) -113};
    private ddz b = new ddz();
    private int c = 0;
    private int d = 0;
    private final AtomicBoolean e = new AtomicBoolean(false);
    private String f;
    private ddv g;
    private int h = 0;
    private String i;
    private long j = 0;
    private long k = 0;
    private ExecutorService l;
    private final dea m = new dea();

    public abstract SharedPreferences a();

    protected abstract String b();

    private static javax.net.ssl.SSLSocketFactory n() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:dds.n():javax.net.ssl.SSLSocketFactory. bs: []
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
        r0 = defpackage.cfi.a();	 Catch:{ Exception -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = new java.io.IOException;
        r1 = "failed getSSLSocketFactory.";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dds.n():javax.net.ssl.SSLSocketFactory");
    }

    public static String a(String str) {
        return diw.a(str, a);
    }

    public final void a(ExecutorService executorService) {
        this.l = executorService;
        o();
        if (SystemClock.elapsedRealtime() < BuildConfig.NEW_CONTACT_DURATION_TIME) {
            l();
        }
    }

    public dem b(String str) {
        throw new UnsupportedOperationException("This method must override");
    }

    public dem c(String str) {
        throw new UnsupportedOperationException("This method must override");
    }

    public String c() {
        throw new UnsupportedOperationException("This method must override");
    }

    public String d() {
        throw new UnsupportedOperationException("This method must override");
    }

    protected String a(String str, boolean z, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (z) {
            return dft.e();
        }
        return dft.d();
    }

    public String e() {
        return diu.a();
    }

    protected Map<String, String> f() {
        return new HashMap();
    }

    public final ddv g() {
        return this.g;
    }

    public final ddz h() {
        return this.b;
    }

    public final void a(ddz ddz) {
        this.b = ddz;
    }

    protected void a(JSONObject jSONObject) {
        this.b = new ddz(jSONObject);
    }

    public String i() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String simCountryIso;
            TelephonyManager telephonyManager = (TelephonyManager) dih.a().getSystemService("phone");
            Object obj = null;
            if (telephonyManager != null) {
                simCountryIso = telephonyManager.getSimCountryIso();
                if (simCountryIso != null) {
                    obj = simCountryIso.toUpperCase();
                }
            }
            if (!TextUtils.isEmpty(obj)) {
                stringBuilder.append(obj);
            }
            stringBuilder.append("-");
            stringBuilder.append("-");
            Locale locale = dih.a().getResources().getConfiguration().locale;
            if (locale != null) {
                simCountryIso = locale.getCountry();
                if (simCountryIso != null) {
                    stringBuilder.append(simCountryIso.toUpperCase());
                }
            }
        } catch (Throwable e) {
            ddq b = ddp.b(ddr.Net_Error_UnExpected_Exception.a());
            b.a(ddr.Api_Param_Source_Location.a(), "SIM::getRegions");
            b.a(ddr.Net_Param_Exception.a(), e);
            b.a(ddr.Net_Param_Cause.a(), stringBuilder.toString());
            b.a();
        }
        return stringBuilder.toString();
    }

    public final java.util.List<defpackage.deg> a(boolean r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:dds.a(boolean):java.util.List<deg>. bs: []
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
        r3 = this;
        r0 = r3.g;
        if (r0 == 0) goto L_0x0055;
    L_0x0004:
        r0 = r3.g;	 Catch:{ Exception -> 0x003b }
        r0 = r0.a(r4);	 Catch:{ Exception -> 0x003b }
        if (r0 == 0) goto L_0x0021;	 Catch:{ Exception -> 0x003b }
    L_0x000c:
        r1 = r0.a();	 Catch:{ Exception -> 0x003b }
        if (r1 == 0) goto L_0x0021;	 Catch:{ Exception -> 0x003b }
    L_0x0012:
        r1 = r0.a();	 Catch:{ Exception -> 0x003b }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x003b }
        if (r1 != 0) goto L_0x0021;	 Catch:{ Exception -> 0x003b }
    L_0x001c:
        r0 = r0.a();	 Catch:{ Exception -> 0x003b }
        return r0;	 Catch:{ Exception -> 0x003b }
    L_0x0021:
        r0 = defpackage.ddr.Net_Error_UnExpected_Exception;	 Catch:{ Exception -> 0x003b }
        r0 = r0.a();	 Catch:{ Exception -> 0x003b }
        r0 = defpackage.ddp.b(r0);	 Catch:{ Exception -> 0x003b }
        r1 = defpackage.ddr.Api_Param_Source_Location;	 Catch:{ Exception -> 0x003b }
        r1 = r1.a();	 Catch:{ Exception -> 0x003b }
        r2 = "SIM::getSpdyLegyServer";	 Catch:{ Exception -> 0x003b }
        r0 = r0.a(r1, r2);	 Catch:{ Exception -> 0x003b }
        r0.a();	 Catch:{ Exception -> 0x003b }
        goto L_0x0055;
        r0 = defpackage.ddr.Net_ConnInfo_Use_Default_Spdy;
        r0 = r0.a();
        r0 = defpackage.ddp.a(r0);
        r0.a();
        r0 = defpackage.deg.f();
        if (r4 == 0) goto L_0x0052;
    L_0x004f:
        r4 = r0.f;
        return r4;
    L_0x0052:
        r4 = r0.c;
        return r4;
    L_0x0055:
        r0 = defpackage.deg.f();
        if (r4 == 0) goto L_0x005e;
    L_0x005b:
        r4 = r0.f;
        return r4;
    L_0x005e:
        r4 = r0.c;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: dds.a(boolean):java.util.List<deg>");
    }

    public final void d(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:dds.d(java.lang.String):void. bs: []
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
        r0 = this;
        if (r1 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Exception -> 0x000a }
        r0.h = r1;	 Catch:{ Exception -> 0x000a }
        return;
    L_0x000a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: dds.d(java.lang.String):void");
    }

    public final void j() {
        if (this.g != null) {
            String i = i();
            if (!(this.g == null || this.g.a(this.h, i))) {
                return;
            }
        }
        if (!this.e.get()) {
            this.e.set(true);
            this.l.execute(new Runnable(this) {
                final /* synthetic */ dds a;

                {
                    this.a = r1;
                }

                public final void run() {
                    try {
                        this.a.k();
                    } catch (IOException e) {
                        ddp.b(ddr.Net_ConnInfo_Load_Error.a()).a(ddr.Net_Param_Exception.a(), e.toString()).a();
                    } catch (Throwable th) {
                        this.a.e.set(false);
                    }
                    this.a.e.set(false);
                }
            });
        }
    }

    private void o() {
        SharedPreferences a = a();
        Object string = a.getString("ServerInfo", null);
        String string2 = a.getString("RegionCode", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                this.g = new ddv(jSONObject, string2, a.getLong("Time", jSONObject.optLong("time")));
                a(jSONObject);
                List a2 = this.g.a();
                dej.a();
                Iterator it = a2.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            } catch (JSONException e) {
                ddp.a(ddr.Net_ConnInfo_Parse_Error.a()).a(ddr.Net_Param_Exception.a(), e.toString()).a();
            }
        }
    }

    public final synchronized ddv k() throws IOException {
        String i = i();
        if (TextUtils.isEmpty(this.i)) {
            this.i = a().getString("Host", null);
        }
        boolean z = false;
        if (this.g == null) {
            o();
            if (this.g == null || this.g.a(this.h, i)) {
                z = true;
            } else {
                return this.g;
            }
        } else if (!this.g.a(this.h, i)) {
            return this.g;
        }
        return b(z);
    }

    private ddv b(boolean z) throws IOException {
        String a;
        String i = i();
        try {
            a = a(i, false);
        } catch (Throwable e) {
            if (!((e instanceof ConnectException) || (e instanceof InterruptedIOException) || (e instanceof SocketException))) {
                ddq b = ddp.b(ddr.Net_ConnInfo_Load_Server_Error.a());
                b.a(ddr.Net_Param_Exception.a(), e);
                b.a(ddr.Net_Param_Cause.a(), z ? "empty" : "expired");
                b.a();
            }
            deu e2 = des.a().e();
            dew dew = dew.LoadServerInfo;
            e2.a(e);
            a = null;
        }
        if (TextUtils.isEmpty(a)) {
            a = a(i, true);
        }
        if (!TextUtils.isEmpty(a)) {
            try {
                if (this.d == 200 || this.d == HttpStatus.SC_MOVED_TEMPORARILY) {
                    JSONObject jSONObject = new JSONObject(a);
                    long currentTimeMillis = System.currentTimeMillis();
                    this.g = new ddv(jSONObject, i, currentTimeMillis);
                    a(jSONObject);
                    List a2 = this.g.a();
                    dej.a();
                    Iterator it = a2.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    Editor edit = a().edit();
                    edit.putString("ServerInfo", a);
                    edit.putString("RegionCode", i);
                    edit.putLong("Time", currentTimeMillis);
                    edit.apply();
                    if (TextUtils.isEmpty(this.i)) {
                        a2 = a(true);
                        if (!a2.isEmpty()) {
                            e(((deg) a2.get(0)).d());
                        }
                    }
                } else {
                    this.k = Long.parseLong(new JSONObject(a).optString("expire"));
                }
            } catch (Throwable e3) {
                ddq b2 = ddp.b(ddr.Net_ConnInfo_Parse_Local_Error.a());
                b2.a(ddr.Net_Param_Exception.a(), e3);
                b2.a();
                deu e4 = des.a().e();
                dew dew2 = dew.LoadServerInfo;
                e4.a(e3);
            }
        }
        return this.g;
    }

    private String a(String str, boolean z) throws IOException {
        if (this.d == HttpStatus.SC_SERVICE_UNAVAILABLE) {
            if (this.j + ((long) (this.c * 1000)) > System.currentTimeMillis()) {
                return null;
            }
        } else if (this.d == HttpStatus.SC_NOT_ACCEPTABLE) {
            if (this.j + (this.k * 1000) > System.currentTimeMillis()) {
                return null;
            }
        } else if (this.j + HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS > System.currentTimeMillis()) {
            return null;
        }
        String b = b();
        String e = e();
        String a = a(str, z, this.i);
        String str2 = "Android_OS";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://");
        stringBuilder.append(a);
        stringBuilder.append("/R3?type=");
        stringBuilder.append(str2);
        stringBuilder.append("&version=");
        stringBuilder.append(b);
        stringBuilder.append("&");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append("regions=");
            stringBuilder.append(str);
            stringBuilder.append("&");
        }
        if (!TextUtils.isEmpty(e)) {
            stringBuilder.append("carrier=");
            stringBuilder.append(e);
            stringBuilder.append("&");
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        stringBuilder.append("time=");
        stringBuilder.append(timeInMillis);
        stringBuilder.append("&key=");
        stringBuilder.append(dds.a(str2, b, str, e, timeInMillis));
        str = stringBuilder.toString();
        if (z) {
            str = g(str);
        } else {
            str = f(str);
        }
        this.j = System.currentTimeMillis();
        return str;
    }

    public final void l() {
        Editor edit = a().edit();
        edit.clear();
        edit.apply();
        this.g = null;
        this.j = 0;
    }

    public final void e(String str) {
        if (!str.equals(this.i)) {
            this.i = str;
            Editor edit = a().edit();
            edit.putString("Host", str);
            edit.apply();
        }
    }

    private java.lang.String f(java.lang.String r9) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:dds.f(java.lang.String):java.lang.String. bs: []
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
        r0 = new java.net.URL;
        r1 = defpackage.dih.a();
        r2 = "connectivity";
        r1 = r1.getSystemService(r2);
        r1 = (android.net.ConnectivityManager) r1;
        r2 = 0;
        if (r1 == 0) goto L_0x0026;
    L_0x0011:
        r1 = r1.getActiveNetworkInfo();
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r3 = r1.getType();
        if (r3 == 0) goto L_0x0024;
    L_0x001d:
        r1 = r1.getType();
        r3 = 6;
        if (r1 != r3) goto L_0x0026;
    L_0x0024:
        r1 = 1;
        goto L_0x0027;
    L_0x0026:
        r1 = 0;
    L_0x0027:
        if (r1 != 0) goto L_0x003a;
    L_0x0029:
        r1 = "http://";
        r1 = r9.startsWith(r1);
        if (r1 == 0) goto L_0x003a;
    L_0x0031:
        r1 = "http://";
        r3 = "https://";
        r1 = r9.replace(r1, r3);
        goto L_0x003b;
    L_0x003a:
        r1 = r9;
    L_0x003b:
        r0.<init>(r1);
        r0 = r0.openConnection();
        r0 = (java.net.HttpURLConnection) r0;
        r1 = r0 instanceof javax.net.ssl.HttpsURLConnection;
        if (r1 == 0) goto L_0x0052;
    L_0x0048:
        r1 = r0;
        r1 = (javax.net.ssl.HttpsURLConnection) r1;
        r3 = defpackage.dds.n();
        r1.setSSLSocketFactory(r3);
    L_0x0052:
        r1 = "GET";	 Catch:{ all -> 0x0100 }
        r0.setRequestMethod(r1);	 Catch:{ all -> 0x0100 }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;	 Catch:{ all -> 0x0100 }
        r0.setConnectTimeout(r1);	 Catch:{ all -> 0x0100 }
        r1 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;	 Catch:{ all -> 0x0100 }
        r0.setReadTimeout(r1);	 Catch:{ all -> 0x0100 }
        r1 = r8.f();	 Catch:{ all -> 0x0100 }
        r1 = r1.entrySet();	 Catch:{ all -> 0x0100 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0100 }
    L_0x006e:
        r3 = r1.hasNext();	 Catch:{ all -> 0x0100 }
        if (r3 == 0) goto L_0x008a;	 Catch:{ all -> 0x0100 }
    L_0x0074:
        r3 = r1.next();	 Catch:{ all -> 0x0100 }
        r3 = (java.util.Map.Entry) r3;	 Catch:{ all -> 0x0100 }
        r4 = r3.getKey();	 Catch:{ all -> 0x0100 }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x0100 }
        r3 = r3.getValue();	 Catch:{ all -> 0x0100 }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x0100 }
        r0.setRequestProperty(r4, r3);	 Catch:{ all -> 0x0100 }
        goto L_0x006e;	 Catch:{ all -> 0x0100 }
    L_0x008a:
        r1 = defpackage.dea.a();	 Catch:{ all -> 0x0100 }
        r3 = r8.m;	 Catch:{ all -> 0x0100 }
        r3 = r3.a(r1);	 Catch:{ all -> 0x0100 }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0100 }
        if (r4 != 0) goto L_0x009f;	 Catch:{ all -> 0x0100 }
    L_0x009a:
        r4 = "X-LCS";	 Catch:{ all -> 0x0100 }
        r0.setRequestProperty(r4, r3);	 Catch:{ all -> 0x0100 }
    L_0x009f:
        r0.connect();	 Catch:{ all -> 0x0100 }
        r3 = r0.getResponseCode();	 Catch:{ all -> 0x0100 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ all -> 0x0100 }
        r5 = 0;	 Catch:{ all -> 0x0100 }
        if (r3 == r4) goto L_0x00cc;	 Catch:{ all -> 0x0100 }
    L_0x00ab:
        r4 = 302; // 0x12e float:4.23E-43 double:1.49E-321;	 Catch:{ all -> 0x0100 }
        if (r3 == r4) goto L_0x00cc;	 Catch:{ all -> 0x0100 }
    L_0x00af:
        r4 = 406; // 0x196 float:5.69E-43 double:2.006E-321;	 Catch:{ all -> 0x0100 }
        if (r3 != r4) goto L_0x00b4;	 Catch:{ all -> 0x0100 }
    L_0x00b3:
        goto L_0x00cc;	 Catch:{ all -> 0x0100 }
    L_0x00b4:
        r1 = 503; // 0x1f7 float:7.05E-43 double:2.485E-321;	 Catch:{ all -> 0x0100 }
        if (r3 != r1) goto L_0x00ca;	 Catch:{ all -> 0x0100 }
    L_0x00b8:
        r1 = "Retry-After";	 Catch:{ all -> 0x0100 }
        r1 = r0.getHeaderField(r1);	 Catch:{ all -> 0x0100 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0100 }
        if (r2 != 0) goto L_0x00ca;	 Catch:{ all -> 0x0100 }
    L_0x00c4:
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ all -> 0x0100 }
        r8.c = r1;	 Catch:{ all -> 0x0100 }
    L_0x00ca:
        r2 = r3;	 Catch:{ all -> 0x0100 }
        goto L_0x00ea;	 Catch:{ all -> 0x0100 }
    L_0x00cc:
        r4 = r0.getInputStream();	 Catch:{ all -> 0x0100 }
        r6 = "UTF-8";	 Catch:{ all -> 0x00fb }
        r6 = defpackage.aden.a(r4, r6);	 Catch:{ all -> 0x00fb }
        r4.close();	 Catch:{ all -> 0x0100 }
        r4 = "x-lcm";	 Catch:{ all -> 0x0100 }
        r4 = r0.getHeaderField(r4);	 Catch:{ all -> 0x0100 }
        r7 = r8.m;	 Catch:{ Exception -> 0x00ea }
        r1 = r7.a(r1, r4, r6);	 Catch:{ Exception -> 0x00ea }
        if (r1 != 0) goto L_0x00e8;
    L_0x00e7:
        goto L_0x00ea;
    L_0x00e8:
        r2 = r3;
        r5 = r6;
    L_0x00ea:
        r8.d = r2;	 Catch:{ all -> 0x0100 }
        r1 = defpackage.dil.a();	 Catch:{ all -> 0x0100 }
        r1 = r1.b();	 Catch:{ all -> 0x0100 }
        r1.a(r9);	 Catch:{ all -> 0x0100 }
        r0.disconnect();
        return r5;
    L_0x00fb:
        r9 = move-exception;
        r4.close();	 Catch:{ all -> 0x0100 }
        throw r9;	 Catch:{ all -> 0x0100 }
    L_0x0100:
        r9 = move-exception;
        r0.disconnect();
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: dds.f(java.lang.String):java.lang.String");
    }

    private java.lang.String g(java.lang.String r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:dds.g(java.lang.String):java.lang.String. bs: []
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
        r12 = this;
        r0 = "";
        r10 = defpackage.deb.CONN_INFO;
        r1 = defpackage.adke.d;
        r5 = r1.a();
        r6 = new java.util.HashMap;
        r6.<init>();
        r1 = r12.f();
        r1 = r1.entrySet();
        r1 = r1.iterator();
    L_0x001b:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x0033;
    L_0x0021:
        r2 = r1.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2.getKey();
        r2 = r2.getValue();
        r6.put(r3, r2);
        goto L_0x001b;
    L_0x0033:
        r8 = new dfg;
        r1 = 0;
        r8.<init>(r1, r1);
        r11 = new java.util.concurrent.CountDownLatch;
        r2 = 1;
        r11.<init>(r2);
        r2 = 0;
        r12.f = r2;	 Catch:{ Exception -> 0x006d }
        r4 = new java.net.URL;	 Catch:{ Exception -> 0x006d }
        r4.<init>(r13);	 Catch:{ Exception -> 0x006d }
        r13 = defpackage.des.a();	 Catch:{ Exception -> 0x006d }
        r2 = defpackage.dem.TALK;	 Catch:{ Exception -> 0x006d }
        r7 = new byte[r1];	 Catch:{ Exception -> 0x006d }
        r9 = new dds$2;	 Catch:{ Exception -> 0x006d }
        r9.<init>(r12, r11);	 Catch:{ Exception -> 0x006d }
        r1 = r13;	 Catch:{ Exception -> 0x006d }
        r3 = r10;	 Catch:{ Exception -> 0x006d }
        r1.a(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x006d }
        r1 = r10.b();	 Catch:{ Exception -> 0x006d }
        r13 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ Exception -> 0x006d }
        r11.await(r1, r13);	 Catch:{ Exception -> 0x006d }
        r13 = r12.f;	 Catch:{ Exception -> 0x006d }
        r13 = android.text.TextUtils.isEmpty(r13);	 Catch:{ Exception -> 0x006d }
        if (r13 != 0) goto L_0x006d;	 Catch:{ Exception -> 0x006d }
    L_0x006a:
        r13 = r12.f;	 Catch:{ Exception -> 0x006d }
        r0 = r13;
    L_0x006d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dds.g(java.lang.String):java.lang.String");
    }

    private static String a(String str, String str2, String str3, String str4, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        stringBuilder.append(str3);
        stringBuilder.append(j);
        if (!TextUtils.isEmpty(str4)) {
            stringBuilder.append(str4);
        }
        return diw.a(stringBuilder.toString(), a);
    }

    public final void m() throws IOException {
        l();
        b(false);
    }
}
