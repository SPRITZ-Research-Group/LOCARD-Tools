package defpackage;

import java.util.Iterator;

/* renamed from: aczt */
final class aczt<E> implements Iterable<E> {
    private static final aczt<Object> c = new aczt();
    final E a;
    final aczt<E> b;
    private final int d;

    public static <E> aczt<E> a() {
        return c;
    }

    private aczt() {
        this.d = 0;
        this.a = null;
        this.b = null;
    }

    private aczt(E e, aczt<E> aczt) {
        this.a = e;
        this.b = aczt;
        this.d = aczt.d + 1;
    }

    private E b(int r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:aczt.b(int):E. bs: []
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
        if (r3 < 0) goto L_0x001f;
    L_0x0002:
        r0 = r2.d;
        if (r3 > r0) goto L_0x001f;
    L_0x0006:
        r0 = r2.c(r3);	 Catch:{ NoSuchElementException -> 0x000f }
        r0 = r0.next();	 Catch:{ NoSuchElementException -> 0x000f }
        return r0;
    L_0x000f:
        r0 = new java.lang.IndexOutOfBoundsException;
        r3 = java.lang.String.valueOf(r3);
        r1 = "Index: ";
        r3 = r1.concat(r3);
        r0.<init>(r3);
        throw r0;
    L_0x001f:
        r3 = new java.lang.IndexOutOfBoundsException;
        r3.<init>();
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: aczt.b(int):E");
    }

    public final Iterator<E> iterator() {
        return c(0);
    }

    public final int b() {
        return this.d;
    }

    private Iterator<E> c(int i) {
        return new aczu(d(i));
    }

    public final aczt<E> a(E e) {
        return new aczt(e, this);
    }

    private aczt<E> b(Object obj) {
        if (this.d == 0) {
            return this;
        }
        if (this.a.equals(obj)) {
            return this.b;
        }
        aczt b = this.b.b(obj);
        if (b == this.b) {
            return this;
        }
        return new aczt(this.a, b);
    }

    public final aczt<E> a(int i) {
        return b(b(i));
    }

    private aczt<E> d(int i) {
        aczt<E> aczt = this;
        while (i >= 0 && i <= aczt.d) {
            if (i == 0) {
                return aczt;
            }
            aczt = aczt.b;
            i--;
        }
        throw new IndexOutOfBoundsException();
    }
}
