package com.google.android.gms.internal.tapandpay;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public abstract class zzat<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzeu = new Object[0];

    zzat() {
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ Iterator iterator() {
        return zza();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        int size = size();
        if (size == 0) {
            return zzeu;
        }
        Object[] objArr = new Object[size];
        zza(objArr, 0);
        return objArr;
    }

    public final <T> T[] toArray(T[] tArr) {
        zzap.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }

    int zza(Object[] objArr, int i) {
        zzbe zzbe = (zzbe) iterator();
        while (zzbe.hasNext()) {
            int i2 = i + 1;
            objArr[i] = zzbe.next();
            i = i2;
        }
        return i;
    }

    public abstract zzbe<E> zza();

    public zzau<E> zzb() {
        return isEmpty() ? zzau.zzc() : zzau.zza(toArray());
    }
}
