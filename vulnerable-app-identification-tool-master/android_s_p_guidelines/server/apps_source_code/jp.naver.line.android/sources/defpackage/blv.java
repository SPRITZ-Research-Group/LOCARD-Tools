package defpackage;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: blv */
public abstract class blv<E> extends AbstractCollection<E> implements Serializable {
    public abstract bnr<E> b();

    public abstract boolean contains(Object obj);

    public /* synthetic */ Iterator iterator() {
        return b();
    }

    blv() {
    }

    public final Object[] toArray() {
        int size = size();
        if (size == 0) {
            return bmy.a;
        }
        Object[] objArr = new Object[size];
        a(objArr, 0);
        return objArr;
    }

    @CanIgnoreReturnValue
    public final <T> T[] toArray(T[] tArr) {
        bkz.a((Object) tArr);
        int size = size();
        if (tArr.length < size) {
            tArr = bmy.a((Object[]) tArr, size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        a(tArr, 0);
        return tArr;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public blz<E> c() {
        switch (size()) {
            case 0:
                return blz.d();
            case 1:
                return blz.a(b().next());
            default:
                return new bna(this, toArray());
        }
    }

    @CanIgnoreReturnValue
    int a(Object[] objArr, int i) {
        Iterator it = iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            objArr[i] = it.next();
            i = i2;
        }
        return i;
    }

    Object writeReplace() {
        return new bmb(toArray());
    }
}
