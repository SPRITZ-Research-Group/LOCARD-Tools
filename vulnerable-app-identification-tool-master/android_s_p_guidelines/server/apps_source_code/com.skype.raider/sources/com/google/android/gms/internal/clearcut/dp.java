package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class dp implements Cloneable {
    private dn<?, ?> a;
    private Object b;
    private List<Object> c = new ArrayList();

    dp() {
    }

    private final dp b() {
        int i = 0;
        dp dpVar = new dp();
        try {
            dpVar.a = this.a;
            if (this.c == null) {
                dpVar.c = null;
            } else {
                dpVar.c.addAll(this.c);
            }
            if (this.b != null) {
                Object obj;
                int i2;
                if (this.b instanceof dr) {
                    dpVar.b = (dr) ((dr) this.b).clone();
                } else if (this.b instanceof byte[]) {
                    dpVar.b = ((byte[]) this.b).clone();
                } else if (this.b instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.b;
                    obj = new byte[bArr.length][];
                    dpVar.b = obj;
                    while (true) {
                        i2 = i;
                        if (i2 >= bArr.length) {
                            break;
                        }
                        obj[i2] = (byte[]) bArr[i2].clone();
                        i = i2 + 1;
                    }
                } else if (this.b instanceof boolean[]) {
                    dpVar.b = ((boolean[]) this.b).clone();
                } else if (this.b instanceof int[]) {
                    dpVar.b = ((int[]) this.b).clone();
                } else if (this.b instanceof long[]) {
                    dpVar.b = ((long[]) this.b).clone();
                } else if (this.b instanceof float[]) {
                    dpVar.b = ((float[]) this.b).clone();
                } else if (this.b instanceof double[]) {
                    dpVar.b = ((double[]) this.b).clone();
                } else if (this.b instanceof dr[]) {
                    dr[] drVarArr = (dr[]) this.b;
                    obj = new dr[drVarArr.length];
                    dpVar.b = obj;
                    while (true) {
                        i2 = i;
                        if (i2 >= drVarArr.length) {
                            break;
                        }
                        obj[i2] = (dr) drVarArr[i2].clone();
                        i = i2 + 1;
                    }
                }
            }
            return dpVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dp)) {
            return false;
        }
        dp dpVar = (dp) obj;
        if (this.b != null && dpVar.b != null) {
            return this.a == dpVar.a ? !this.a.a.isArray() ? this.b.equals(dpVar.b) : this.b instanceof byte[] ? Arrays.equals((byte[]) this.b, (byte[]) dpVar.b) : this.b instanceof int[] ? Arrays.equals((int[]) this.b, (int[]) dpVar.b) : this.b instanceof long[] ? Arrays.equals((long[]) this.b, (long[]) dpVar.b) : this.b instanceof float[] ? Arrays.equals((float[]) this.b, (float[]) dpVar.b) : this.b instanceof double[] ? Arrays.equals((double[]) this.b, (double[]) dpVar.b) : this.b instanceof boolean[] ? Arrays.equals((boolean[]) this.b, (boolean[]) dpVar.b) : Arrays.deepEquals((Object[]) this.b, (Object[]) dpVar.b) : false;
        } else {
            if (this.c != null && dpVar.c != null) {
                return this.c.equals(dpVar.c);
            }
            try {
                return Arrays.equals(a(), dpVar.a());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(a()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private final byte[] a() throws IOException {
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
        byte[] bArr = new byte[0];
        dl.a(bArr);
        if (this.b != null) {
            throw new NoSuchMethodError();
        }
        Iterator it2 = this.c.iterator();
        if (!it2.hasNext()) {
            return bArr;
        }
        it2.next();
        throw new NoSuchMethodError();
    }
}
