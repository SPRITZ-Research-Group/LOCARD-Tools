package com.facebook.internal;

import android.os.AsyncTask;
import android.os.Bundle;
import com.facebook.n;
import com.facebook.s;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONArray;

final class bs extends AsyncTask<Void, Void, String[]> {
    final /* synthetic */ bo a;
    private String b;
    private Bundle c;
    private Exception[] d;

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        String[] strArr = (String[]) obj;
        this.a.f.dismiss();
        for (Throwable th : this.d) {
            if (th != null) {
                this.a.a(th);
                return;
            }
        }
        if (strArr == null) {
            this.a.a(new n("Failed to stage photos for web dialog"));
            return;
        }
        Collection asList = Arrays.asList(strArr);
        if (asList.contains(null)) {
            this.a.a(new n("Failed to stage photos for web dialog"));
            return;
        }
        bj.a(this.c, "media", new JSONArray(asList));
        String a = bg.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.g());
        stringBuilder.append("/dialog/");
        stringBuilder.append(this.b);
        this.a.b = bj.a(a, stringBuilder.toString(), this.c).toString();
        this.a.a((this.a.g.getDrawable().getIntrinsicWidth() / 2) + 1);
    }

    bs(bo boVar, String str, Bundle bundle) {
        this.a = boVar;
        this.b = str;
        this.c = bundle;
    }

    private java.lang.String[] a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r14_0 com.facebook.GraphRequest) in PHI: PHI: (r14_2 com.facebook.GraphRequest) = (r14_0 com.facebook.GraphRequest), (r14_1 com.facebook.GraphRequest) binds: {(r14_0 com.facebook.GraphRequest)=B:17:0x0062, (r14_1 com.facebook.GraphRequest)=B:20:0x0094}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r15 = this;
        r0 = r15.c;
        r1 = "media";
        r0 = r0.getStringArray(r1);
        r1 = r0.length;
        r1 = new java.lang.String[r1];
        r2 = r0.length;
        r2 = new java.lang.Exception[r2];
        r15.d = r2;
        r2 = new java.util.concurrent.CountDownLatch;
        r3 = r0.length;
        r2.<init>(r3);
        r3 = new java.util.concurrent.ConcurrentLinkedQueue;
        r3.<init>();
        r10 = com.facebook.AccessToken.a();
        r4 = 0;
        r11 = 0;
    L_0x0021:
        r12 = 0;
        r13 = 1;
        r4 = r0.length;	 Catch:{ Exception -> 0x00c7 }
        if (r11 >= r4) goto L_0x00c3;	 Catch:{ Exception -> 0x00c7 }
    L_0x0026:
        r4 = r15.isCancelled();	 Catch:{ Exception -> 0x00c7 }
        if (r4 == 0) goto L_0x0041;	 Catch:{ Exception -> 0x00c7 }
    L_0x002c:
        r0 = r3.iterator();	 Catch:{ Exception -> 0x00c7 }
    L_0x0030:
        r1 = r0.hasNext();	 Catch:{ Exception -> 0x00c7 }
        if (r1 == 0) goto L_0x0040;	 Catch:{ Exception -> 0x00c7 }
    L_0x0036:
        r1 = r0.next();	 Catch:{ Exception -> 0x00c7 }
        r1 = (android.os.AsyncTask) r1;	 Catch:{ Exception -> 0x00c7 }
        r1.cancel(r13);	 Catch:{ Exception -> 0x00c7 }
        goto L_0x0030;	 Catch:{ Exception -> 0x00c7 }
    L_0x0040:
        return r12;	 Catch:{ Exception -> 0x00c7 }
    L_0x0041:
        r4 = r0[r11];	 Catch:{ Exception -> 0x00c7 }
        r4 = android.net.Uri.parse(r4);	 Catch:{ Exception -> 0x00c7 }
        r5 = com.facebook.internal.bj.b(r4);	 Catch:{ Exception -> 0x00c7 }
        if (r5 == 0) goto L_0x0057;	 Catch:{ Exception -> 0x00c7 }
    L_0x004d:
        r4 = r4.toString();	 Catch:{ Exception -> 0x00c7 }
        r1[r11] = r4;	 Catch:{ Exception -> 0x00c7 }
        r2.countDown();	 Catch:{ Exception -> 0x00c7 }
        goto L_0x00b7;	 Catch:{ Exception -> 0x00c7 }
    L_0x0057:
        r9 = new com.facebook.internal.bs$1;	 Catch:{ Exception -> 0x00c7 }
        r9.<init>(r15, r1, r11, r2);	 Catch:{ Exception -> 0x00c7 }
        r5 = com.facebook.internal.bj.d(r4);	 Catch:{ Exception -> 0x00c7 }
        if (r5 == 0) goto L_0x008e;	 Catch:{ Exception -> 0x00c7 }
    L_0x0062:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x00c7 }
        r4 = r4.getPath();	 Catch:{ Exception -> 0x00c7 }
        r5.<init>(r4);	 Catch:{ Exception -> 0x00c7 }
        r4 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;	 Catch:{ Exception -> 0x00c7 }
        r4 = android.os.ParcelFileDescriptor.open(r5, r4);	 Catch:{ Exception -> 0x00c7 }
        r5 = new com.facebook.GraphRequest$ParcelableResourceWithMimeType;	 Catch:{ Exception -> 0x00c7 }
        r6 = "image/png";	 Catch:{ Exception -> 0x00c7 }
        r5.<init>(r4, r6);	 Catch:{ Exception -> 0x00c7 }
        r7 = new android.os.Bundle;	 Catch:{ Exception -> 0x00c7 }
        r7.<init>(r13);	 Catch:{ Exception -> 0x00c7 }
        r4 = "file";	 Catch:{ Exception -> 0x00c7 }
        r7.putParcelable(r4, r5);	 Catch:{ Exception -> 0x00c7 }
        r14 = new com.facebook.GraphRequest;	 Catch:{ Exception -> 0x00c7 }
        r6 = "me/staging_resources";	 Catch:{ Exception -> 0x00c7 }
        r8 = com.facebook.ag.POST;	 Catch:{ Exception -> 0x00c7 }
        r4 = r14;	 Catch:{ Exception -> 0x00c7 }
        r5 = r10;	 Catch:{ Exception -> 0x00c7 }
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00c7 }
        goto L_0x00b0;	 Catch:{ Exception -> 0x00c7 }
    L_0x008e:
        r5 = com.facebook.internal.bj.c(r4);	 Catch:{ Exception -> 0x00c7 }
        if (r5 == 0) goto L_0x00bb;	 Catch:{ Exception -> 0x00c7 }
    L_0x0094:
        r5 = new com.facebook.GraphRequest$ParcelableResourceWithMimeType;	 Catch:{ Exception -> 0x00c7 }
        r6 = "image/png";	 Catch:{ Exception -> 0x00c7 }
        r5.<init>(r4, r6);	 Catch:{ Exception -> 0x00c7 }
        r7 = new android.os.Bundle;	 Catch:{ Exception -> 0x00c7 }
        r7.<init>(r13);	 Catch:{ Exception -> 0x00c7 }
        r4 = "file";	 Catch:{ Exception -> 0x00c7 }
        r7.putParcelable(r4, r5);	 Catch:{ Exception -> 0x00c7 }
        r14 = new com.facebook.GraphRequest;	 Catch:{ Exception -> 0x00c7 }
        r6 = "me/staging_resources";	 Catch:{ Exception -> 0x00c7 }
        r8 = com.facebook.ag.POST;	 Catch:{ Exception -> 0x00c7 }
        r4 = r14;	 Catch:{ Exception -> 0x00c7 }
        r5 = r10;	 Catch:{ Exception -> 0x00c7 }
        r4.<init>(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00c7 }
    L_0x00b0:
        r4 = r14.g();	 Catch:{ Exception -> 0x00c7 }
        r3.add(r4);	 Catch:{ Exception -> 0x00c7 }
    L_0x00b7:
        r11 = r11 + 1;	 Catch:{ Exception -> 0x00c7 }
        goto L_0x0021;	 Catch:{ Exception -> 0x00c7 }
    L_0x00bb:
        r0 = new com.facebook.n;	 Catch:{ Exception -> 0x00c7 }
        r1 = "The image Uri must be either a file:// or content:// Uri";	 Catch:{ Exception -> 0x00c7 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00c7 }
        throw r0;	 Catch:{ Exception -> 0x00c7 }
    L_0x00c3:
        r2.await();	 Catch:{ Exception -> 0x00c7 }
        return r1;
    L_0x00c7:
        r0 = r3.iterator();
    L_0x00cb:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x00db;
    L_0x00d1:
        r1 = r0.next();
        r1 = (android.os.AsyncTask) r1;
        r1.cancel(r13);
        goto L_0x00cb;
    L_0x00db:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bs.a():java.lang.String[]");
    }
}
