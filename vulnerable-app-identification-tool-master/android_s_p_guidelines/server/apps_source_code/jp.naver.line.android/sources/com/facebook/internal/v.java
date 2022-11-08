package com.facebook.internal;

import com.facebook.ai;
import com.facebook.s;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import jp.naver.android.npush.network.NPushProtocol;
import org.json.JSONException;
import org.json.JSONObject;

public final class v {
    static final String a = "v";
    private static final AtomicLong b = new AtomicLong();
    private final String c;
    private final z d;
    private final File e;
    private boolean f;
    private boolean g;
    private final Object h;
    private AtomicLong i = new AtomicLong(0);

    public v(String str, z zVar) {
        this.c = str;
        this.d = zVar;
        this.e = new File(s.n(), str);
        this.h = new Object();
        if (this.e.mkdirs() || this.e.isDirectory()) {
            w.a(this.e);
        }
    }

    public final java.io.InputStream a(java.lang.String r7, java.lang.String r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.v.a(java.lang.String, java.lang.String):java.io.InputStream. bs: []
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
        r6 = this;
        r0 = new java.io.File;
        r1 = r6.e;
        r2 = com.facebook.internal.bj.b(r7);
        r0.<init>(r1, r2);
        r1 = 0;
        r2 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0082 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0082 }
        r3 = new java.io.BufferedInputStream;
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3.<init>(r2, r4);
        r2 = com.facebook.internal.ac.a(r3);	 Catch:{ all -> 0x007d }
        if (r2 != 0) goto L_0x0022;
    L_0x001e:
        r3.close();
        return r1;
    L_0x0022:
        r4 = "key";	 Catch:{ all -> 0x007d }
        r4 = r2.optString(r4);	 Catch:{ all -> 0x007d }
        if (r4 == 0) goto L_0x0079;	 Catch:{ all -> 0x007d }
    L_0x002a:
        r7 = r4.equals(r7);	 Catch:{ all -> 0x007d }
        if (r7 != 0) goto L_0x0031;	 Catch:{ all -> 0x007d }
    L_0x0030:
        goto L_0x0079;	 Catch:{ all -> 0x007d }
    L_0x0031:
        r7 = "tag";	 Catch:{ all -> 0x007d }
        r7 = r2.optString(r7, r1);	 Catch:{ all -> 0x007d }
        if (r8 != 0) goto L_0x003b;	 Catch:{ all -> 0x007d }
    L_0x0039:
        if (r7 != 0) goto L_0x0043;	 Catch:{ all -> 0x007d }
    L_0x003b:
        if (r8 == 0) goto L_0x0047;	 Catch:{ all -> 0x007d }
    L_0x003d:
        r7 = r8.equals(r7);	 Catch:{ all -> 0x007d }
        if (r7 != 0) goto L_0x0047;
    L_0x0043:
        r3.close();
        return r1;
    L_0x0047:
        r7 = new java.util.Date;	 Catch:{ all -> 0x007d }
        r7.<init>();	 Catch:{ all -> 0x007d }
        r7 = r7.getTime();	 Catch:{ all -> 0x007d }
        r1 = com.facebook.ai.CACHE;	 Catch:{ all -> 0x007d }
        r2 = a;	 Catch:{ all -> 0x007d }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007d }
        r5 = "Setting lastModified to ";	 Catch:{ all -> 0x007d }
        r4.<init>(r5);	 Catch:{ all -> 0x007d }
        r5 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x007d }
        r4.append(r5);	 Catch:{ all -> 0x007d }
        r5 = " for ";	 Catch:{ all -> 0x007d }
        r4.append(r5);	 Catch:{ all -> 0x007d }
        r5 = r0.getName();	 Catch:{ all -> 0x007d }
        r4.append(r5);	 Catch:{ all -> 0x007d }
        r4 = r4.toString();	 Catch:{ all -> 0x007d }
        com.facebook.internal.ar.a(r1, r2, r4);	 Catch:{ all -> 0x007d }
        r0.setLastModified(r7);	 Catch:{ all -> 0x007d }
        return r3;
    L_0x0079:
        r3.close();
        return r1;
    L_0x007d:
        r7 = move-exception;
        r3.close();
        throw r7;
    L_0x0082:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.v.a(java.lang.String, java.lang.String):java.io.InputStream");
    }

    public final OutputStream b(String str, String str2) throws IOException {
        final File b = w.b(this.e);
        b.delete();
        if (b.createNewFile()) {
            try {
                OutputStream fileOutputStream = new FileOutputStream(b);
                final long currentTimeMillis = System.currentTimeMillis();
                final String str3 = str;
                OutputStream bufferedOutputStream = new BufferedOutputStream(new x(fileOutputStream, new ab(this) {
                    final /* synthetic */ v d;

                    public final void a() {
                        if (currentTimeMillis < this.d.i.get()) {
                            b.delete();
                        } else {
                            v.a(this.d, str3, b);
                        }
                    }
                }), 8192);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(NPushProtocol.PROTOCOL_KEY, str);
                    if (!bj.a(str2)) {
                        jSONObject.put("tag", str2);
                    }
                    byte[] bytes = jSONObject.toString().getBytes();
                    bufferedOutputStream.write(0);
                    bufferedOutputStream.write((bytes.length >> 16) & 255);
                    bufferedOutputStream.write((bytes.length >> 8) & 255);
                    bufferedOutputStream.write((bytes.length >> 0) & 255);
                    bufferedOutputStream.write(bytes);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    ar.a(ai.CACHE, 5, a, "Error creating JSON header for cache file: ".concat(String.valueOf(e)));
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                }
            } catch (FileNotFoundException e2) {
                ar.a(ai.CACHE, 5, a, "Error creating buffer output stream: ".concat(String.valueOf(e2)));
                throw new IOException(e2.getMessage());
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Could not create file at ");
        stringBuilder.append(b.getAbsolutePath());
        throw new IOException(stringBuilder.toString());
    }

    public final void a() {
        final File[] listFiles = this.e.listFiles(w.a());
        this.i.set(System.currentTimeMillis());
        if (listFiles != null) {
            s.d().execute(new Runnable(this) {
                final /* synthetic */ v b;

                public final void run() {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            });
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("{FileLruCache: tag:");
        stringBuilder.append(this.c);
        stringBuilder.append(" file:");
        stringBuilder.append(this.e.getName());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    static /* synthetic */ void a(v vVar, String str, File file) {
        if (!file.renameTo(new File(vVar.e, bj.b(str)))) {
            file.delete();
        }
        synchronized (vVar.h) {
            if (!vVar.f) {
                vVar.f = true;
                s.d().execute(new Runnable(vVar) {
                    final /* synthetic */ v a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        v.b(this.a);
                    }
                });
            }
        }
    }

    static /* synthetic */ void b(v vVar) {
        v vVar2 = vVar;
        synchronized (vVar2.h) {
            vVar2.f = false;
            vVar2.g = true;
        }
        try {
            long j;
            ar.a(ai.CACHE, a, "trim started");
            PriorityQueue priorityQueue = new PriorityQueue();
            File[] listFiles = vVar2.e.listFiles(w.a());
            long j2 = 0;
            if (listFiles != null) {
                long j3 = 0;
                j = j3;
                for (File file : listFiles) {
                    aa aaVar = new aa(file);
                    priorityQueue.add(aaVar);
                    ai aiVar = ai.CACHE;
                    String str = a;
                    StringBuilder stringBuilder = new StringBuilder("  trim considering time=");
                    stringBuilder.append(Long.valueOf(aaVar.b()));
                    stringBuilder.append(" name=");
                    stringBuilder.append(aaVar.a().getName());
                    ar.a(aiVar, str, stringBuilder.toString());
                    j3 += file.length();
                    j++;
                }
                j2 = j3;
            } else {
                j = 0;
            }
            while (true) {
                if (j2 > ((long) vVar2.d.a()) || j > ((long) vVar2.d.b())) {
                    File a = ((aa) priorityQueue.remove()).a();
                    ai aiVar2 = ai.CACHE;
                    String str2 = a;
                    StringBuilder stringBuilder2 = new StringBuilder("  trim removing ");
                    stringBuilder2.append(a.getName());
                    ar.a(aiVar2, str2, stringBuilder2.toString());
                    j2 -= a.length();
                    j--;
                    a.delete();
                } else {
                    synchronized (vVar2.h) {
                        vVar2.g = false;
                        vVar2.h.notifyAll();
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            synchronized (vVar2.h) {
                vVar2.g = false;
                vVar2.h.notifyAll();
            }
        }
    }
}
