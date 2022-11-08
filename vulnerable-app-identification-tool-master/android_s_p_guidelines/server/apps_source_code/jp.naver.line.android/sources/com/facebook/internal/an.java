package com.facebook.internal;

import android.net.Uri;
import com.facebook.ai;
import java.io.IOException;
import java.io.InputStream;

class an {
    static final String a = "an";
    private static v b;

    an() {
    }

    private static synchronized v a() throws IOException {
        v vVar;
        synchronized (an.class) {
            if (b == null) {
                b = new v(a, new z());
            }
            vVar = b;
        }
        return vVar;
    }

    static InputStream a(Uri uri) {
        if (uri != null && b(uri)) {
            try {
                return a().a(uri.toString(), null);
            } catch (IOException e) {
                ar.a(ai.CACHE, 5, a, e.toString());
            }
        }
        return null;
    }

    static java.io.InputStream a(java.net.HttpURLConnection r5) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.an.a(java.net.HttpURLConnection):java.io.InputStream. bs: []
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
        r0 = r5.getResponseCode();
        r1 = 0;
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r2) goto L_0x0038;
    L_0x0009:
        r0 = r5.getURL();
        r0 = r0.toString();
        r0 = android.net.Uri.parse(r0);
        r2 = r5.getInputStream();
        r3 = b(r0);	 Catch:{ IOException -> 0x0037 }
        if (r3 == 0) goto L_0x0037;	 Catch:{ IOException -> 0x0037 }
    L_0x001f:
        r3 = a();	 Catch:{ IOException -> 0x0037 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x0037 }
        r4 = new com.facebook.internal.ao;	 Catch:{ IOException -> 0x0037 }
        r4.<init>(r2, r5);	 Catch:{ IOException -> 0x0037 }
        r5 = r3.b(r0, r1);	 Catch:{ IOException -> 0x0037 }
        r0 = new com.facebook.internal.y;	 Catch:{ IOException -> 0x0037 }
        r0.<init>(r4, r5);	 Catch:{ IOException -> 0x0037 }
        r1 = r0;
        goto L_0x0038;
    L_0x0037:
        r1 = r2;
    L_0x0038:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.an.a(java.net.HttpURLConnection):java.io.InputStream");
    }

    private static boolean b(Uri uri) {
        if (uri != null) {
            String host = uri.getHost();
            if (host.endsWith("fbcdn.net")) {
                return true;
            }
            if (host.startsWith("fbcdn") && host.endsWith("akamaihd.net")) {
                return true;
            }
        }
        return false;
    }
}
