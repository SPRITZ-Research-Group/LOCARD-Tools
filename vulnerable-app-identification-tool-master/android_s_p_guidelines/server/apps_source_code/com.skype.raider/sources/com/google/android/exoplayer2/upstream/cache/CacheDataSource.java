package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.g;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CacheDataSource implements f {
    private final a a;
    private final f b;
    private final f c;
    private final f d;
    @Nullable
    private final a e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private f i;
    private boolean j;
    private Uri k;
    private int l;
    private String m;
    private long n;
    private long o;
    private b p;
    private boolean q;
    private boolean r;
    private long s;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public interface a {
    }

    public final long a(DataSpec dataSpec) throws IOException {
        boolean z = true;
        try {
            String str;
            this.k = dataSpec.a;
            this.l = dataSpec.g;
            if (dataSpec.f != null) {
                str = dataSpec.f;
            } else {
                str = dataSpec.a.toString();
            }
            this.m = str;
            this.n = dataSpec.d;
            if (!((this.g && this.q) || (dataSpec.e == -1 && this.h))) {
                z = false;
            }
            this.r = z;
            if (dataSpec.e != -1 || this.r) {
                this.o = dataSpec.e;
            } else {
                this.o = this.a.c();
                if (this.o != -1) {
                    this.o -= dataSpec.d;
                    if (this.o <= 0) {
                        throw new g();
                    }
                }
            }
            a(true);
            return this.o;
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    public final int a(byte[] buffer, int offset, int readLength) throws IOException {
        if (readLength == 0) {
            return 0;
        }
        if (this.o == 0) {
            return -1;
        }
        try {
            int bytesRead = this.i.a(buffer, offset, readLength);
            if (bytesRead >= 0) {
                if (this.i == this.b) {
                    this.s += (long) bytesRead;
                }
                this.n += (long) bytesRead;
                if (this.o == -1) {
                    return bytesRead;
                }
                this.o -= (long) bytesRead;
                return bytesRead;
            }
            if (this.j) {
                this.o = 0;
            }
            c();
            if ((this.o > 0 || this.o == -1) && a(false)) {
                return a(buffer, offset, readLength);
            }
            return bytesRead;
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    public final Uri a() {
        return this.i == this.d ? this.i.a() : this.k;
    }

    public final void b() throws IOException {
        this.k = null;
        if (this.e != null && this.s > 0) {
            this.s = 0;
        }
        try {
            c();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    private boolean a(boolean r27) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r4_3 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec) in PHI: PHI: (r4_1 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec) = (r4_0 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec), (r4_2 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec), (r4_3 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec), (r4_3 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec) binds: {(r4_0 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec)=B:4:0x000a, (r4_2 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec)=B:31:0x00a8, (r4_3 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec)=B:39:0x00f6, (r4_3 'dataSpec' com.google.android.exoplayer2.upstream.DataSpec)=B:44:0x011f}
	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
	at jadx.core.dex.visitors.ModVisitor.processInvoke(ModVisitor.java:222)
	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:83)
	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:68)
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
        r26 = this;
        r0 = r26;
        r6 = r0.r;
        if (r6 == 0) goto L_0x0059;
    L_0x0006:
        r24 = 0;
    L_0x0008:
        if (r24 != 0) goto L_0x0078;
    L_0x000a:
        r0 = r26;
        r6 = r0.d;
        r0 = r26;
        r0.i = r6;
        r4 = new com.google.android.exoplayer2.upstream.DataSpec;
        r0 = r26;
        r5 = r0.k;
        r0 = r26;
        r6 = r0.n;
        r0 = r26;
        r8 = r0.o;
        r0 = r26;
        r10 = r0.m;
        r0 = r26;
        r11 = r0.l;
        r4.<init>(r5, r6, r8, r10, r11);
    L_0x002b:
        r6 = r4.e;
        r12 = -1;
        r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r6 != 0) goto L_0x0129;
    L_0x0033:
        r6 = 1;
    L_0x0034:
        r0 = r26;
        r0.j = r6;
        r25 = 0;
        r22 = 0;
        r0 = r26;	 Catch:{ IOException -> 0x012c }
        r6 = r0.i;	 Catch:{ IOException -> 0x012c }
        r22 = r6.a(r4);	 Catch:{ IOException -> 0x012c }
        r25 = 1;
    L_0x0046:
        r0 = r26;
        r6 = r0.j;
        if (r6 == 0) goto L_0x0058;
    L_0x004c:
        r6 = -1;
        r6 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1));
        if (r6 == 0) goto L_0x0058;
    L_0x0052:
        r0 = r22;
        r2 = r26;
        r2.o = r0;
    L_0x0058:
        return r25;
    L_0x0059:
        r0 = r26;
        r6 = r0.f;
        if (r6 == 0) goto L_0x006f;
    L_0x005f:
        r0 = r26;	 Catch:{ InterruptedException -> 0x0068 }
        r6 = r0.a;	 Catch:{ InterruptedException -> 0x0068 }
        r24 = r6.a();	 Catch:{ InterruptedException -> 0x0068 }
        goto L_0x0008;
    L_0x0068:
        r6 = move-exception;
        r6 = new java.io.InterruptedIOException;
        r6.<init>();
        throw r6;
    L_0x006f:
        r0 = r26;
        r6 = r0.a;
        r24 = r6.b();
        goto L_0x0008;
    L_0x0078:
        r0 = r24;
        r6 = r0.d;
        if (r6 == 0) goto L_0x00c3;
    L_0x007e:
        r0 = r24;
        r6 = r0.e;
        r5 = android.net.Uri.fromFile(r6);
        r0 = r26;
        r6 = r0.n;
        r0 = r24;
        r12 = r0.b;
        r8 = r6 - r12;
        r0 = r24;
        r6 = r0.c;
        r10 = r6 - r8;
        r0 = r26;
        r6 = r0.o;
        r12 = -1;
        r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r6 == 0) goto L_0x00a8;
    L_0x00a0:
        r0 = r26;
        r6 = r0.o;
        r10 = java.lang.Math.min(r10, r6);
    L_0x00a8:
        r4 = new com.google.android.exoplayer2.upstream.DataSpec;
        r0 = r26;
        r6 = r0.n;
        r0 = r26;
        r12 = r0.m;
        r0 = r26;
        r13 = r0.l;
        r4.<init>(r5, r6, r8, r10, r12, r13);
        r0 = r26;
        r6 = r0.b;
        r0 = r26;
        r0.i = r6;
        goto L_0x002b;
    L_0x00c3:
        r0 = r24;
        r6 = r0.c;
        r12 = -1;
        r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r6 != 0) goto L_0x0106;
    L_0x00cd:
        r6 = 1;
    L_0x00ce:
        if (r6 == 0) goto L_0x0108;
    L_0x00d0:
        r0 = r26;
        r10 = r0.o;
    L_0x00d4:
        r4 = new com.google.android.exoplayer2.upstream.DataSpec;
        r0 = r26;
        r13 = r0.k;
        r0 = r26;
        r14 = r0.n;
        r0 = r26;
        r0 = r0.m;
        r18 = r0;
        r0 = r26;
        r0 = r0.l;
        r19 = r0;
        r12 = r4;
        r16 = r10;
        r12.<init>(r13, r14, r16, r18, r19);
        r0 = r26;
        r6 = r0.c;
        if (r6 == 0) goto L_0x011f;
    L_0x00f6:
        r0 = r26;
        r6 = r0.c;
        r0 = r26;
        r0.i = r6;
        r0 = r24;
        r1 = r26;
        r1.p = r0;
        goto L_0x002b;
    L_0x0106:
        r6 = 0;
        goto L_0x00ce;
    L_0x0108:
        r0 = r24;
        r10 = r0.c;
        r0 = r26;
        r6 = r0.o;
        r12 = -1;
        r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r6 == 0) goto L_0x00d4;
    L_0x0116:
        r0 = r26;
        r6 = r0.o;
        r10 = java.lang.Math.min(r10, r6);
        goto L_0x00d4;
    L_0x011f:
        r0 = r26;
        r6 = r0.d;
        r0 = r26;
        r0.i = r6;
        goto L_0x002b;
    L_0x0129:
        r6 = 0;
        goto L_0x0034;
    L_0x012c:
        r21 = move-exception;
        if (r27 != 0) goto L_0x0149;
    L_0x012f:
        r0 = r26;
        r6 = r0.j;
        if (r6 == 0) goto L_0x0149;
    L_0x0135:
        r20 = r21;
    L_0x0137:
        if (r20 == 0) goto L_0x0149;
    L_0x0139:
        r0 = r20;
        r6 = r0 instanceof com.google.android.exoplayer2.upstream.g;
        if (r6 == 0) goto L_0x014c;
    L_0x013f:
        r6 = r20;
        r6 = (com.google.android.exoplayer2.upstream.g) r6;
        r6 = r6.a;
        if (r6 != 0) goto L_0x014c;
    L_0x0147:
        r21 = 0;
    L_0x0149:
        if (r21 == 0) goto L_0x0046;
    L_0x014b:
        throw r21;
    L_0x014c:
        r20 = r20.getCause();
        goto L_0x0137;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CacheDataSource.a(boolean):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() throws IOException {
        if (this.i != null) {
            try {
                this.i.b();
                this.i = null;
                this.j = false;
                if (this.p != null) {
                    this.p = null;
                }
            } catch (Throwable th) {
                if (this.p != null) {
                    this.p = null;
                }
            }
        }
    }

    private void a(IOException exception) {
        if (this.i == this.b || (exception instanceof com.google.android.exoplayer2.upstream.cache.a.a)) {
            this.q = true;
        }
    }
}
