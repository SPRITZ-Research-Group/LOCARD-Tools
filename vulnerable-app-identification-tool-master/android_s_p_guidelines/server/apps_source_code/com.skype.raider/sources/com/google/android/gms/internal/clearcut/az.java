package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class az extends e<Long> implements am<Long>, RandomAccess {
    private static final az a;
    private long[] b;
    private int c;

    static {
        e azVar = new az();
        a = azVar;
        azVar.b();
    }

    az() {
        this(new long[10], 0);
    }

    private az(long[] jArr, int i) {
        this.b = jArr;
        this.c = i;
    }

    private final void c(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    private final String d(int i) {
        return "Index:" + i + ", Size:" + this.c;
    }

    public final long a(int i) {
        c(i);
        return this.b[i];
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            Object obj2 = new long[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, obj2, 0, i);
            System.arraycopy(this.b, i, obj2, i + 1, this.c - i);
            this.b = obj2;
        }
        this.b[i] = longValue;
        this.c++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        c();
        aj.a((Object) collection);
        if (!(collection instanceof az)) {
            return super.addAll(collection);
        }
        az azVar = (az) collection;
        if (azVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < azVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + azVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(azVar.b, 0, this.b, this.c, azVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final /* synthetic */ am b(int i) {
        if (i >= this.c) {
            return new az(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof az)) {
            return super.equals(obj);
        }
        az azVar = (az) obj;
        if (this.c != azVar.c) {
            return false;
        }
        long[] jArr = azVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(a(i));
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + aj.a(this.b[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        c(i);
        long j = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Long.valueOf(this.b[i]))) {
                System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
                this.c--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    protected final void removeRange(int i, int i2) {
        c();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.b, i2, this.b, i, this.c - i2);
        this.c -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        c();
        c(i);
        long j = this.b[i];
        this.b[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.c;
    }
}
