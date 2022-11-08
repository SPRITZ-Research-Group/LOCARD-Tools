package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class g implements Cloneable {
    private e<?, ?> a;
    private Object b;
    private List<l> c = new ArrayList();

    g() {
    }

    private final byte[] b() throws IOException {
        byte[] bArr = new byte[a()];
        a(b.a(bArr));
        return bArr;
    }

    private final g c() {
        int i = 0;
        g gVar = new g();
        try {
            gVar.a = this.a;
            if (this.c == null) {
                gVar.c = null;
            } else {
                gVar.c.addAll(this.c);
            }
            if (this.b != null) {
                Object obj;
                int i2;
                if (this.b instanceof j) {
                    gVar.b = (j) ((j) this.b).clone();
                } else if (this.b instanceof byte[]) {
                    gVar.b = ((byte[]) this.b).clone();
                } else if (this.b instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.b;
                    obj = new byte[bArr.length][];
                    gVar.b = obj;
                    while (true) {
                        i2 = i;
                        if (i2 >= bArr.length) {
                            break;
                        }
                        obj[i2] = (byte[]) bArr[i2].clone();
                        i = i2 + 1;
                    }
                } else if (this.b instanceof boolean[]) {
                    gVar.b = ((boolean[]) this.b).clone();
                } else if (this.b instanceof int[]) {
                    gVar.b = ((int[]) this.b).clone();
                } else if (this.b instanceof long[]) {
                    gVar.b = ((long[]) this.b).clone();
                } else if (this.b instanceof float[]) {
                    gVar.b = ((float[]) this.b).clone();
                } else if (this.b instanceof double[]) {
                    gVar.b = ((double[]) this.b).clone();
                } else if (this.b instanceof j[]) {
                    j[] jVarArr = (j[]) this.b;
                    obj = new j[jVarArr.length];
                    gVar.b = obj;
                    while (true) {
                        i2 = i;
                        if (i2 >= jVarArr.length) {
                            break;
                        }
                        obj[i2] = (j) jVarArr[i2].clone();
                        i = i2 + 1;
                    }
                }
            }
            return gVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    final int a() {
        int i = 0;
        int i2;
        if (this.b != null) {
            e eVar = this.a;
            Object obj = this.b;
            if (!eVar.c) {
                return eVar.a(obj);
            }
            int length = Array.getLength(obj);
            for (i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += eVar.a(Array.get(obj, i2));
                }
            }
            return i;
        }
        Iterator it = this.c.iterator();
        while (true) {
            i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            l lVar = (l) it.next();
            i = (lVar.b.length + (b.c(lVar.a) + 0)) + i2;
        }
    }

    final void a(b bVar) throws IOException {
        if (this.b != null) {
            e eVar = this.a;
            Object obj = this.b;
            if (eVar.c) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        eVar.a(obj2, bVar);
                    }
                }
                return;
            }
            eVar.a(obj, bVar);
            return;
        }
        for (l lVar : this.c) {
            bVar.b(lVar.a);
            bVar.b(lVar.b);
        }
    }

    final void a(l lVar) throws IOException {
        if (this.c != null) {
            this.c.add(lVar);
            return;
        }
        Object a;
        if (this.b instanceof j) {
            byte[] bArr = lVar.b;
            a a2 = a.a(bArr, bArr.length);
            int d = a2.d();
            if (d != bArr.length - b.a(d)) {
                throw i.a();
            }
            a = ((j) this.b).a(a2);
        } else if (this.b instanceof j[]) {
            j[] jVarArr = (j[]) this.a.a(Collections.singletonList(lVar));
            j[] jVarArr2 = (j[]) this.b;
            j[] a3 = (j[]) Arrays.copyOf(jVarArr2, jVarArr2.length + jVarArr.length);
            System.arraycopy(jVarArr, 0, a3, jVarArr2.length, jVarArr.length);
        } else {
            a3 = this.a.a(Collections.singletonList(lVar));
        }
        this.a = this.a;
        this.b = a3;
        this.c = null;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.b != null && gVar.b != null) {
            return this.a == gVar.a ? !this.a.a.isArray() ? this.b.equals(gVar.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) gVar.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) gVar.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) gVar.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) gVar.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) gVar.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) gVar.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) gVar.b) : false;
        } else {
            if (this.c != null && gVar.c != null) {
                return this.c.equals(gVar.c);
            }
            try {
                return Arrays.equals(b(), gVar.b());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(b()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
