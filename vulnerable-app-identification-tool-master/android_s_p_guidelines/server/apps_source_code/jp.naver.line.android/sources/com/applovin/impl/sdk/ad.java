package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

class ad {
    private static final Object a = new JSONObject();
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;

    ad(AppLovinSdkImpl appLovinSdkImpl) {
        this.b = appLovinSdkImpl;
        this.c = appLovinSdkImpl.getLogger();
    }

    private int a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return AppLovinErrorCodes.FETCH_AD_TIMEOUT;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : -1;
        } else {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : HttpStatus.SC_UNAUTHORIZED;
        }
    }

    private HttpURLConnection a(String str, String str2, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.b.get(ea.w)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.b.get(ea.y)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(java.io.InputStream r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ad.a(java.io.InputStream):void. bs: []
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
        if (r0 == 0) goto L_0x0006;
    L_0x0002:
        r0.close();	 Catch:{ Exception -> 0x0006 }
    L_0x0006:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.a(java.io.InputStream):void");
    }

    private <T> void a(String str, int i, String str2, String str3, T t, af<T> afVar) throws JSONException {
        Object str4;
        Throwable e;
        AppLovinLogger appLovinLogger;
        String str5;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" received from from \"");
        stringBuilder.append(str3);
        stringBuilder.append("\": ");
        stringBuilder.append(str4);
        this.c.d("ConnectionManager", stringBuilder.toString());
        if (i < 200 || i >= HttpStatus.SC_MULTIPLE_CHOICES) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(i);
            stringBuilder2.append(" error received from \"");
            stringBuilder2.append(str3);
            stringBuilder2.append("\"");
            this.c.e("ConnectionManager", stringBuilder2.toString());
            afVar.a(i);
            return;
        }
        Object obj = (str4 == null || str4.length() <= 2) ? null : 1;
        if (!(i == 204 || obj == null)) {
            try {
                if (!(t instanceof String)) {
                    if (t instanceof gf) {
                        str4 = gg.a(str4, this.b);
                    } else if (t instanceof JSONObject) {
                        str4 = new JSONObject(str4);
                    } else {
                        StringBuilder stringBuilder3 = new StringBuilder("Unable to handle '");
                        stringBuilder3.append(t.getClass().getName());
                        stringBuilder3.append("'");
                        this.c.e("ConnectionManager", stringBuilder3.toString());
                    }
                }
            } catch (JSONException e2) {
                e = e2;
                appLovinLogger = this.c;
                str5 = "ConnectionManager";
                stringBuilder = new StringBuilder("Invalid JSON returned from \"");
                stringBuilder.append(str3);
                stringBuilder.append("\"");
                appLovinLogger.e(str5, stringBuilder.toString(), e);
                str4 = t;
                afVar.a(str4, i);
            } catch (SAXException e3) {
                e = e3;
                appLovinLogger = this.c;
                str5 = "ConnectionManager";
                stringBuilder = new StringBuilder("Invalid XML returned from \"");
                stringBuilder.append(str3);
                stringBuilder.append("\"");
                appLovinLogger.e(str5, stringBuilder.toString(), e);
                str4 = t;
                afVar.a(str4, i);
            }
            afVar.a(str4, i);
        }
        str4 = t;
        afVar.a(str4, i);
    }

    private void a(String str, String str2, int i, long j) {
        StringBuilder stringBuilder = new StringBuilder("Successful ");
        stringBuilder.append(str);
        stringBuilder.append(" returned ");
        stringBuilder.append(i);
        stringBuilder.append(" in ");
        stringBuilder.append(((float) (System.currentTimeMillis() - j)) / 1000.0f);
        stringBuilder.append(" s over ");
        stringBuilder.append(ag.a(this.b));
        stringBuilder.append(" to \"");
        stringBuilder.append(str2);
        stringBuilder.append("\"");
        this.c.i("ConnectionManager", stringBuilder.toString());
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder("Failed ");
        stringBuilder.append(str);
        stringBuilder.append(" returned ");
        stringBuilder.append(i);
        stringBuilder.append(" in ");
        stringBuilder.append(((float) (System.currentTimeMillis() - j)) / 1000.0f);
        stringBuilder.append(" s over ");
        stringBuilder.append(ag.a(this.b));
        stringBuilder.append(" to \"");
        stringBuilder.append(str2);
        stringBuilder.append("\"");
        this.c.e("ConnectionManager", stringBuilder.toString(), th);
    }

    private static void a(java.net.HttpURLConnection r0) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ad.a(java.net.HttpURLConnection):void. bs: []
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
        if (r0 == 0) goto L_0x0006;
    L_0x0002:
        r0.disconnect();	 Catch:{ Exception -> 0x0006 }
    L_0x0006:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.a(java.net.HttpURLConnection):void");
    }

    void a(String str, af<String> afVar) {
        a(str, "GET", -1, null, "", true, afVar);
    }

    <T> void a(java.lang.String r19, java.lang.String r20, int r21, org.json.JSONObject r22, T r23, boolean r24, com.applovin.impl.sdk.ae r25, com.applovin.impl.sdk.af<T> r26) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ad.a(java.lang.String, java.lang.String, int, org.json.JSONObject, java.lang.Object, boolean, com.applovin.impl.sdk.ae, com.applovin.impl.sdk.af):void. bs: []
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
        r18 = this;
        r8 = r18;
        r0 = r19;
        r9 = r20;
        r10 = r23;
        r7 = r25;
        r11 = r26;
        if (r0 == 0) goto L_0x01c6;
    L_0x000e:
        if (r9 == 0) goto L_0x01be;
    L_0x0010:
        if (r11 == 0) goto L_0x01b6;
    L_0x0012:
        r1 = r19.toLowerCase();
        r2 = "http";
        r1 = r1.startsWith(r2);
        if (r1 != 0) goto L_0x003e;
    L_0x001e:
        r1 = r8.c;
        r2 = "ConnectionManager";
        r3 = new java.lang.StringBuilder;
        r4 = "Requested postback submission to non HTTP endpoint ";
        r3.<init>(r4);
        r3.append(r0);
        r0 = "; skipping...";
        r3.append(r0);
        r0 = r3.toString();
        r1.userError(r2, r0);
        r0 = -900; // 0xfffffffffffffc7c float:NaN double:NaN;
        r11.a(r0);
        return;
    L_0x003e:
        r1 = r8.b;
        r2 = com.applovin.impl.sdk.ea.bS;
        r1 = r1.get(r2);
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x006b;
    L_0x004e:
        r1 = "https://";
        r1 = r0.contains(r1);
        if (r1 != 0) goto L_0x006b;
    L_0x0056:
        r1 = r8.b;
        r1 = r1.getLogger();
        r2 = "ConnectionManager";
        r3 = "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...";
        r1.w(r2, r3);
        r1 = "http://";
        r2 = "https://";
        r0 = r0.replace(r1, r2);
    L_0x006b:
        r12 = r0;
        r13 = java.lang.System.currentTimeMillis();
        r15 = 0;
        r0 = r8.c;	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r1 = "ConnectionManager";	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r3 = "Sending ";	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2.append(r9);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r3 = " request to \"";	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2.append(r3);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2.append(r12);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r3 = "\"...";	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2.append(r3);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r0.i(r1, r2);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r0 = r21;	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        r5 = r8.a(r12, r9, r0);	 Catch:{ Throwable -> 0x0190, all -> 0x018d }
        if (r22 == 0) goto L_0x00f5;
    L_0x009b:
        r0 = r22.toString();	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = r8.c;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r2 = "ConnectionManager";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r4 = "Request to \"";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3.append(r12);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r4 = "\" is ";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3.append(r4);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3.append(r0);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1.d(r2, r3);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = "Content-Type";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r2 = "application/json; charset=utf-8";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r5.setRequestProperty(r1, r2);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = 1;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r5.setDoOutput(r1);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = "UTF-8";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = java.nio.charset.Charset.forName(r1);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = r0.getBytes(r1);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = r1.length;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r5.setFixedLengthStreamingMode(r1);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1 = new java.io.PrintWriter;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r3 = r5.getOutputStream();	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r4 = "UTF8";	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1.print(r0);	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        r1.close();	 Catch:{ Throwable -> 0x00ef, all -> 0x00ec }
        goto L_0x00f5;
    L_0x00ec:
        r0 = move-exception;
        goto L_0x01af;
    L_0x00ef:
        r0 = move-exception;
        r7 = r0;
        r17 = r5;
        goto L_0x0194;
    L_0x00f5:
        r0 = r5.getResponseCode();	 Catch:{ MalformedURLException -> 0x0177, Throwable -> 0x0172, all -> 0x016e }
        r16 = r5.getContentType();	 Catch:{ MalformedURLException -> 0x0177, Throwable -> 0x0172, all -> 0x016e }
        if (r0 <= 0) goto L_0x015d;
    L_0x00ff:
        r1 = r18;
        r2 = r20;
        r3 = r12;
        r4 = r0;
        r17 = r5;
        r5 = r13;
        r1.a(r2, r3, r4, r5);	 Catch:{ MalformedURLException -> 0x0179 }
        r6 = r17.getInputStream();	 Catch:{ MalformedURLException -> 0x0179 }
        if (r24 == 0) goto L_0x014d;
    L_0x0111:
        r0 = r8.b;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0148, all -> 0x0144 }
        r2 = com.applovin.impl.sdk.ag.a(r6, r0);	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0148, all -> 0x0144 }
        if (r7 == 0) goto L_0x0132;
    L_0x0119:
        if (r2 == 0) goto L_0x0129;
    L_0x011b:
        r0 = r2.length();	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r0 = (long) r0;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r7.b(r0);	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        goto L_0x0129;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
    L_0x0124:
        r0 = move-exception;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r7 = r0;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r15 = r6;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        goto L_0x0194;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
    L_0x0129:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r3 = 0;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r0 = r0 - r13;	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
        r7.a(r0);	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0124, all -> 0x0144 }
    L_0x0132:
        r3 = r17.getResponseCode();	 Catch:{ MalformedURLException -> 0x014b, Throwable -> 0x0148, all -> 0x0144 }
        r1 = r18;
        r4 = r16;
        r5 = r12;
        r15 = r6;
        r6 = r23;
        r7 = r26;
        r1.a(r2, r3, r4, r5, r6, r7);	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x0186;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x0144:
        r0 = move-exception;	 Catch:{ MalformedURLException -> 0x0179 }
        r15 = r6;	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x01ad;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x0148:
        r0 = move-exception;	 Catch:{ MalformedURLException -> 0x0179 }
        r15 = r6;	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x0175;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x014b:
        r15 = r6;	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x0179;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x014d:
        r15 = r6;	 Catch:{ MalformedURLException -> 0x0179 }
        if (r7 == 0) goto L_0x0159;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x0150:
        r1 = java.lang.System.currentTimeMillis();	 Catch:{ MalformedURLException -> 0x0179 }
        r3 = 0;	 Catch:{ MalformedURLException -> 0x0179 }
        r1 = r1 - r13;	 Catch:{ MalformedURLException -> 0x0179 }
        r7.a(r1);	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x0159:
        r11.a(r10, r0);	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x0186;	 Catch:{ MalformedURLException -> 0x0179 }
    L_0x015d:
        r17 = r5;	 Catch:{ MalformedURLException -> 0x0179 }
        r7 = 0;	 Catch:{ MalformedURLException -> 0x0179 }
        r1 = r18;	 Catch:{ MalformedURLException -> 0x0179 }
        r2 = r20;	 Catch:{ MalformedURLException -> 0x0179 }
        r3 = r12;	 Catch:{ MalformedURLException -> 0x0179 }
        r4 = r0;	 Catch:{ MalformedURLException -> 0x0179 }
        r5 = r13;	 Catch:{ MalformedURLException -> 0x0179 }
        r1.a(r2, r3, r4, r5, r7);	 Catch:{ MalformedURLException -> 0x0179 }
        r11.a(r0);	 Catch:{ MalformedURLException -> 0x0179 }
        goto L_0x0186;
    L_0x016e:
        r0 = move-exception;
        r17 = r5;
        goto L_0x01af;
    L_0x0172:
        r0 = move-exception;
        r17 = r5;
    L_0x0175:
        r7 = r0;
        goto L_0x0194;
    L_0x0177:
        r17 = r5;
    L_0x0179:
        r0 = -901; // 0xfffffffffffffc7b float:NaN double:NaN;
        if (r24 == 0) goto L_0x0183;
    L_0x017d:
        r11.a(r0);	 Catch:{ Throwable -> 0x0181 }
        goto L_0x0186;	 Catch:{ Throwable -> 0x0181 }
    L_0x0181:
        r0 = move-exception;	 Catch:{ Throwable -> 0x0181 }
        goto L_0x0175;	 Catch:{ Throwable -> 0x0181 }
    L_0x0183:
        r11.a(r10, r0);	 Catch:{ Throwable -> 0x0181 }
    L_0x0186:
        a(r15);
        a(r17);
        return;
    L_0x018d:
        r0 = move-exception;
        r5 = r15;
        goto L_0x01af;
    L_0x0190:
        r0 = move-exception;
        r7 = r0;
        r17 = r15;
    L_0x0194:
        r0 = r8.a(r7);	 Catch:{ all -> 0x01ac }
        r1 = r18;	 Catch:{ all -> 0x01ac }
        r2 = r20;	 Catch:{ all -> 0x01ac }
        r3 = r12;	 Catch:{ all -> 0x01ac }
        r4 = r0;	 Catch:{ all -> 0x01ac }
        r5 = r13;	 Catch:{ all -> 0x01ac }
        r1.a(r2, r3, r4, r5, r7);	 Catch:{ all -> 0x01ac }
        r11.a(r0);	 Catch:{ all -> 0x01ac }
        a(r15);
        a(r17);
        return;
    L_0x01ac:
        r0 = move-exception;
    L_0x01ad:
        r5 = r17;
    L_0x01af:
        a(r15);
        a(r5);
        throw r0;
    L_0x01b6:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "No callback specified";
        r0.<init>(r1);
        throw r0;
    L_0x01be:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "No method specified";
        r0.<init>(r1);
        throw r0;
    L_0x01c6:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "No endpoint specified";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.a(java.lang.String, java.lang.String, int, org.json.JSONObject, java.lang.Object, boolean, com.applovin.impl.sdk.ae, com.applovin.impl.sdk.af):void");
    }

    <T> void a(String str, String str2, int i, JSONObject jSONObject, T t, boolean z, af<T> afVar) {
        a(str, str2, i, jSONObject, t, z, null, afVar);
    }
}
