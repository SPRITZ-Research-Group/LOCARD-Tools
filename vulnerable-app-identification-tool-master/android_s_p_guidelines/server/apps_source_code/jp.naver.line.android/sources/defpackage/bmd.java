package defpackage;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: bmd */
public abstract class bmd<K, V> implements Serializable, Map<K, V> {
    static final Entry<?, ?>[] a = new Entry[0];
    @LazyInit
    private transient bmq<Entry<K, V>> b;
    @LazyInit
    private transient bmq<K> c;
    @LazyInit
    private transient blv<V> d;

    abstract bmq<Entry<K, V>> g();

    public abstract V get(Object obj);

    boolean l() {
        return false;
    }

    public /* synthetic */ Set entrySet() {
        return f();
    }

    public /* synthetic */ Set keySet() {
        return h();
    }

    public /* synthetic */ Collection values() {
        return c();
    }

    public static <K, V> bmd<K, V> b(K k, V v) {
        return bls.a(k, v);
    }

    static <K, V> bmg<K, V> c(K k, V v) {
        return new bmg(k, v);
    }

    public static <K, V> bme<K, V> e() {
        return new bme();
    }

    static void a(boolean z, String str, Entry<?, ?> entry, Entry<?, ?> entry2) {
        if (!z) {
            StringBuilder stringBuilder = new StringBuilder("Multiple entries with same ");
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(entry);
            stringBuilder.append(" and ");
            stringBuilder.append(entry2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    bmd() {
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return c().contains(obj);
    }

    public final bmq<Entry<K, V>> f() {
        bmq<Entry<K, V>> bmq = this.b;
        if (bmq != null) {
            return bmq;
        }
        bmq = g();
        this.b = bmq;
        return bmq;
    }

    public final bmq<K> h() {
        bmq<K> bmq = this.c;
        if (bmq != null) {
            return bmq;
        }
        bmq = i();
        this.c = bmq;
        return bmq;
    }

    bmq<K> i() {
        return isEmpty() ? bmq.f() : new bmm(this);
    }

    final bnr<K> j() {
        final bnr b = f().b();
        return new bnr<K>(this) {
            final /* synthetic */ bmd b;

            public final boolean hasNext() {
                return b.hasNext();
            }

            public final K next() {
                return ((Entry) b.next()).getKey();
            }
        };
    }

    public blv<V> c() {
        blv<V> blv = this.d;
        if (blv != null) {
            return blv;
        }
        blv = k();
        this.d = blv;
        return blv;
    }

    blv<V> k() {
        return new bmo(this);
    }

    public boolean equals(Object obj) {
        return bmw.a((Map) this, obj);
    }

    public int hashCode() {
        return bnm.a(f());
    }

    public String toString() {
        return bmw.a(this);
    }

    Object writeReplace() {
        return new bmf(this);
    }

    public static <K, V> bmd<K, V> d() {
        return bnb.b;
    }
}
