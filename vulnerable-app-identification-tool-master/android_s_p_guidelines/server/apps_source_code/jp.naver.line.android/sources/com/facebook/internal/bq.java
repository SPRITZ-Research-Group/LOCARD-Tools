package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.m;

final class bq extends WebViewClient {
    final /* synthetic */ bo a;

    private bq(bo boVar) {
        this.a = boVar;
    }

    /* synthetic */ bq(bo boVar, byte b) {
        this(boVar);
    }

    public final boolean shouldOverrideUrlLoading(android.webkit.WebView r6, java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bq.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean. bs: []
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
        r6 = "Redirect URL: ";
        r0 = java.lang.String.valueOf(r7);
        r6.concat(r0);
        com.facebook.internal.bj.a();
        r6 = r5.a;
        r6 = r6.c;
        r6 = r7.startsWith(r6);
        r0 = 1;
        if (r6 == 0) goto L_0x009c;
    L_0x0019:
        r6 = r5.a;
        r6 = r6.a(r7);
        r7 = "error";
        r7 = r6.getString(r7);
        if (r7 != 0) goto L_0x002d;
    L_0x0027:
        r7 = "error_type";
        r7 = r6.getString(r7);
    L_0x002d:
        r1 = "error_msg";
        r1 = r6.getString(r1);
        if (r1 != 0) goto L_0x003b;
    L_0x0035:
        r1 = "error_message";
        r1 = r6.getString(r1);
    L_0x003b:
        if (r1 != 0) goto L_0x0043;
    L_0x003d:
        r1 = "error_description";
        r1 = r6.getString(r1);
    L_0x0043:
        r2 = "error_code";
        r2 = r6.getString(r2);
        r3 = com.facebook.internal.bj.a(r2);
        r4 = -1;
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x0055 }
        goto L_0x0056;
    L_0x0055:
        r2 = -1;
    L_0x0056:
        r3 = com.facebook.internal.bj.a(r7);
        if (r3 == 0) goto L_0x006a;
    L_0x005c:
        r3 = com.facebook.internal.bj.a(r1);
        if (r3 == 0) goto L_0x006a;
    L_0x0062:
        if (r2 != r4) goto L_0x006a;
    L_0x0064:
        r7 = r5.a;
        r7.a(r6);
        goto L_0x009b;
    L_0x006a:
        if (r7 == 0) goto L_0x0082;
    L_0x006c:
        r6 = "access_denied";
        r6 = r7.equals(r6);
        if (r6 != 0) goto L_0x007c;
    L_0x0074:
        r6 = "OAuthAccessDeniedException";
        r6 = r7.equals(r6);
        if (r6 == 0) goto L_0x0082;
    L_0x007c:
        r6 = r5.a;
        r6.cancel();
        goto L_0x009b;
    L_0x0082:
        r6 = 4201; // 0x1069 float:5.887E-42 double:2.0756E-320;
        if (r2 != r6) goto L_0x008c;
    L_0x0086:
        r6 = r5.a;
        r6.cancel();
        goto L_0x009b;
    L_0x008c:
        r6 = new com.facebook.FacebookRequestError;
        r6.<init>(r2, r7, r1);
        r7 = r5.a;
        r2 = new com.facebook.v;
        r2.<init>(r6, r1);
        r7.a(r2);
    L_0x009b:
        return r0;
    L_0x009c:
        r6 = "fbconnect://cancel";
        r6 = r7.startsWith(r6);
        if (r6 == 0) goto L_0x00aa;
    L_0x00a4:
        r6 = r5.a;
        r6.cancel();
        return r0;
    L_0x00aa:
        r6 = "touch";
        r6 = r7.contains(r6);
        r1 = 0;
        if (r6 == 0) goto L_0x00b4;
    L_0x00b3:
        return r1;
    L_0x00b4:
        r6 = r5.a;	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r6 = r6.getContext();	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r2 = new android.content.Intent;	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r3 = "android.intent.action.VIEW";	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r7 = android.net.Uri.parse(r7);	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r2.<init>(r3, r7);	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        r6.startActivity(r2);	 Catch:{ ActivityNotFoundException -> 0x00c9 }
        return r0;
    L_0x00c9:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bq.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.a.a(new m(str, i, str2));
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.cancel();
        this.a.a(new m(null, -11, null));
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        "Webview loading URL: ".concat(String.valueOf(str));
        bj.a();
        super.onPageStarted(webView, str, bitmap);
        if (!this.a.k) {
            this.a.f.show();
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (!this.a.k) {
            this.a.f.dismiss();
        }
        this.a.h.setBackgroundColor(0);
        this.a.e.setVisibility(0);
        this.a.g.setVisibility(0);
        this.a.l = true;
    }
}
